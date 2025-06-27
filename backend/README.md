# Projet ERP Personnel – Backend Authentification & Sécurité

## Présentation

Ce projet est une application ERP développée à titre personnel durant mes études.  
L’objectif est de bâtir un système complet de gestion d’entreprise, modulable et évolutif.

Pour l’instant, seule la **partie backend d’authentification et sécurité** est implémentée.  
Cette base technique est essentielle pour garantir une gestion robuste des utilisateurs et des accès, préalable indispensable à tout ERP.

---

## Fonctionnalités actuelles

- Backend Java 21 avec Spring Boot et Maven  
- Gestion sécurisée des utilisateurs grâce à Spring Security et JWT (JSON Web Tokens)  
- Fonctionnalités d’inscription (register), connexion (login) et gestion des sessions via cookies  
- Connexion sécurisée à une base de données MongoDB Atlas  
- Tests unitaires et d’intégration automatisés avec Maven  
- Conteneurisation Docker pour faciliter le déploiement  
- Pipeline CI/CD configuré via GitHub Actions avec analyse de la qualité du code via SonarQube  

---

## Installation et utilisation

### Prérequis

- Java 21 installé sur la machine  
- Maven (pour build local)  
- Docker (pour conteneuriser l’application)  
- Accès à un cluster MongoDB Atlas (compte MongoDB + cluster configuré)  

### Étapes

1. **Cloner le dépôt**  
```bash
git clone <git@github.com:EvannLietard/Erp.git>
cd <Erp>
```

### Pipeline CI/CD & Qualité de code

Le projet intègre un pipeline GitHub Actions qui automatise :

- La compilation et le build
- L’exécution des tests
- L’analyse statique du code avec SonarQube

Ces outils garantissent une meilleure qualité de code et une livraison continue fluide.

### Sécurité et gestion des secrets

La chaîne de connexion MongoDB Atlas **n’est plus stockée en clair** dans `application.properties`.  
Pour garantir la sécurité, la connexion à la base de données utilise désormais une **variable d’environnement** `MONGODB_URI` :

- **En CI/CD** : la variable est injectée via les secrets GitHub Actions, ce qui évite toute exposition d’identifiants sensibles dans le code ou les logs.

> **Note :**  
> Les tests unitaires et d’intégration sont exécutés automatiquement par le pipeline CI/CD (GitHub Actions).  
> **La construction Docker (`Dockerfile`) ne lance pas les tests** : elle se contente de builder et packager l’application, car la qualité du code est déjà garantie par la CI.

> **Note développeur :**
> Si vous voulez lancer les tests ou l’application en local, **vous devez avoir MongoDB installé sur votre machine** (téléchargeable ici : https://www.mongodb.com/try/download/community).
>
> Vérifiez l’installation avec :
> ```powershell
> mongosh --version
> ```
> ou
> ```powershell
> mongod --version
> ```
>
> Ensuite, ouvrez un terminal MongoDB (`mongosh` ou `mongo`), puis exécutez :
> 
> ```js
> use erp
> db.roles.insertOne({ name: "ROLE_USER" })
> ```
>
> Sinon, certains tests d’authentification échoueront avec l’erreur `Role not found`.

### Évolutions futures prévues

- Ajout de modules métier pour un ERP complet (gestion clients, produits, commandes, facturation...)
- Développement d’une interface frontend moderne (React, Angular ou Vue.js)

### Technologies utilisées

- Java 21, Spring Boot, Spring Security
- MongoDB Atlas (base cloud)
- Docker (conteneurisation)
- GitHub Actions (CI/CD)
- SonarQube (analyse qualité code)
- Maven (gestion de projet et build)

---

## Documentation de l’API

La documentation complète des endpoints est disponible dans [docs/API.md](docs/API.md).



