package com.example.erp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.GrantedAuthority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utilitaire pour la gestion des tokens JWT dans l'application.
 * <p>
 * Permet de générer, parser, valider et extraire des informations des tokens JWT.
 * Gère la signature, l'expiration et la récupération des rôles utilisateur.
 * </p>
 */
@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    private Key secretKey;
    private static final long JWT_EXPIRATION_IN_MS = 60L * 60 * 1000;

    /**
     * Constructeur par défaut. Initialise la clé secrète.
     */
    public JwtUtil() {
        this.init();
    }

    /**
     * Initialise la clé secrète pour la signature des JWT.
     */
    public void init() {
        secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    /**
     * Retourne la clé secrète utilisée pour signer les JWT.
     * @return la clé secrète
     */
    protected Key getKey() {
        return this.secretKey;
    }

    /**
     * Génère un token JWT pour l'utilisateur donné.
     * @param userDetails les informations de l'utilisateur
     * @return le token JWT généré
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList());
        return createToken(claims, userDetails.getUsername());
    }

    /**
     * Crée un token JWT avec les claims et le sujet donnés.
     * @param claims les informations à inclure dans le token
     * @param subject le sujet (généralement le nom d'utilisateur)
     * @return le token JWT généré
     */
    private String createToken(Map<String, Object> claims, String subject) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION_IN_MS);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }

    /**
     * Extrait le nom d'utilisateur (subject) du token JWT.
     * @param token le token JWT
     * @return le nom d'utilisateur
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrait une information spécifique des claims du token JWT.
     * @param token le token JWT
     * @param claimsResolver fonction pour extraire l'information voulue
     * @return la valeur extraite
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extrait tous les claims du token JWT.
     * @param token le token JWT
     * @return les claims
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .setAllowedClockSkewSeconds(1)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Vérifie si le token JWT est expiré.
     * @param token le token JWT
     * @return true si expiré, false sinon
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extrait la date d'expiration du token JWT.
     * @param token le token JWT
     * @return la date d'expiration
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Valide le token JWT pour un utilisateur donné.
     * Vérifie le nom d'utilisateur, l'expiration et la validité du token.
     * @param token le token JWT
     * @param userDetails les informations de l'utilisateur
     * @return true si le token est valide, false sinon
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (ExpiredJwtException e) {
            logger.warn("Token expiré : {}", e.getMessage());
            return false;
        } catch (JwtException e) {
            logger.warn("Token JWT invalide : {}", e.getMessage());
            return false;
        }
    }
}
