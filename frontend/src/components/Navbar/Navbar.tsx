import React from "react";
import "./Navbar.css";
import logoEntrepriseRectangle from "../../assets/LogoEntrepriseRectangle.png";

export const Navbar: React.FC<{ onHomeClick?: () => void; page?: 'home' | 'features' }> = ({ onHomeClick, page }) => (
  <nav className="navbar">
    <button
      className="navbar-logo"
      style={{cursor: onHomeClick ? 'pointer' : 'default', background: 'none', border: 'none', padding: 0, font: 'inherit', fontWeight: 800, fontSize: '2rem', color: '#6366f1'}}
      onClick={onHomeClick}
      tabIndex={0}
      aria-label="Retour à l'accueil"
    >
      Nova<span style={{color: '#60a5fa'}}>ERP</span>
    </button>
    <ul className="navbar-links">
      <li><a href="#features">Fonctionnalités</a></li>
      {page === 'home' && <li><a href="#advantages">Avantages</a></li>}
      {page === 'home' && <li><a href="#clients">Clients</a></li>}
    </ul>
    <button className="navbar-login">Connexion</button>
  </nav>
);
