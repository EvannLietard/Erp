import React, { useState } from "react";
import "./Sidebar.css";
import "../../App.css";

interface SidebarProps {
  page: string;
  onNavigate: (page: string) => void;
}

export const Sidebar: React.FC<SidebarProps> = ({ page, onNavigate }) => {
  const [open, setOpen] = useState(true);

  return (
    <>
      <button className="sidebar-toggle" aria-label={open ? "Réduire le menu" : "Ouvrir le menu"} onClick={() => setOpen(!open)} style={{left: open ? 18 : 10, position: 'absolute'}}>
        <span className="sidebar-burger">☰</span>
      </button>
      <nav className={`sidebar${open ? " open" : " closed"}`}>
        <button className="sidebar-logo" aria-label="Accueil" onClick={() => onNavigate('home')} style={{background: 'none', border: 'none', boxShadow: 'none', padding: 0, margin: 0}}>
          <span className="sidebar-nom">Nova<span style={{ color: '#60a5fa' }}>ERP</span></span>
        </button>
      </nav>
    </>
  );
};
