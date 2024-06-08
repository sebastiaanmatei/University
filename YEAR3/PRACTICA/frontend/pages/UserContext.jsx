// UserContext.js
import { createContext, useContext, useState } from 'react';

export const UserContext = createContext();

export const UserProvider = ({ children }) => {
  const [userxx, setUserxx] = useState(null);

  return (
    <UserContext.Provider value={{ userxx, setUserxx }}>
      {children}
    </UserContext.Provider>
  );
};