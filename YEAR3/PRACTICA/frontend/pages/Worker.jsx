import React, { useState, useEffect } from 'react';
import './Worker.css';
import Feed from './TaskFeed/Feed';
import { useNavigate, useLocation } from 'react-router-dom';
import Sidebar2 from './Sidebar2';
import Mess2 from './Mess2';
import SockJS from 'sockjs-client';
import {Stomp} from '@stomp/stompjs';
import {Client} from '@stomp/stompjs';
import io from 'socket.io-client';


function Worker() {


  const [tasksUpdated, setTasksUpdated] = useState(false);

    // const socket = new SockJS('http://localhost:8080/ws')
    // const client = Stomp.over(socket);

    useEffect(() => {

      const socket = new SockJS('http://localhost:8080/app/ws')
      const client = Stomp.over(socket);
      client.connect({}, () => {
        alert('Connected to WebSocket');

        client.subscribe('/topic/tasksUpdated', () => {
          // Handle the task update received from the server
          alert('New task updates! Refresh page');
          setTasksUpdated(true);
        });
      });

      return () => {
        client.disconnect();
      };
    }, []);



    const locationvw = useLocation();
    const navigatevw = useNavigate();

    if(locationvw.state != null){
        localStorage.setItem('userData3', JSON.stringify(locationvw.state.userxx));
    }


    const savedUserDataW = JSON.parse(localStorage.getItem('userData3'));
    const [uservw, setUservw] = useState(savedUserDataW || null);

    const handleLogout = () => {
        // Implement logout functionality here (e.g., clear session, redirect to login)
        navigatevw('/');
      };

    return (
        <div className="worker-window-container">
        <title>TaskPlanner</title>
        <div className="worker-window">
        <button className="view-profile-button" onClick={handleLogout}>Log out</button>
        
            <h1 className="texts2">powered by TaskPlanner</h1>
            <div className="left-panel-card">
                <h1 className="texts77">Task Feed</h1>
                <Feed userId={uservw ? uservw.id : 'noid'} userSkills={uservw ? uservw.skills : []} tasksUpdated={tasksUpdated} />
            </div>

            <div className="right-panel">
                <Sidebar2 user={uservw}/>
                <Mess2 user={uservw}/>
            </div>
        </div>
        </div>
    );
}

export default Worker;
