import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './Admin.css';
import axios from 'axios';
import io from 'socket.io-client';


const Admin = () => {
//   const history = useHistory();


  const navigate = useNavigate();
  const [userType, setUserType] = useState('Worker');
  const [formData, setFormData] = useState({
    username: '',
    email: '',
    password: '',
    availability: '',
    company: '',
    skills: '',
  });

  // // Create a WebSocket connection when the component is mounted
  // useEffect(() => {
  //   // Connect to the WebSocket server
  //   const socket = io('http://localhost:8080/websocket-endpoint');

  //   // Handle incoming messages from the server
  //   socket.on('message', (data) => {
  //     console.log('Received message from server:', data);
  //     // Process the received data as needed
  //   });

  //   // Clean up the WebSocket connection when the component unmounts
  //   return () => {
  //     socket.disconnect();
  //   };
  // }, []); // Empty dependency array means this effect runs once after initial render

  const handleLogout = () => {
    // Implement logout functionality here (e.g., clear session, redirect to login)
    navigate('/');
  };

  const handleUserTypeChange = (e) => {
    setUserType(e.target.value);
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleAddUser = () => {
    // Implement user addition logic here (e.g., send data to server)

    let userData = {
        username: formData.username,
        email: formData.email,
        password: formData.password,
        // Other fields
      };
      

      if (userType === 'Worker') {
        // Add fields specific to Worker
        userData = {
          ...userData,
          availability: formData.availability,
          skills: formData.skills,
        };

        // Make the POST request to your Spring Boot API
        axios.post('http://localhost:8080/users/newWorker', userData)
        .then((response) => {
            console.log(response.data);
            // Optionally, handle success or show a success message
        })
        .catch((error) => {
            console.error(error);
            // Handle error or show an error message
        });

        // Send the user data to the WebSocket server
        // const socket = io('http://localhost:8080/websocket-endpoint');
        // socket.emit('/newWorker', userData);
        // console.log('Form Data:', formData);



      } else if (userType === 'Manager') {
        // Add fields specific to Manager
        userData = {
          ...userData,
          company: formData.company,
        };



        // Make the POST request to your Spring Boot API
        axios.post('http://localhost:8080/users/newManager', userData)
        .then((response) => {
            console.log(response.data);
            // Optionally, handle success or show a success message
        })
        .catch((error) => {
            console.error(error);
            // Handle error or show an error message
        });

        // Send the user data to the WebSocket server
        // const socket = io('http://localhost:8080/websocket-endpoint');
        // socket.emit('newManager', userData);
        // console.log('Form Data:', formData);
      }
    
      



    console.log('Form Data:', formData);
  };

  return (
    <div className="admin-page">
      <div className="logout-button-container">
        <button className="logout-button" onClick={handleLogout}>
          Logout
        </button>
      </div>
      <div className="admin-card">
        <h1 className="admin-title">Add User</h1>
        <div className="user-type-container">
          <label>User Type:</label>
          <select name="userType" value={userType} onChange={handleUserTypeChange}>
            <option value="Worker">Worker</option>
            <option value="Manager">Manager</option>
          </select>
        </div>
        <div className="form-fields">
          <input
            type="text"
            name="username"
            placeholder="Username"
            value={formData.username}
            onChange={handleInputChange}
          />
          <input
            type="email"
            name="email"
            placeholder="Email"
            value={formData.email}
            onChange={handleInputChange}
          />
          <input
            type="password"
            name="password"
            placeholder="Password"
            value={formData.password}
            onChange={handleInputChange}
          />
          {userType === 'Worker' && (
            <div>
              <input
                type="text"
                name="availability"
                placeholder="Availability"
                value={formData.availability}
                onChange={handleInputChange}
              />
              <input
                type="text"
                name="skills"
                placeholder="Skills (comma-separated)"
                value={formData.skills}
                onChange={handleInputChange}
              />
            </div>
          )}
          {userType === 'Manager' && (
            <input
              type="text"
              name="company"
              placeholder="Company"
              value={formData.company}
              onChange={handleInputChange}
            />
          )}
        </div>
        <button className="add-user-button" onClick={handleAddUser}>
          Add User
        </button>
      </div>
    </div>
  );
};

export default Admin;
