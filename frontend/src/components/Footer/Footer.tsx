import React from "react";
import logoEntrepriseRectangle from "../../assets/LogoEntrepriseRectangle.png";
import "./Footer.css";

export const Footer: React.FC = () => (
  <footer className="footer">
    <div className="footer-content">
      <div style={{display: 'flex', alignItems: 'center', gap: '1rem'}}>
        <img src={logoEntrepriseRectangle} alt="Logo NovaERP" style={{height: '32px', width: 'auto'}} />
        <span>© {new Date().getFullYear()} NovaERP. Tous droits réservés.</span>
      </div>
      <div className="footer-links">
        <button className="footer-link">Mentions légales</button>
        <button className="footer-link">Contact</button>
        <a href="https://www.linkedin.com/" target="_blank" rel="noopener noreferrer" className="footer-link">LinkedIn</a>
      </div>
    </div>
  </footer>
);
