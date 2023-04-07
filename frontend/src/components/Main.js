import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import Config from '../config/Config';
import * as ApiService from '../service/ApiService';
import dayjs from 'dayjs';
import { getToken } from '../util/Auth';
import DeleteOutlinedIcon from '@mui/icons-material/DeleteOutlined';

const Main = (props) => {
  const { navigate } = props;

  const token = getToken();

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
  const [groups, setGroups] = useState([]);
  const [selectedGroupId, setSelectedGroupId] = useState(0);
  const [todos, setTodos] = useState([]);
  const [todo, setTodo] = useState('');
  const [changeStatus, setChangeStatus] = useState(0);
  const [error, setError] = useState('');

  // 회원 정보 조회
  useEffect(() => {
    if (token !== '' && token !== null) {
      ApiService.getMethod(`member`, { token: token }).then((res) => {
        let member = res.data;

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
  }, [token]);

  // 그룹 조회
  useEffect(() => {
    if (memberInfo.memberId !== '') {
      ApiService.getMethod(`todo-groups/${memberInfo.memberId}`).then((res) => {
        const groups = res.data;
        setGroups(groups);

        const groupId = groups[0].groupId;
        setSelectedGroupId(groupId);
      });
    }
  }, [memberInfo]);

  // todo 조회
  useEffect(() => {
    if (groups.length !== 0 && selectedGroupId !== 0) {
      const apiParam = {
        groupId: selectedGroupId,
        date: dayjs(date).format(Config.defaultDateValueFormat)
      };

      ApiService.getMethod(`todos`, apiParam).then((res) => {
        const todos = res.data;
        setTodos(todos);
      });
    }
  }, [date, groups, selectedGroupId, changeStatus]);

  const handleSelectGroup = (groupId) => {
    setSelectedGroupId(groupId);
  };

  const handlePrevDay = () => {
    setDate(dayjs(date).add(-1, 'days'));
  };

  const handleNextDay = () => {
    setDate(dayjs(date).add(1, 'days'));
  };

  const addTodo = (e) => {
    e.preventDefault();

    const title = todo.trim();
    if (title.length === 0) {
      setError('할 일을 입력해주세요.');
    } else {
      setError('');
      const apiParam = {
        groupId: selectedGroupId,
        date: dayjs(date).format(Config.defaultDateValueFormat),
        completeYn: 'N',
        disOrder: todos.length + 1,
        title: title
      };

      ApiService.postMethod(`todo`, apiParam).then((res) => {
        const status = res.status;
        if (status !== 200) {
          alert('addTodo Error');
          return;
        }

        setChangeStatus(changeStatus + 1);
      });
    }

    setTodo('');
  };

  const changeCompleteYn = (todoId) => {
    ApiService.patchMethod(`complete/${todoId}`).then((res) => {
      const status = res.status;
      if (status !== 200) {
        alert('changeCompleteYn Error');
        return;
      }

      setChangeStatus(changeStatus + 1);
    });
  };

  const deleteTodo = (todoId) => {
    ApiService.deleteMethod(`todo/${todoId}`).then((res) => {
      const status = res.status;
      if (status !== 200) {
        alert('deleteTodo Error');
        return;
      }

      setChangeStatus(changeStatus + 1);
    });
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
        {groups.map((item) => {
          return (
            <GroupTab
              key={item.groupId}
              onClick={() => handleSelectGroup(item.groupId)}
              style={{ fontWeight: selectedGroupId === item.groupId && 'bold' }}
            >
              {item.name}
            </GroupTab>
          );
        })}
        <GroupPlusTab>+</GroupPlusTab>
      </GroupWrapper>

      <Div>
        <FormWrapper onSubmit={addTodo}>
          <Input
            type="text"
            placeholder="Add Todo"
            value={todo}
            onChange={(e) => setTodo(e.target.value)}
          />
          <Button type="submit">+</Button>
        </FormWrapper>
        {error && <ErrorMessage>{error}</ErrorMessage>}
      </Div>

      <TodosWrapper>
        {todos.map((item) => {
          return (
            <TodoWrapper key={item.todoId}>
              <input
                type="checkbox"
                checked={item.completeYn === 'Y'}
                onChange={() => changeCompleteYn(item.todoId)}
              />
              <TodoLable style={{ textDecoration: item.completeYn === 'Y' && 'line-through' }}>
                {item.title}
              </TodoLable>
              <DeleteOutlinedIcon
                fontSize="small"
                style={{ marginLeft: '5px' }}
                onClick={() => deleteTodo(item.todoId)}
              />
            </TodoWrapper>
          );
        })}
        {todos.length === 0 && <div>할 일을 등록하세요!</div>}
      </TodosWrapper>
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
    border: 1px solid;
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
`;

const Div = styled.div`
  display: flex;
  flex-direction: column;
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

const TodosWrapper = styled.ul`
  display: flex;
  flex-direction: column;
  align-items: left;
  background-color: #fff;
  border-radius: 10px;
  padding: 40px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
`;

const TodoLable = styled.label`
  font-size: 18px;
  margin-left: 10px;
`;

const TodoWrapper = styled.li`
  display: flex;
  flex-direction: row;
  margin-bottom: 15px;
`;

const ErrorMessage = styled.div`
  color: red;
  font-size: 16px;
  margin-bottom: 20px;
`;

export default Main;
