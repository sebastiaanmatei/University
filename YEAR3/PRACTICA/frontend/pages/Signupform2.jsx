// SignupForm.js

import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import './Signupform2.css';

const Signupform2 = () => {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();

    // Perform signup logic here

    // After successful signup, navigate to the login page
    navigate('/');
  };

  return (
    <div className="signup-form-container">
      <form className="signup-form" onSubmit={handleSubmit}>
        <h2>Sign Up</h2>
        <div className="form-group">
          <text className="det1">Username</text>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
            className="inus2"
          />
        </div>
        <div className="form-group">
          <text className="det4">Email</text>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            className="email2"
          />
        </div>
        <div className="form-group">
          <text className="det3">Password</text>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
            className="pass2"
          />
        </div>
        <button className="signupbtn" type="submit">
          Sign Up
        </button>
        <p className="signup-link">
          Already have an account? <Link to="/" className='where'>Sign in here</Link>
        </p>
      </form>
    </div>
  );
};

export default Signupform2;
