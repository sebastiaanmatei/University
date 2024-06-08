// Message.js
import React from 'react';
import './Message.css';
import moment from 'moment';

function formatTime(isoTime) {
  const formattedTime = moment(isoTime).format('HH:mm');
  return formattedTime;
}

function Message({ content, isMyMessage, time }) {
  const timeSubstring = time.substring(11, 16); // Extract '12:00'
  const messageClass = isMyMessage ? 'my-message' : 'other-message';

  return (
    <div className={`message-container ${messageClass}`}>
      <div className="time">{timeSubstring}</div>
      <div className="message-content">{content}</div>
    </div>
  );
}

export default Message;
