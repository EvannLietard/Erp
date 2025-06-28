import { useState } from 'react';
import { Navbar } from './components/Navbar';
import Footer from './components/Footer';
import HomePage from './pages/HomePage';
import FeaturesPage from './pages/FeaturesPage';
import './App.css';

function App() {
  const [page, setPage] = useState<'home' | 'features'>('home');

  return (
    <>
      <Navbar onHomeClick={() => setPage('home')} page={page} />
      {page === 'home' ? (
        <HomePage onShowAllFeatures={() => setPage('features')} />
      ) : (
        <>
          <FeaturesPage />
          <div style={{ textAlign: 'center', margin: '2rem' }}>
            <button className="cta-button" onClick={() => setPage('home')}>
              Retour à l'accueil
            </button>
          </div>
        </>
      )}
      <Footer />
    </>
  );
}

export default App;
