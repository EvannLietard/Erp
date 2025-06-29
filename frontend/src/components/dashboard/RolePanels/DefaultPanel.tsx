import React from "react";

const DefaultPanel: React.FC = () => (
  <div className="dashboard-card">
    <h2 className="dashboard-title">Accès refusé</h2>
    <p className="dashboard-text">
      Vous n'avez pas les droits nécessaires pour accéder à cette page. <br />
      Merci de contacter l'administrateur.
    </p>
  </div>
);

export default DefaultPanel;
