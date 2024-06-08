import React, { useState } from 'react';
import { useNavigate, Link, useParams } from 'react-router-dom';
import './Signupform.css';
import axios from 'axios';
import Select from 'react-select'; // Import react-select
import io from  'socket.io-client';

const Signupform = () => {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [availability, setAvailability] = useState('');
  const [skills, setSkills] = useState('');
  const navigate = useNavigate();
  const params = useParams(); // Initialize useParams


  const availabilityOptions = [
    {label: 'Web Development' },
    {label: 'Software Engineering' },
    {label: 'Driving' },
    {label: 'Human Resources' },
    {label: 'Sales' },
    {label: 'Design' },
    {label: 'Management' },
    {label: 'Accountability' },
    {label: 'Bank Management' },
  ];

  const customStyles = {
    control: (styles) => ({
      ...styles,
      border: '1px solid rgba(255, 255, 255, 0.3)',
      backgroundColor: 'rgba(255, 255, 255, 0.2)',
      boxShadow: '0 4px 30px rgba(0, 0, 0, 0.1)',
      color: 'white',
      width: '100%',
      marginTop: '10px',
      position: 'absolute',
      top: '365px',
      height: '50px',
    }),
    menu: (styles) => ({
      ...styles,
      position: 'absolute',
      top: '370px', // Position the menu below the control
      overflow: 'auto', // Enable scrolling
      backgroundColor: 'rgba(255, 255, 255, 1)', // Set the background color
      border: '1px solid rgba(255, 255, 255, 1)',
      boxShadow: '0 4px 30px rgba(0, 0, 0, 0.1)',
      color: 'black',
      width: '100%' // Adjust the width to match the control's width
    }),
    option: (styles, { isFocused }) => ({
      ...styles,
      backgroundColor: isFocused ? 'rgba(94, 62, 223, 0.6)' : 'transparent',
      color: 'black',
      cursor: 'pointer',
    }),
    singleValue: (styles) => ({
      ...styles,
      color: 'white',
    }),
    placeholder: (styles) => ({
      ...styles,
      color: 'rgba(255, 255, 255, 0.7)',
    }),
  };

  const handleAddUser = () => {
    let userData = {
      username: username,
      email: email,
      password: password,
      skills: skills,
      availability: availability,
      
    };

  //   axios.post('http://localhost:8080/users/newWorker', userData)
  //   .then((response) => {
  //     if (response.status === 200) {
  //       alert('bitch');
  //       const userId = response.data.userId;
  //       navigate('/worker');
  //     } else {
  //       const errorMessage = response.data.error; // Replace with the actual field name
  //       if (errorMessage) {
  //         alert(errorMessage);
  //       } else {
  //         alert('An unknown error occurred. Please try again later.');
  //       }
  //     }
  //   })
  //   .catch((error) => {
  //     console.error(error);
  //     alert('An error occurred. Please try again later.');
  //   });
  // };


  axios
      .post('http://localhost:8080/users/newWorker', userData)
      .then((response) => {
        if (response.data.success) {
          const userId = response.data.user.id;
          const userxx = response.data.user;

          navigate(`/worker/${userId}`, { state: { userxx } });
        } else {
          alert('Incorrect username or password. Please try again.');
        }
      })
      .catch((error) => {
        alert(error);
      });
  };

      // 

  return (
    <div className="signup-form-container">
      <div className="signup-form" >
      <h2>Sign Up</h2>
        <div className="form-group">
          <text className="det1111">Username</text>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
            className="inus2222"
          />
        </div>
        <div className="form-group">
          <text className="det4444">Email</text>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            className="email2222"
          />
        </div>
        <div className="form-group">
          <text className="det3">Password (at least 8 characters including digits)</text>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
            className="pass2"
          />
        </div>
        <div className="form-group">
        
          <text className="det20">Skills (comma separated)</text>
          <input
            type="availability"
            value={availability}
            onChange={(e) => setAvailability(e.target.value)}
            required
            className="ava20"
          />
        </div>
        <div className="form-group">
          <text className="det30">Availability (only 'available' or 'working') </text>
          <input
            type="skills"
            value={skills}
            onChange={(e) => setSkills(e.target.value)}
            required
            className="ski30"
          />
        </div>
        <div className='form-group'>
          {/* <select
            id="choices"
            placeholder='valid skills (no typos allowed)'
            required
            className="ava50"
            size={20} // Set the maximum number of visible options
          >
            <option>Truck Driving</option>
            <option>Web Development</option>
          </select> */}
          <Select
            options={availabilityOptions}
            placeholder="Check valid skills"
            styles={customStyles} // Apply custom styles
          />
        </div>
        <button className="signupbtn" type="submit" onClick={handleAddUser}>
          Sign Up
        </button>
        <p className="signup-link22">
          Already have an account? <Link to="/" className="where22">Sign in here</Link>
        </p>
      </div>
    </div>
  );
};

export default Signupform;
