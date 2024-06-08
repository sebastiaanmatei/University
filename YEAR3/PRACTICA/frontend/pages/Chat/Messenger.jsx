// Inside Messenger.js

import React, {useState, useEffect} from 'react';
import './Messenger.css';
import Conversation from './Conversation';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCog, faUser } from '@fortawesome/free-solid-svg-icons';
import { faPaperPlane } from '@fortawesome/free-solid-svg-icons';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';

function Messenger(props) {

  const { conversation, user } = props;
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
    <div className="messenger-container">
        <div className='upbar5'>
            <div className='wfl'>
            <FontAwesomeIcon icon={faUser} className='uuu2'/> {/* Search icon */}
              </div>  
              <text className='cnv'>{name != null ? name : 'user'}</text>

            <text className='avl'>
              available
            </text>
            <button className='settings2'>
              <FontAwesomeIcon icon={faCog} className='setic'/>
            </button>
            <div className="dropdown-content2">
                <button className="profile-button">
                <text className="alabel"> View Profile</text>
                </button>
                {/* <button className="advanced-button"><text className="alabel"> Advanced</text></button> */}
                <button className="Block"><text className="blockuser"> Block user</text></button>
            </div>
        </div>

        <Conversation id={user.id}/>


        <div className='messbar2'>
        <input className='gunoiinfect'
            placeholder='Write something...'
            value={message}
            onChange={(e) => setMessage(e.target.value)}/>
            
            <button className='send2' onClick={handleSendMessage}>
                <FontAwesomeIcon icon={faPaperPlane} />
            </button>
        </div>
      
      
    </div>
  );
}

export default Messenger;
