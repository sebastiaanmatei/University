// Inside Sidebar.js

import React from 'react';
import './Sidebar2.css';
import UsersList from './Chat/UsersList';
// import UsersList from './UsersList';

function Sidebar2(props) {

  const { user } = props;
  // const locationvw = useLocation();

  localStorage.setItem('userData4', JSON.stringify(user));

  const savedUserDataW = JSON.parse(localStorage.getItem('userData4')) || user;
  // const [usergolf, setUsergolf] = useState(savedUserDataW);

  const username = user ? user.username : 'Guest'; // Provide a default value if user is undefined


  return (
    <div className="sidebar-container2">
      {/* Sidebar content */}
      <div className='profile'>
        <img className='profile_pic'></img>
        <div className='online'></div>
        <text className='username'>{username}</text>
        
        
      </div>
      <input className='sbar'
            placeholder='search user'></input>
      <UsersList user={user}/>
    </div>
  );
}

export default Sidebar2;
