import React, { useState } from "react";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";

function Login() {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  function handleSubmit(event) {
    event.preventDefault();

    // // 서버로 이메일과 비밀번호 전송
    // if (email === "example@example.com" && password === "password") {
    //   // 로그인 성공
    // } else {
    //   // 로그인 실패
    //   setError("이메일 또는 비밀번호가 올바르지 않습니다.");
    // }
  }

  const handleRegisterClick = () => {
    navigate("/signup");
  };

  return (
    <LoginPageWrapper>
      <Title>로그인</Title>
      <FormWrapper onSubmit={handleSubmit}>
        <div>
          <Label htmlFor="email">이메일</Label>
          <Input
            type="email"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
        <div>
          <Label htmlFor="password">비밀번호</Label>
          <Input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        {error && <ErrorMessage>{error}</ErrorMessage>}
        <SubmitButton type="submit">로그인</SubmitButton>
      </FormWrapper>
      <SignupButton onClick={handleRegisterClick}>회원가입</SignupButton>
    </LoginPageWrapper>
  );
}

const LoginPageWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 100px;
`;

const FormWrapper = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #f7f7f7;
  border-radius: 10px;
  padding: 40px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
`;

const Title = styled.h1`
  font-size: 36px;
  margin-bottom: 20px;
`;

const Label = styled.label`
  font-size: 20px;
  margin-bottom: 10px;
`;

const Input = styled.input`
  width: 100%;
  padding: 10px;
  border-radius: 5px;
  border: none;
  margin-bottom: 20px;
`;

const ErrorMessage = styled.div`
  color: red;
  font-size: 16px;
  margin-bottom: 20px;
`;

const SubmitButton = styled.button`
  background-color: #0077ff;
  color: white;
  font-size: 20px;
  padding: 10px 20px;
  border-radius: 5px;
  border: none;
  cursor: pointer;
  transition: all 0.2s ease-in-out;

  &:hover {
    background-color: #0062cc;
  }
`;

const SignupButton = styled.button`
  background-color: #0077ff;
  color: white;
  font-size: 20px;
  padding: 10px 20px;
  border-radius: 5px;
  border: none;
  cursor: pointer;
  transition: all 0.2s ease-in-out;
  margin-top: 20px;

  &:hover {
    background-color: #0062cc;
  }
`;

export default Login;
