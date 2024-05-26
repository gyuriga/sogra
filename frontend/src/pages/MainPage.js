import '../App.css';
import React,{useState, useEffect} from 'react';
import axios from 'axios';
import styled from '@emotion/styled';
import Search from '../components/Search';
import Info  from '../components/Info';
import Arrival from '../components/Arrival';
import Toilet from '../components/Toilet';
import Elivator from '../components/Elevator';

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

  useEffect(() => {
    // /api/congestion/update
    // const url = '/api/congestion/update';
    const url = 'http://localhost:8080/api/congestion/update?page=1&perPage=1321';
    axios.get(url);
  }, []);

  return (
    <div className="App">
      <Logo src='/assets/images/logo.png' />
      <Search setStation={setStation} />
      <Info station={station} />
      <Arrival station={station} />
      <Title>
        {
          station[0] ? station[0]+'역 정보' : null
        }
         </Title>
      <Toilet station={station} />
      <Elivator station={station} />
    </div>
  );
}

export default App;
