// Inside Messenger.js

import React, {useState, useEffect} from 'react';
import './Mess2.css';
import Conversation from './Chat/Conversation';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCog } from '@fortawesome/free-solid-svg-icons';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';

function Mess2(props) {

  const { user } = props;

  const [name, setname]=useState(null);
  const [idConv, setcnvvv]=useState(null);
  const [stompClient255, setStompClient255] = useState(null);
  const [message, setMessage] = useState(''); // State variable to track the message


  useEffect(() => {
    const socket255 = new SockJS('http://localhost:8080/app/ws');
    const newStompClient255 = Stomp.over(socket255);

    newStompClient255.connect({}, () => {
      setStompClient255(newStompClient255);

      newStompClient255.subscribe('/topic/userconv', (message) => {
        console.log('rahaturi');

        const receivedUser55 = message.body; 
        const [username, idConversation] = receivedUser55.split(',');
        setname(username);
        setcnvvv(idConversation);

      });
      
    });

    return () => {
      if (stompClient255) {
        stompClient255.disconnect();
      }
    };
  }, []);


  const handleSendMessage = () => {
    if (stompClient255 && message) {
      const messageData = {
        idSender: user.id,
        idConversation: idConv,
        content: message,
        timestamp: new Date().toISOString(),
      };

      stompClient255.send('/app/sendMessage', {}, JSON.stringify(messageData));
      setMessage('');
    }
  };




  
  return (
    <div className="messenger-container2">
        <div className='upbar'>
            <img className='worker_profile'></img>  
            <text className='converser'>{name}</text>
            <text className='availability'>
              available
            </text>
            <button className='settings2'>
              <FontAwesomeIcon icon={faCog} className='ic2'/>
            </button>
            <div className="dropdown-content">
                <button className="profile-button">
                <text className="alabel"> View Profile</text>
                </button>
                {/* <button className="advanced-button"><text className="alabel"> Advanced</text></button> */}
                <button className="Block"><text className="blockuser"> Block user</text></button>
            </div>
        </div>

        <Conversation id={user.id}/>


        <div className='messbar'>
        <input className='field9'
            placeholder='Write something...'
            value={message}
            onChange={(e) => setMessage(e.target.value)}/>
            
            <button className='send' onClick={handleSendMessage}>
                {/* <FontAwesomeIcon icon={faPaperPlane} /> */}
            </button>
        </div>
      
      
    </div>
  );
}

export default Mess2;
