import React from 'react';
import LoginForm from './pages/LoginForm';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Admin from './pages/Admin';
import Manager from './pages/Manager';
import Worker from './pages/Worker';
import Signupform from './pages/Signupform';

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/worker/:userId" element={<Worker />} />
          <Route path="/manager/:userId" element={<Manager />} />
          <Route path="/" element={<LoginForm />} />
          <Route path="/admin" element={<Admin />} />
          <Route path="/manager" element={<Manager />} />
          <Route path="/worker" element={<Worker />} />
          <Route path="/signupworker" element={<Signupform />} />
          
        </Routes>
      </div>
    </Router>
  );
}

export default App;
