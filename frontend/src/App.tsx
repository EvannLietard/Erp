import { useState, useRef } from 'react';
import { Navbar, Footer } from './components';
import { HomePage, FeaturesPage } from './pages';
import './App.css';

function App() {
  const [page, setPage] = useState<'home' | 'features'>('home');

  // Création des refs pour chaque section de la HomePage
  const featuresRef = useRef<HTMLDivElement>(null);
  const advantagesRef = useRef<HTMLDivElement>(null);
  const clientsRef = useRef<HTMLDivElement>(null);

  // Fonctions de scroll fluide
  const scrollToFeatures = () => featuresRef.current?.scrollIntoView({ behavior: 'smooth' });
  const scrollToAdvantages = () => advantagesRef.current?.scrollIntoView({ behavior: 'smooth' });
  const scrollToClients = () => clientsRef.current?.scrollIntoView({ behavior: 'smooth' });

  return (
    <>
      <Navbar
        onHomeClick={() => setPage('home')}
        page={page}
        onFeaturesClick={page === 'home' ? scrollToFeatures : undefined}
        onAdvantagesClick={page === 'home' ? scrollToAdvantages : undefined}
        onClientsClick={page === 'home' ? scrollToClients : undefined}
      />
      {page === 'home' ? (
        <HomePage
          onShowAllFeatures={() => setPage('features')}
          featuresRef={featuresRef}
          advantagesRef={advantagesRef}
          clientsRef={clientsRef}
        />
      ) : (
        <main style={{ minHeight: '100vh' }}>
          <FeaturesPage />
          <div style={{ textAlign: 'center', margin: '2rem' }}>
            <button className="cta-button" onClick={() => setPage('home')}>
              Retour à l'accueil
            </button>
          </div>
        </main>
      )}
      <Footer />
    </>
  );
}

export default App;
