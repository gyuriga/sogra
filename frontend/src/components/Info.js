import React,{useEffect, useState} from 'react';
import styled from '@emotion/styled';
import Select from 'react-select'
import axios from 'axios';

const InfoContainer = styled.div`
  display: flex;
  flex-direction: column;
  border-radius: 10px;
  background-color: #fff;
  padding: 20px;
  gap: 0 10px;
  margin-top: 20px;
  text-align: left;
`;

const Title = styled.p`
  font-size: 18px;
  font-family: 'NanumSquareNeoExtraBold';
  color: #3689FD;
  margin-bottom: 10px;
`;

const InfoItem = styled.div`
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

const Info = ({station}) => {
  const [startStation, endStation] = station;
  const [startStationCode, setStartStationCode] = useState();
  const [endStationCode, setEndStationCode] = useState();
  const [diff, setDiff] = useState(0);

  useEffect(() => {
    if (!startStation || !endStation) return;
    axios.get('/assets/json/station.json')
    .then((response) => {
      setStartStationCode(response.data.stations.find((station) => station.station_name === startStation).station_number);
      setEndStationCode(response.data.stations.find((station) => station.station_name === endStation).station_number);
    });
  }, [station]);

  useEffect(() => {
    setDiff(Math.abs(startStationCode - endStationCode));
  }, [startStationCode, endStationCode])

  return (
    <>
      {(startStation && endStation) ? (
        <InfoContainer>
          <Title>{startStation}역 - {endStation}역</Title>
          <InfoItem>
            <p>경유역 수</p>
            <span>{diff}개 역</span>
          </InfoItem>
          <InfoItem>
            <p>소요 시간</p>
            <span>{diff*2}분</span>
          </InfoItem>
        </InfoContainer>
      ) : null}
    </>

  );
}

export default Info;