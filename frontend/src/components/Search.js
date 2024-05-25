import React,{useEffect, useState} from 'react';
import styled from '@emotion/styled';
import Select from 'react-select'
import axios from 'axios';

const SearchContainer = styled.div`
  display: flex;
  // justify-content: center;
  // align-items: center;
  flex-direction: column;
  border-radius: 10px;
  background-color: #fff;
  padding: 20px;
  gap: 10px;
`;
const SearchItem = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  text-align: left;
  & > .start_station, & > .end_station {
    font-size: 14px;
    font-family: 'NanumSquareNeoExtraBold';
  }
`;

const SearchButton = styled.button`
  width: 100%;
  padding: 10px;
  border: none;
  background-color: #e1e1e1;
  border-radius: 5px;
  margin-top: 10px;
  color: #333;
  font-family: 'NanumSquareNeoBold';
  cursor: not-allowed;
  &.active {
    background-color: #3689FD;
    cursor: pointer;
    color: #fff;
  }
`;

const Search = (props) => {

  const [startStation, setStartStation] = useState('');
  const [endStation, setEndStation] = useState('');
  const [options, setOptions] = useState([]);

  const updateStation = () => {
    props.setStation([startStation, endStation]);
  };

  useEffect(() => {
    axios.get('/assets/json/station.json')
    .then((response) => {
      const data = response.data.stations;
      data.map((station) => {
        const option = {value: station.station_name, label: station.station_name};
        setOptions(options => [...options, option]);
      });
    })
    .catch((error) => {
      console.error(error);
    });
  }, []);

  useEffect(() => {
    console.log(options);
  }, [options])

  return (
    <SearchContainer>
      <SearchItem>
        <p className='start_station'>출발역</p>
        <Select 
          className="react-select-container"
          classNamePrefix="react-select"
          placeholder='역명을 입력해주세요.'
          options={options} 
          onChange={(e) => setStartStation(e.value)}
        />
      </SearchItem>
      <SearchItem>
        <p className='end_station'>도착역</p>
        <Select 
          className="react-select-container"
          classNamePrefix="react-select"
          placeholder='역명을 입력해주세요.'
          options={options} 
          onChange={(e) => setEndStation(e.value)}
        />
      </SearchItem>
      {
        startStation && endStation ? (
          <SearchButton className='active' onClick={updateStation}>검색</SearchButton>
        ) : (
          <SearchButton onClick={updateStation} disabled >검색</SearchButton>
        )
      }
    </SearchContainer>
  );
}

export default Search;