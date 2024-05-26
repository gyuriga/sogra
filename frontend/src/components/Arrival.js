import React,{useEffect, useState} from 'react';
import styled from '@emotion/styled';
import Select from 'react-select'
import axios from 'axios';
import ArrivalItem from './ArrivalItem';

const ArrivalWrapper = styled.div`
  margin-top: 30px;
`;

const ArrivalContainer = styled.div`
  display: flex;
  flex-direction: column;
  border-radius: 10px;
  background-color: #fff;
  padding: 20px;
  gap: 0 10px;
  margin-top: 5px;
  text-align: left;
`;

const TitleWrapper = styled.div`
  display: flex;
  gap: 0 5px;
`;
  

const Title = styled.p`
  font-size: 20px;
  font-family: 'NanumSquareNeoExtraBold';
  text-align: left;
`;

const Direction = styled.p`
  font-size: 18px;
  font-family: 'NanumSquareNeoExtraBold';
  color: #3689FD;
  margin-bottom: 10px;
`;

const ArrivalList = styled.div`
  display: flex;
  flex-direction: column;
`;

const ArrivalButton = styled.a`
  text-align: center;
  font-size: 12px;
  width: 100%;
  padding: 10px;
  background-color: #e1e1e1;
  border: none;
  color: #333;
  border-radius: 5px;
  margin-top: 10px;
  font-family: 'NanumSquareNeoBold';
  text-decoration: none;
`;

const Time = styled.span`
  margin-left: 5px;
  font-size: 14px;
  line-height: 27px;
  font-family: 'NanumSquareNeoBold';
  margin-left: auto;
`;

const TimeZeroFormat = (time) => {
  let [hour, minute] = time.split(':');
  if (hour < 10) {
    hour = '0' + hour;
  }
  if (minute < 10) {
    minute = '0' + minute;
  }
  return hour + ':' + minute;

}

const Arrival = ({station}) => {
  const [startStation, endStation] = station;
  const [startStationCode, setStartStationCode] = useState();
  const [endStationCode, setEndStationCode] = useState();
  const [upDirection, setUpDirection] = useState(true);
  const [time, setTime] = useState('');
  const [time1, setTime1] = useState('');
  const [time2, setTime2] = useState('');

  useEffect(() => {
    const [hour, minute] = time.split(':');
    const date = new Date();
    date.setHours(hour);
    date.setMinutes(minute);

    if (!startStation) return;
    axios.get('/assets/json/up_time.json')
    .then((response) => {
      const data = response.data;
      const startStationTimes = data[startStation];
      // 가장 가까운 2개의 시간 찾기
      let index = 0;
      const time1 = startStationTimes.find((item, idx) => {
        const [item_hour, item_minute] = item.split(':');
        const item_date = new Date();
        item_date.setHours(item_hour);
        item_date.setMinutes(item_minute);
        index = idx;
        return item_date > date;
      });
      const time2 = startStationTimes[index + 1];
      setTime1(time1);
      setTime2(time2);
    });
  }, [time]);

  useEffect(() => {
    if (!startStation || !endStation) return;
    axios.get('/assets/json/station.json')
    .then((response) => {
      setStartStationCode(response.data.stations.find((station) => station.station_name === startStation).station_number);
      setEndStationCode(response.data.stations.find((station) => station.station_name === endStation).station_number);
    });

    const nowDate = new Date();
    const nowHour = nowDate.getHours();
    const nowMinute = nowDate.getMinutes();
    // setTime('7:00');
    setTime(nowHour + ':' + nowMinute);



  }, [station])

  useEffect(() => {
    if (startStationCode > endStationCode) {
      setUpDirection(false);
      console.log('down');
    }
  }, [startStationCode, endStationCode]);

  return(
    <>
      {startStation && endStation ? (
      <ArrivalWrapper>
        <TitleWrapper>
          <Title>열차 도착 정보</Title>
          <Time>{TimeZeroFormat(time)} 기준 정보</Time>
        </TitleWrapper>

        <ArrivalContainer>
          <Direction>{ upDirection ? '반석' : '판암'} 방면</Direction>
          <ArrivalList>

            <ArrivalItem station={startStation} reqTime={time} time={time1}></ArrivalItem>
            <ArrivalItem station={startStation} reqTime={time} type={2} time={time2}></ArrivalItem>

          </ArrivalList>
          <ArrivalButton href='/arrival'>내역 더보기 &gt;</ArrivalButton>
        </ArrivalContainer>
      </ArrivalWrapper>
      ) : null}
    </>
  );
}

export default Arrival;