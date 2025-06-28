import { useState } from 'react';
import { Navbar, Footer } from './components';
import { HomePage, FeaturesPage } from './pages';
import './App.css';

function App() {
  const [page, setPage] = useState<'home' | 'features'>('home');

  return (
    <>
      <Navbar onHomeClick={() => setPage('home')} page={page} />
      {page === 'home' ? (
        <HomePage onShowAllFeatures={() => setPage('features')} />
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
