import React from "react";
import "../App.css";
import logoEntreprise from "../assets/LogoEntrepiseCarrée.png";
import logoEntrepriseRectangle from "../assets/LogoEntrepriseRectangle.png";

const clientLogos = [
  logoEntreprise,
  logoEntreprise,
  logoEntreprise,
];

const advantages = [
  { icon: "💡", title: "Innovant", desc: "Technologies de pointe et évolutives." },
  { icon: "⚡", title: "Rapide", desc: "Performance optimale et réactivité." },
  { icon: "🔒", title: "Sécurisé", desc: "Protection avancée de vos données." },
  { icon: "🤝", title: "Support", desc: "Assistance dédiée et personnalisée." },
];

const HomePage: React.FC<{ onShowAllFeatures?: () => void }> = ({ onShowAllFeatures }) => {
  return (
    <div className="homepage-container">
      <header className="hero-section">
        <div className="hero-content">
          <h1>Bienvenue sur votre ERP Moderne</h1>
          <p>Gérez votre entreprise efficacement avec une interface intuitive, rapide et sécurisée.</p>
          <button className="cta-button">Démarrer <span className="cta-icon">→</span></button>
        </div>
        <div className="hero-image">
          <img src={logoEntreprise} alt="Logo Entreprise" />
        </div>
      </header>
      <section className="features-section" id="features">
        <h2>Fonctionnalités clés</h2>
        <div className="features-grid">
          <div className="feature-card">
            <h3>Gestion des utilisateurs & droits</h3>
            <p>Créez, modifiez et attribuez des rôles à vos collaborateurs.</p>
          </div>
          <div className="feature-card">
            <h3>Gestion des clients (CRM)</h3>
            <p>Centralisez les informations clients et suivez les interactions.</p>
          </div>
          <div className="feature-card">
            <h3>Gestion commerciale</h3>
            <p>Gérez devis, commandes, factures et relances simplement.</p>
          </div>
          <div className="feature-card">
            <h3>Comptabilité & finances</h3>
            <p>Automatisez la gestion comptable et le suivi financier.</p>
          </div>
          <div className="feature-card">
            <h3>Ressources humaines (RH)</h3>
            <p>Gérez la paie, les congés, les absences et les dossiers RH.</p>
          </div>
          <div className="feature-card">
            <h3>Tableaux de bord & reporting</h3>
            <p>Visualisez vos indicateurs clés et générez des rapports personnalisés.</p>
          </div>
        </div>
        {onShowAllFeatures && (
          <div style={{textAlign: 'center', marginTop: '2rem'}}>
            <button className="cta-button" style={{fontSize: '1rem', padding: '0.7rem 2rem'}} onClick={onShowAllFeatures}>
              Voir toutes les fonctionnalités
            </button>
          </div>
        )}
      </section>
      <section className="advantages-section" id="advantages">
        <h2>Pourquoi choisir notre ERP ?</h2>
        <div className="advantages-grid">
          {advantages.map((adv) => (
            <div className="advantage-card" key={adv.title}>
              <span className="advantage-icon">{adv.icon}</span>
              <h3>{adv.title}</h3>
              <p>{adv.desc}</p>
            </div>
          ))}
        </div>
      </section>
      <section className="clients-section" id="clients">
        <h2>Ils nous font confiance</h2>
        <div className="clients-logos">
          {clientLogos.map((logo) => (
            <img src={logo} alt="Client" key={logo} />
          ))}
        </div>
      </section>
    </div>
  );
};

export default HomePage;
