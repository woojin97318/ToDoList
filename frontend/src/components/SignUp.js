import React, { useState } from 'react';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import styled from 'styled-components';
import * as ApiService from '../service/ApiService';
import { useNavigate } from 'react-router-dom';

const SignUp = () => {
  const navigate = useNavigate();

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [name, setName] = useState('');
  const [birthday, setBirthday] = useState(null);
  const [gender, setGender] = useState('');
  const [phone, setPhone] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (password !== confirmPassword) {
      setError('비밀번호가 일치하지 않습니다.');
    } else {
      setError('');

      const apiParam = {
        email: email,
        password: password,
        nickname: name,
        birthDate: birthday,
        genderCode: gender,
        phoneNo: phone
      };

      ApiService.postMethod(`signup`, apiParam, {}).then((response) => {
        console.log(response);

        if (response.status === 200) {
          alert('회원가입이 완료되었습니다.');
          navigate('/');
        } else {
          alert(response.data.message);
        }
      });
    }
  };

  return (
    <Container>
      <Title>회원가입</Title>
      <Form onSubmit={handleSubmit}>
        <Label>이메일</Label>
        <Input type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />

        <Label>비밀번호</Label>
        <Input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />

        <Label>비밀번호 확인</Label>
        <Input
          type="password"
          value={confirmPassword}
          onChange={(e) => setConfirmPassword(e.target.value)}
          required
        />

        {error && <ErrorMessage>{error}</ErrorMessage>}

        <Label>이름</Label>
        <Input type="text" value={name} onChange={(e) => setName(e.target.value)} required />

        <Label>생일</Label>
        <DatePicker selected={birthday} onChange={(date) => setBirthday(date)} />

        <Label>성별</Label>
        <RadioGroup>
          <RadioButton
            type="radio"
            name="gender"
            value="M"
            checked={gender === 'M'}
            onChange={(e) => setGender(e.target.value)}
            required
          />
          <Label>남성</Label>
          <RadioButton
            type="radio"
            name="gender"
            value="F"
            checked={gender === 'F'}
            onChange={(e) => setGender(e.target.value)}
            required
          />
          <Label>여성</Label>
        </RadioGroup>

        <Label>전화번호</Label>
        <Input type="tel" value={phone} onChange={(e) => setPhone(e.target.value)} required />

        <Button type="submit">가입하기</Button>
      </Form>
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 10px;
`;

const Form = styled.form`
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
  margin-bottom: 5px;
`;

const Input = styled.input`
  width: 100%;
  padding: 10px;
  border-radius: 5px;
  border: none;
  margin-bottom: 20px;
`;

const RadioGroup = styled.div`
  display: flex;
  flex-direction: row;
  margin-bottom: 20px;
`;

const RadioButton = styled.input`
  margin-right: 10px;
`;

const ErrorMessage = styled.div`
  color: red;
  font-size: 16px;
  margin-bottom: 20px;
`;

const Button = styled.button`
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

export default SignUp;
