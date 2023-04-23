import React from 'react';
import { Routes, Route } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import Login from './components/Login';
import SignUp from './components/SignUp';
import Main from './components/Main';

function App() {
  const navigate = useNavigate();

  return (
    <Routes>
      <Route path="/" element={<Login navigate={navigate} />} />
      <Route path="/signup" element={<SignUp navigate={navigate} />} />
      <Route path="/main" element={<Main />} />
    </Routes>
  );
}

export default App;
