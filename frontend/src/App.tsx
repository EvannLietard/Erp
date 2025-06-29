import { useRef } from 'react';
import { BrowserRouter as Router, Routes, Route, useNavigate } from 'react-router-dom';
import { Navbar, Footer } from './components';
import { HomePage, FeaturesPage } from './pages';
import AuthPage from './pages/AuthPage/AuthPage';
import DashboardPage from './pages/DashboardPage/DashboardPage';
import { AuthProvider } from './context/AuthContext';
import './App.css';

function AppRoutes() {
  const featuresRef = useRef<HTMLDivElement>(null);
  const advantagesRef = useRef<HTMLDivElement>(null);
  const clientsRef = useRef<HTMLDivElement>(null);
  const navigate = useNavigate();

  // Fonctions de scroll fluide
  const scrollToFeatures = () => featuresRef.current?.scrollIntoView({ behavior: 'smooth' });
  const scrollToAdvantages = () => advantagesRef.current?.scrollIntoView({ behavior: 'smooth' });
  const scrollToClients = () => clientsRef.current?.scrollIntoView({ behavior: 'smooth' });

  // Détecte la route courante
  const currentPath = window.location.pathname;
  let navbarPage: 'home' | 'features' | 'login' | undefined = undefined;
  if (currentPath === '/') navbarPage = 'home';
  else if (currentPath === '/features') navbarPage = 'features';
  else if (currentPath === '/login') navbarPage = 'login';

  return (
    <>
      {currentPath !== '/dashboard' && (
        <Navbar
          onHomeClick={() => navigate('/')}
          page={navbarPage}
          onFeaturesClick={navbarPage === 'home' ? scrollToFeatures : undefined}
          onAdvantagesClick={navbarPage === 'home' ? scrollToAdvantages : undefined}
          onClientsClick={navbarPage === 'home' ? scrollToClients : undefined}
          onLoginClick={() => navigate('/login')}
        />
      )}
      <Routes>
        <Route
          path="/"
          element={
            <HomePage
              onShowAllFeatures={() => navigate('/features')}
              featuresRef={featuresRef}
              advantagesRef={advantagesRef}
              clientsRef={clientsRef}
              onStartClick={() => navigate('/login')}
            />
          }
        />
        <Route path="/features" element={<FeaturesPage />} />
        <Route path="/login" element={<AuthPage />} />
        <Route path="/dashboard" element={<DashboardPage />} />
      </Routes>
      {currentPath !== '/dashboard' && <Footer />}
    </>
  );
}

function App() {
  return (
    <Router>
      <AuthProvider>
        <AppRoutes />
      </AuthProvider>
    </Router>
  );
}

export default App;
