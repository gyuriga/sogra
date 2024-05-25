import './App.css';
import React,{useState, useEffect} from 'react';
import styled from '@emotion/styled';
import Search from './components/Search';
import Info  from './components/Info';
import Arrival from './components/Arrival';
import Toilet from './components/Toilet';
import Elivator from './components/Elevator';

const Title = styled.p`
  margin-top: 30px;
  font-size: 20px;
  font-family: 'NanumSquareNeoExtraBold';
  text-align: left;
`;

const Logo = styled.img`
  height: 35px;
  display: flex;
`;

function App() {
  const [station, setStation] = useState([]);

  useEffect(() => {
    console.log(station);
  }, [station]);

  return (
    <div className="App">
      <Logo src='/assets/images/logo.png' />
      <Search setStation={setStation} />
      <Info station={station} />
      <Arrival station={station} />
      <Title>유성온천역 정보</Title>
      <Toilet />
      <Elivator />
    </div>
  );
}

export default App;
