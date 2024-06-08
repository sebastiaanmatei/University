import React, { useState, useEffect } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import './LoginForm.css';
import axios from 'axios';

const LoginForm = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    document.title = 'Task Planner';
  }, []);

  const handleMan = (e) => {
    navigate('/signupmanager');
  }

  const handleSubmit = (e) => {
    e.preventDefault();

    const userData = {
      username,
      password,
    };

    axios
      .post('http://localhost:8080/users/login', userData)
      .then((response) => {
        if (response.data.success) {
          const userType = response.data.userType;
          const userId = response.data.user.id;
          const userxx = response.data.user;

          navigate(`/${userType}/${userId}`, { state: { userxx } });
        } else {
          alert('Incorrect username or password. Please try again.');
        }
      })
      .catch((error) => {
        alert(error);
      });
  };

  return (
    <div className="login-form-container">
      <button className='redman' onClick={handleMan}>Become a Manager</button>
      <button className='expl'>Explore</button>

      <form className="login-form" onSubmit={handleSubmit}>
        <text className='x'>TaskPlanner</text>
        <div className="form-group">
          <text className='det'>Username</text>
          <input
            className='inus'
            type="username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <text className='det2'>Password</text>
          <input
            className='inpass'
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button className="loginbtn" type="submit">
          Login
        </button>
        <p className="sgnlink">
          Don't have an account? <Link to="/signupworker" className='where'>Sign up here</Link>
        </p>
      </form>
    </div>
  );
};

export default LoginForm;
