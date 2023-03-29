import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import moment from 'moment/moment';
import Config from '../config/Config';

const Main = ({ memberInfo }) => {
  const [date, setDate] = useState(moment());
  const [todos, setTodos] = useState([]);
  const [todo, setTodo] = useState('');

  useEffect(() => {
    // 해당 날짜의 todo 조회
  }, [date]);

  const handlePrevDay = () => {
    setDate(moment(date).add(-1, 'days'));
  };

  const handleNextDay = () => {
    setDate(moment(date).add(1, 'days'));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!todo || !date) return;
    setTodos([...todos, { todo, date }]);
    setTodo('');
    // setDate(new Date());
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

      <form onSubmit={handleSubmit}>
        <Input
          type="text"
          placeholder="Add Todo"
          value={todo}
          onChange={(e) => setTodo(e.target.value)}
        />
        <button type="submit">Add</button>
      </form>
      <ul>
        {todos
          .filter((item) => item.date.toDateString() === date.toDateString())
          .map((item, index) => (
            <li key={index}>
              {item.todo} ({item.date.toLocaleTimeString()})
            </li>
          ))}
      </ul>
    </Container>
  );
};

const Container = styled.div`
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
`;

const Header = styled.h1`
  text-align: center;
  margin-bottom: 30px;
`;

const Input = styled.input`
  width: 100%;
  padding: 10px;
  margin-bottom: 20px;
  font-size: 18px;
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

const UserInfo = styled.div`
  display: flex;
  justify-content: flex-end;
  margin-bottom: 20px;
`;

const Username = styled.span`
  margin-right: 10px;
  font-weight: bold;
`;

export default Main;
