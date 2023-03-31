import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import Config from '../config/Config';
import * as ApiService from '../service/ApiService';
import dayjs from 'dayjs';

const Main = (props) => {
  const { navigate, token } = props;

  const [memberInfo, setMemberInfo] = useState({
    memberId: '',
    email: '',
    nickname: '',
    birthDate: '',
    genderCode: '',
    phoneNo: '',
    authoritys: []
  });
  const [date, setDate] = useState(dayjs());
  const [group, setGroup] = useState([]);
  const [selectedGroup, setSelectedGroup] = useState(0);
  const [todos, setTodos] = useState([]);
  const [todo, setTodo] = useState('');

  useEffect(() => {
    if (token !== '' && token !== undefined) {
      ApiService.getMethod(`member`, { token: token }).then((response) => {
        let member = response.data;

        let authoritys = [];
        member.authorityDtoSet.map((item) => {
          let authority = item.authorityName;
          authoritys = [...authoritys, authority];
        });

        setMemberInfo({
          ...memberInfo,
          memberId: member.memberId,
          email: member.email,
          nickname: member.nickname,
          birthDate: member.birthDate,
          genderCode: member.genderCode,
          phoneNo: member.phoneNo,
          authoritys: authoritys
        });
      });
    } else {
      alert('로그인이 필요합니다.');
      navigate('/');
    }
  }, [memberInfo, navigate, token]);

  useEffect(() => {
    // 해당 날짜의 todo 조회
    console.log(dayjs(date).format(Config.defaultDateDisplayFormat));
    console.log(group);
  }, [date, group]);

  useEffect(() => {
    // memberId기준, date기준으로 선택된 그룹에 대한 todo 가져오기
    // API를 호출하여 그룹 데이터 가져오기
    // 가져온 데이터를 setGroup 함수를 사용하여 group 상태를 업데이트
  }, []);

  const handleSelectGroup = (index) => {
    setSelectedGroup(index);
  };

  const handlePrevDay = () => {
    setDate(dayjs(date).add(-1, 'days'));
  };

  const handleNextDay = () => {
    setDate(dayjs(date).add(1, 'days'));
  };

  const handleSubmit = (e) => {
    // e.preventDefault();
    // if (!todo || !date) return;
    // setTodos([...todos, { todo, date }]);
    // setTodo('');
  };

  return (
    <Container>
      <UserInfo>
        <Username>{memberInfo.nickname}</Username>
      </UserInfo>
      <Header>Todo List</Header>
      <DateHeader>
        <span onClick={handlePrevDay}>&#8249;</span>
        {date.format(Config.defaultDateDisplayFormat)}
        <span onClick={handleNextDay}>&#8250;</span>
      </DateHeader>

      <GroupWrapper>
        <GroupTab type="button">group1</GroupTab>
        <GroupTab type="button">group2</GroupTab>
        <GroupPlusTab type="button">+</GroupPlusTab>
      </GroupWrapper>

      <FormWrapper onSubmit={handleSubmit}>
        <Input
          type="text"
          placeholder="Add Todo"
          value={todo}
          onChange={(e) => setTodo(e.target.value)}
        />
        <Button type="submit">+</Button>
      </FormWrapper>
      <ul>
        {/* {todos
          .filter((item) => item.date.toDateString() === date.toDateString())
          .map((item, index) => (
            <li key={index}>
              {item.todo} ({item.date.toLocaleTimeString()})
            </li>
          ))} */}
      </ul>
    </Container>
  );
};

const Container = styled.div`
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
`;

const UserInfo = styled.div`
  display: flex;
  justify-content: flex-end;
  margin-bottom: 20px;
`;

const Username = styled.span`
  margin-right: 10px;
  font-weight: bold;
`;

const Header = styled.h1`
  text-align: center;
  margin-bottom: 30px;
`;

const DateHeader = styled.h2`
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 24px;
  margin-bottom: 20px;

  span {
    margin: 0 20px;
    cursor: pointer;
  }
`;

const GroupWrapper = styled.div`
  display: flex;
  flex-direction: row;
  margin-bottom: 10px;
`;

const GroupTab = styled.button`
  display: flex;
  flex-direction: column;
  font-size: 20px;
  border-radius: 5px;
  border: none;
  margin-right: 5px;

  &:hover {
    font-weight: bold;
  }
`;

const GroupPlusTab = styled.button`
  display: flex;
  flex-direction: column;
  font-size: 20px;
  border-radius: 5px;
  border: none;

  &:hover {
    font-weight: bold;
  }
`;

const FormWrapper = styled.form`
  display: flex;
  flex-direction: row;
  align-items: center;
  background-color: #f7f7f7;
  border-radius: 10px;
  padding: 40px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
`;

const Input = styled.input`
  width: 90%;
  padding: 10px;
  margin-bottom: 20px;
  border-radius: 5px;
  border: none;
  font-size: 18px;
`;

const Button = styled.button`
  width: 10%;
  padding: 10px;
  margin-bottom: 20px;
  border-radius: 5px;
  border: none;
  background-color: #0077ff;
  color: white;
  font-size: 20px;
  cursor: pointer;
  transition: all 0.2s ease-in-out;

  &:hover {
    background-color: #0062cc;
  }
`;

export default Main;
