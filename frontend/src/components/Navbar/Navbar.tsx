import React from "react";
import "./Navbar.css";
import logoEntrepriseRectangle from "../../assets/LogoEntrepriseRectangle.png";

interface NavbarProps {
  onHomeClick?: () => void;
  page?: 'home' | 'features';
  onFeaturesClick?: () => void;
  onAdvantagesClick?: () => void;
  onClientsClick?: () => void;
}

export const Navbar: React.FC<NavbarProps> = ({ onHomeClick, page, onFeaturesClick, onAdvantagesClick, onClientsClick }) => (
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
      <li>
        <a
          className="navbar-link"
          href="#features"
          onClick={e => { e.preventDefault(); onFeaturesClick && onFeaturesClick(); }}
          style={{ cursor: 'pointer' }}
        >
          Fonctionnalités
        </a>
      </li>
      {page === 'home' && (
        <li>
          <a
            className="navbar-link"
            href="#advantages"
            onClick={e => { e.preventDefault(); onAdvantagesClick && onAdvantagesClick(); }}
            style={{ cursor: 'pointer' }}
          >
            Avantages
          </a>
        </li>
      )}
      {page === 'home' && (
        <li>
          <a
            className="navbar-link"
            href="#clients"
            onClick={e => { e.preventDefault(); onClientsClick && onClientsClick(); }}
            style={{ cursor: 'pointer' }}
          >
            Clients
          </a>
        </li>
      )}
    </ul>
    <button className="navbar-login">Connexion</button>
  </nav>
);
