import React, { useState } from "react";
import "./Sidebar.css";
import "../../App.css";
import { useAuth } from '../../context/AuthContext';

interface SidebarProps {
  page: string;
  onNavigate: (page: string) => void;
}

export const Sidebar: React.FC<SidebarProps> = ({ page, onNavigate }) => {
  const [open, setOpen] = useState(true);
  const { logout } = useAuth();

  return (
    <>
      <button
        className="sidebar-toggle-fixed"
        aria-label={open ? "Réduire le menu" : "Ouvrir le menu"}
        onClick={() => setOpen(!open)}
      >
        <span className="sidebar-burger">☰</span>
      </button>
      {open && (
        <nav className="sidebar open">
          <button className="sidebar-logo" aria-label="Accueil" onClick={() => onNavigate('dashboard')} style={{background: 'none', border: 'none', boxShadow: 'none', padding: 0, margin: 0}}>
            <span className="sidebar-nom">Nova<span style={{ color: '#60a5fa' }}>ERP</span></span>
          </button>
          <div style={{ flex: 1 }} />
          <button className="sidebar-logout" onClick={logout}>
            Déconnexion
          </button>
        </nav>
      )}
    </>
  );
};
