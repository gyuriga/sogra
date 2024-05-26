import React,{useEffect, useState} from 'react';
import styled from '@emotion/styled';
import Select from 'react-select'
import axios from 'axios';

const ToiletContainer = styled.div`
  display: flex;
  flex-direction: column;
  border-radius: 10px;
  background-color: #fff;
  padding: 20px;
  gap: 0 10px;
  margin-top: 10px;
  text-align: left;
`;

const TitleContainer = styled.div`
  display: flex;
  flex-direction: row;
  & > a {
    font-family: 'NanumSquareNeoBold';
    font-size: 14px;
    margin-left: auto;
  }
`;

const Title = styled.p`
  font-size: 18px;
  font-family: 'NanumSquareNeoExtraBold';
  color: #3689FD;
  margin-bottom: 10px;
`;

const ToiletItem = styled.div`
  display: flex;
  gap: 0 10px;
  & > p {
    font-size: 14px;
    font-family: 'NanumSquareNeoExtraBold';
  }
  & > span {
    font-size: 14px;
    font-family: 'NanumSquareNeoRegular';
  }
`;
const Toilet = ({station}) => {
  const [startStation, endStation] = station;
  const [gate, setGate] = useState('');
  const [location, setLocation] = useState('');

  useEffect(() => {
    if (!startStation || !endStation) return;
    const url = `http://localhost:8080/api/toilet?page=1&perPage=1321&stationName=${decodeURI(startStation)}`;
    axios.get(url)
    .then((response) => {
      console.log(response.data);
      setGate(response.data['게이트내외']);
      setLocation(response.data['상세위치']);
    }); 
  }, [station]);

  return (
    <>
      {(startStation && endStation) ? (
        <ToiletContainer>
          <TitleContainer>
            <Title>화장실 정보</Title>
            <a>화장실 정보 모아보기 &gt;</a>
          </TitleContainer>
          <ToiletItem>
            <p>게이트 내외</p>
            <span>{gate === "외" ? "게이트 외부" : gate}</span>
          </ToiletItem>
          <ToiletItem>
            <p style={{minWidth: 60}}>상세 위치</p>
            <span>{location}</span>
          </ToiletItem>
        </ToiletContainer>
      ) : null}
    </>
  );
}

export default Toilet;