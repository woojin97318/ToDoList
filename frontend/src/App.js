import React, { useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import Login from './components/Login';
import SignUp from './components/SignUp';
import Main from './components/Main';

function App() {
  const [memberInfo, setMemberInfo] = useState({
    token: '',
    email: '',
    nickname: '',
    birthDate: '',
    genderCode: '',
    phoneNo: '',
    authoritys: []
  });

  const changeMemberInfo = (memberInfo) => {
    setMemberInfo(memberInfo);
  };

  return (
    <Routes>
      <Route
        path="/"
        element={<Login memberInfo={memberInfo} changeMemberInfo={changeMemberInfo} />}
      />
      <Route
        path="/signup"
        element={<SignUp memberInfo={memberInfo} changeMemberInfo={changeMemberInfo} />}
      />
      <Route path="/main" element={<Main memberInfo={memberInfo} />} />
    </Routes>
  );
}

export default App;
