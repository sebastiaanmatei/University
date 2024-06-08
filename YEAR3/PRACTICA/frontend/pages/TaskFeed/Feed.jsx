import React, { useState, useEffect } from 'react';
import './Feed.css';
import axios from 'axios';
import qs from 'qs';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';


function ContactList({ userId, userSkills, tasksUpdated }) {
  const [tasks, setTasks] = useState([]);
  const [updateTrigger, setUpdateTrigger] = useState(false);
  const [stompClient, setStompClient] = useState(null);

  // Function to fetch tasks from the server
  const fetchTasks = async () => {
    try {
      const url = `http://localhost:8080/users/filteredTasks?skills=${userSkills.join('&skills=')}`;
      const response = await axios.get(url);
      setTasks(response.data);
    } catch (error) {
      alert(error);
    }
  };

  // Call the fetchTasks function when the component mounts
  useEffect(() => {
    fetchTasks();

    const socket = new SockJS('http://localhost:8080/app/ws');
    const newStompClient = Stomp.over(socket);

    newStompClient.connect({}, () => {
      setStompClient(newStompClient);
      stompClient.subscribe('/topic/accTask', () => {
        fetchTasks();
      });
    });
    return () => {
    };
  }, []);

  useEffect(() => {
    if (tasksUpdated) {
      fetchTasks();
      setUpdateTrigger(false);
    }
  }, [tasksUpdated]);

  const toggleDescription = (taskId) => {
    const updatedTasks = tasks.map((task) => {
      if (task.id === taskId) {
        return { ...task, isExpanded: !task.isExpanded };
      }
      return { ...task, isExpanded: false };
    });
    setTasks(updatedTasks);
  };


  const acceptTask = async (taskId, taskName) => {
    try {

      stompClient.send('/app/acceptTask', {}, taskId+','+userId);
    } catch (error) {
      alert(error);
    }
  };


  return (
    <div className="contact-list-container">
      <text className='fdd'>Task Feed</text>
      <div className="contact-list">
        {tasks.map((contact) => (
          <div
            key={contact.id}
            className={`contact-card ${contact.isExpanded ? 'expanded-card' : ''}`}
          >
            <h2>{contact.title}</h2>
            <text className='skr'>Skill required: {contact.skillRequired}</text>
            <p>{contact.description}</p>
            <button className="accept-button" onClick={() => acceptTask(contact.id, contact.title)}>
              <text className="textAccept">Accept Task</text>
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default ContactList;
