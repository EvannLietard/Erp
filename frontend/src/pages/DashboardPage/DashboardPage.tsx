import React from "react";
import "./DashboardPage.css";
import { Sidebar, Footer } from "../../components";
import { useNavigate } from "react-router-dom";

const DashboardPage: React.FC = () => {
  const navigate = useNavigate();
  return (
    <>
      <div className="dashboard-layout">
        <Sidebar
          page="dashboard"
          onNavigate={(page) => {
            if (page === "home") navigate("/");
          }}
        />
        <main className="dashboard-main">
          <div className="dashboard-card">
            <h2 className="dashboard-title">Bienvenue !</h2>
            <p className="dashboard-text">
              Vous êtes bien sur votre espace personnel.
            </p>
          </div>
        </main>
      </div>
      <Footer />
    </>
  );
};

export default DashboardPage;
