// User.js

import React from 'react';
import './User.css';
import { faUser } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

function User(props) {
  const userImage2='https://static.vecteezy.com/system/resources/previews/005/129/844/non_2x/profile-user-icon-isolated-on-white-background-eps10-free-vector.jpg';
  const { userImage, username, isOnline, lastMessage, onClick } = props;

  return (
    <div className="user-item" onClick={onClick}>
      <div className="user-pic2">
        <FontAwesomeIcon icon={faUser} className='uuu2'/> {/* Search icon */}
      </div>
      {/* <div className={`user-status ${isOnline ? 'online' : 'offline'}`}></div> */}
      <div className="user-info">
        <div className="user-username">{username}</div>
        <div className="user-last-message">{lastMessage}</div>
      </div>
    </div>
  );
}

export default User;
