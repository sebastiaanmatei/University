import React, { useState, useEffect } from 'react';
import './Manager.css';
import Messenger from './Chat/Messenger';
import Sidebar from './Chat/Sidebar';
import { useNavigate, useLocation } from 'react-router-dom';
import { faComment } from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';

function Manager() {
  const locationvv = useLocation();
  const navigatex = useNavigate();
  const [conversation, setConversation] = useState(null);



  const [stompClient, setStompClient] = useState(null);
  // let newStompClient = null;

  useEffect(() => {
    const socket = new SockJS('http://localhost:8080/app/ws');

    const newStompClient = Stomp.over(socket);

    newStompClient.connect({}, () => {
      // alert('WebSocket connection established');
      // setStompClient(newStompClient);
      // newstompClient.subscribe('/topic/accTask', () => {

      // });
    });
    return () => {
      // if (stompClient) {
      //   stompClient.disconnect();
      // }
    };
  }, []);
  



  const handleSubscribe = (message) => {
    alert("Subscribed to /topic/conversations");
    const receivedConversation = JSON.parse(message.body);
    alert("a primit convs:");
    alert(receivedConversation);
    setConversation(receivedConversation);
  };

  const renderChatComponents = stompClient !== null;

  
  // ...
  



  if (locationvv.state != null) {
    localStorage.setItem('userData', JSON.stringify(locationvv.state.userxx));
  }
  // if(stompClient != null){
  //   localStorage.setItem('clientstomp', JSON.stringify(stompClient));

  // }

  const savedUserData = JSON.parse(localStorage.getItem('userData'));
  const [userxx, setUserxx] = useState(savedUserData || null);

  

  const [titleT, setTitleT] = useState('');
  const [skillT, setSkillT] = useState('');
  const [descT, setDescT] = useState('');

  function generateRandomId(length) {
    let result = '';
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    const charactersLength = characters.length;
    for (let i = 0; i < length; i++) {
      const randomIndex = Math.floor(Math.random() * charactersLength);
      result += characters.charAt(randomIndex);
    }
    return result;
  }

  const handleAddTask = () => {
    if (!titleT || !skillT || !descT) {
      alert('Please fill in all task details.');
      return;
    }

    const taskInfo = {
      id: generateRandomId(20),
      title: titleT,
      description: descT,
      idManager: userxx.id,
      skillRequired: skillT,
    };

    axios
      .post('http://localhost:8080/users/newTask', taskInfo) // Updated URL
      // .then(() => {})
      .catch((error) => {
        alert('An error occurred while adding the task: ' + error.message);
      });

          alert("task added");
          setTitleT('');
          setSkillT('');
          setDescT('');


          stompClient.send('/app/newTaskUpdates', {}, {});
          alert("sent updates");    
      

    };

  const handleLogout = () => {
    navigatex('/');
  };

  return (
    <div className="manager-window-container">
      <div className="manager-window">
        <div className="above-card2">
          <FontAwesomeIcon icon={faComment} className="cmmm" size="xs" />
          <h1 className="t3">Messages</h1>
          <button className="lgtt" onClick={handleLogout}>
            Logout
          </button>
        </div>
        <h1 className="t1">TaskPlanner</h1>
        <div className="left-panel">
          <div className="left-panel-cardw">
            <h1 className="infot">Add a new task below!</h1>
            <form onSubmit={handleAddTask}>
              <div className="form-group">
                <label className="labelTitle">Title</label>
                <input
                  className="field"
                  placeholder="enter here..."
                  type="text"
                  name="Title"
                  value={titleT}
                  onChange={(e) => setTitleT(e.target.value)}
                  required
                />
              </div>
              <div className="form-group">
                <label className="labelSkill">Skill Required</label>
                <input
                  className="field2"
                  placeholder="enter here..."
                  type="text"
                  name="SkillRequired"
                  value={skillT}
                  onChange={(e) => setSkillT(e.target.value)}
                  required
                />
              </div>
              <div className="form-group">
                <label className="labelDesc">Description</label>
                <textarea
                  className="tarea"
                  type="text"
                  name="Description"
                  value={descT}
                  onChange={(e) => setDescT(e.target.value)}
                  required
                />
              </div>
              <button className="addTaskBtn" type="submit">
                Add Task
              </button>
            </form>
          </div>
        </div>
        <div className="right-panel">
        <Sidebar user={userxx} stompClient={stompClient} />
              <Messenger conversation={conversation} user={userxx}/>
        </div>
      </div>
    </div>
  );
}

export default Manager;