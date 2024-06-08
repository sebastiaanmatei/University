import React, { useEffect, useState } from 'react';
import './UsersList.css';
import User from './User';
import axios from 'axios';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';

function UsersList(props) {
    const { user } = props;
    const random = '653f5fac40c0d845dbc8ceb2';
    const [newStompClient, setNewStompClient] = useState(null);
    const [conversations, setConversations] = useState([]);
    const [stompClient, setStompClient] = useState(null);


    useEffect(() => {
        const socket = new SockJS('http://localhost:8080/app/ws');
        const newStompClient = Stomp.over(socket);

        newStompClient.connect({}, () => {
            setStompClient(newStompClient);
            newStompClient.subscribe('/topic/updatecc', (message) => {
                // alert(message);
                fetchConversations();
              });

              newStompClient.subscribe('/topic/accTask', (message) => {
                fetchConversations();
              });  
        });

        fetchConversations();

        return () => {
        };
    }, [user]);

    const fetchConversations = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/users/filteredConversations?id=${user.id}`);
            setConversations(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    const requestConversation = (conversationId) => {
        // Here, you should use the provided userId instead of '653f5fac40c0d845dbc8ceb2'
        stompClient.send('/app/fetchConversation', {}, conversationId+','+user.id);
        // stompClient.send('/app/fetchUserConv', {}, conversationId + ',' + user.id);

      };

    return (
        <div className="user-list" >
            {conversations.map((conversation, index) => (
                <User
                    key={index}
                    username={conversation.username}
                    // lastMessage={conversation.lastMessage}
                    lastMessage="available"
                    onClick={() => requestConversation(conversation.idConversation)}
                />
            ))}
        </div>
    );
}

export default UsersList;
