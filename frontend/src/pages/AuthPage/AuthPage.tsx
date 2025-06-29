import React, { useState } from "react";
import "./AuthPage.css";
import { useAuth } from '../../context/AuthContext';
import { useNavigate } from 'react-router-dom';

const AuthPage: React.FC = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const { login, loading, error } = useAuth();
  const [localError, setLocalError] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLocalError("");
    const success = await login(username, password);
    if (!success) setLocalError("Identifiants invalides");
    else navigate('/dashboard');
  };

  return (
    <div className="authpage-container">
      <form className="auth-form" onSubmit={handleSubmit}>
        <h2>Connexion</h2>
        <label htmlFor="username">Nom d'utilisateur</label>
        <input
          id="username"
          type="text"
          value={username}
          onChange={e => setUsername(e.target.value)}
          autoComplete="username"
          required
        />
        <label htmlFor="password">Mot de passe</label>
        <input
          id="password"
          type="password"
          value={password}
          onChange={e => setPassword(e.target.value)}
          autoComplete="current-password"
          required
        />
        {(localError || error) && <div className="auth-error">{localError || error}</div>}
        <button className="cta-button" type="submit" disabled={loading}>
          {loading ? "Connexion..." : "Se connecter"}
        </button>
      </form>
    </div>
  );
};

export default AuthPage;
