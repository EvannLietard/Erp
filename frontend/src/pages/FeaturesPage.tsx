import React from "react";

const allFeatures = [
  { title: "Gestion des utilisateurs & droits", desc: "Créez, modifiez et attribuez des rôles à vos collaborateurs." },
  { title: "Gestion des clients (CRM)", desc: "Centralisez les informations clients et suivez les interactions." },
  { title: "Gestion des fournisseurs", desc: "Référencez et suivez vos partenaires et fournisseurs." },
  { title: "Gestion commerciale", desc: "Gérez devis, commandes, factures et relances simplement." },
  { title: "Gestion des stocks & inventaire", desc: "Suivi en temps réel des stocks, alertes et inventaires." },
  { title: "Achats & ventes", desc: "Optimisez vos processus d’achat et de vente." },
  { title: "Comptabilité & finances", desc: "Automatisez la gestion comptable et le suivi financier." },
  { title: "Ressources humaines (RH)", desc: "Gérez la paie, les congés, les absences et les dossiers RH." },
  { title: "Gestion de projets", desc: "Planifiez, suivez et collaborez sur vos projets d’entreprise." },
  { title: "Production (GPAO)", desc: "Pilotez la production, les ordres de fabrication et la traçabilité." },
  { title: "Tableaux de bord & reporting", desc: "Visualisez vos indicateurs clés et générez des rapports personnalisés." },
  { title: "Gestion documentaire", desc: "Stockez, partagez et sécurisez vos documents d’entreprise." },
  { title: "Service après-vente (SAV)", desc: "Gérez les interventions, tickets et le support client." },
  { title: "Gestion des temps & pointage", desc: "Suivi des temps de travail, pointage et absences." },
  { title: "Multi-sites & multi-sociétés", desc: "Administrez plusieurs entités ou sites depuis une seule plateforme." },
];

const FeaturesPage: React.FC = () => {
  return (
    <div className="features-page-container" style={{padding: '3rem 1rem', maxWidth: 1100, margin: '0 auto'}}>
      <h1>Liste complète des fonctionnalités</h1>
      <div className="features-grid">
        {allFeatures.map((feature) => (
          <div className="feature-card" key={feature.title}>
            <h3>{feature.title}</h3>
            <p>{feature.desc}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default FeaturesPage;
