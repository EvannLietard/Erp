import React, { useEffect, useState } from "react";
import { jwtDecode } from "jwt-decode";
import { Sidebar, Footer } from "../../components";
import { useNavigate } from "react-router-dom";
import "./DashboardPage.css";

import {
  SuperAdminPanel,
  CompanyAdminPanel,
  SiteManagerPanel,
  HrManagerPanel,
  AccountantPanel,
  PurchasingManagerPanel,
  SalesManagerPanel,
  StockManagerPanel,
  EmployeePanel,
  DefaultPanel,
} from "../../components/dashboard/RolePanels";


interface JwtPayload {
  roles: string[];
}

// Mapping rôle → composant panel
const rolePanels: Record<string, React.FC> = {
  ROLE_SUPER_ADMIN: SuperAdminPanel,
  ROLE_COMPANY_ADMIN: CompanyAdminPanel,
  ROLE_SITE_MANAGER: SiteManagerPanel,
  ROLE_HR_MANAGER: HrManagerPanel,
  ROLE_ACCOUNTANT: AccountantPanel,
  ROLE_PURCHASING_MANAGER: PurchasingManagerPanel,
  ROLE_SALES_MANAGER: SalesManagerPanel,
  ROLE_STOCK_MANAGER: StockManagerPanel,
  ROLE_EMPLOYEE: EmployeePanel,
};

const DashboardPage: React.FC = () => {
  const navigate = useNavigate();
  const [roles, setRoles] = useState<string[]>([]);

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      try {
        const decoded = jwtDecode<JwtPayload>(token);
        setRoles(decoded.roles || []);
      } catch {
        setRoles([]);
      }
    }
  }, []);

  const firstRole = roles.find((r) => r in rolePanels);
  const PanelComponent = firstRole ? rolePanels[firstRole] : DefaultPanel;

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
            <PanelComponent />
          </div>
        </main>
      </div>
      <Footer />
    </>
  );
};

export default DashboardPage;