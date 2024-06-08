import React from 'react';
import { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom'; // Import useLocation from react-router-dom

import './Sidebar.css';
import UsersList from './UsersList';
import { faSearch, faPlus, faUser } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
// import axios from 'axios';
// import axios from 'axios/dist/axios';



function Sidebar(props) {

  const { user, stompClient } = props;
  const locationvw = useLocation();
  // alert(stompClient === null);

  localStorage.setItem('userData4', JSON.stringify(user));

  const savedUserDataW = JSON.parse(localStorage.getItem('userData4')) || user;
  const [uservwx, setUservwx] = useState(savedUserDataW);

  // alert('sidebar');
  // alert(user.id);
  // alert(user.username);
  // console.log(user.id);
  const username = user ? user.username : 'Guest'; // Provide a default value if user is undefined
  return (
    <div className="sidebar-container">
      {/* Sidebar content */}
      <div className='profile2'>
        <div className='profile_pic2'>
        <FontAwesomeIcon icon={faUser} className='uuu'/> {/* Search icon */}
        </div>
        <div className='online'></div>
        <text className='username2'>{username}</text>
        {/* <FontAwesomeIcon icon={faPlus} className='searc'/> {}
        <FontAwesomeIcon icon={faSearch} className='searc2'/> Search icon */}
        
        
      </div>
      
      <input className='sbar2'
      
            placeholder='Search...'>

            
            </input>
      <UsersList user={uservwx} stompClient={stompClient}/>

      <div className='support'>
        <button className='customize'>
          Customize your chat
        </button>
        <button className='reachout'>
          Privacy and support
        </button>
      </div>
    </div>
  );
}

export default Sidebar;