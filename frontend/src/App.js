import React, { useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import Login from './components/Login';
import SignUp from './components/SignUp';
import Main from './components/Main';

function App() {
  const navigate = useNavigate();

  const [token, setToken] = useState('');

  const changeToken = (token) => {
    setToken(token);
  };

  return (
    <Routes>
      <Route path="/" element={<Login navigate={navigate} changeToken={changeToken} />} />
      <Route path="/signup" element={<SignUp navigate={navigate} />} />
      <Route path="/main" element={<Main navigate={navigate} token={token} />} />
    </Routes>
  );
}

export default App;
