import React, { useState, useEffect } from 'react';
import './Conversation.css';
import Message from './Message';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';

function Conversation(props) {
  const { id } = props;
  const [conv, setConv] = useState(null);
  const [stompClient2, setStompClient2] = useState(null);

  useEffect(() => {
    const socket2 = new SockJS('http://localhost:8080/app/ws');
    const newStompClient2 = Stomp.over(socket2);

    newStompClient2.connect({}, () => {
      // alert('WebSocket connection established');
      setStompClient2(newStompClient2);

      newStompClient2.subscribe('/topic/conversations', (message) => {
        // Handle the task update received from the server
        console.log('New messages received!');

        const receivedConversation = JSON.parse(message.body);
        setConv(receivedConversation);
        // onReceivedInfo(receivedConversation);
      });

      newStompClient2.subscribe('/topic/newMessages', (message) => {
        // Handle the task update received from the server
        console.log('New message sent received!');

        const receivedConversation = JSON.parse(message.body);
        setConv(receivedConversation);
        // onReceivedInfo(receivedConversation);
      });

      newStompClient2.subscribe('/topic/accTask', (message) => {
        // Handle the task update received from the server
        console.log('New message sent received!');

        const receivedConversation = JSON.parse(message.body);
        setConv(receivedConversation);
        // onReceivedInfo(receivedConversation);
      });
    });

    return () => {
      if (stompClient2) {
        stompClient2.disconnect();
      }
    };
  }, []);

  return (
    <div className="conversation">
      {conv !== null ? (
        conv.map((message, index) => (
          <Message
            key={index}
            content={message.content}
            time={message.timeStamp}
            isMyMessage={message.idSender === id} 
          />
        ))
      ) : (
        <div></div>
      )}
    </div>
  );
}

export default Conversation;
