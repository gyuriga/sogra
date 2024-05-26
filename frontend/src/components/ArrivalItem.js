import React,{useEffect, useState} from 'react';
import styled from '@emotion/styled';
import axios from 'axios';

const ArrivalItemContainer = styled.div`
  display: flex;
  font-size: 14px;
  flex-direction: column;
  gap: 2px 0;

  & span {
    margin-left: 5px;
  }
  & p {
    font-family: 'NanumSquareNeoExtraBold';
  }
  & .last {
    padding-bottom: 10px;
  }
`;

const ArrivalItemBox = styled.div`
  display: flex;
  flex-direction: row;
`;

const TimeTranslater = (item) => {
  const [item_hour, item_minute] = item.split(':');
  const item_date = new Date();
  item_date.setHours(item_hour);
  item_date.setMinutes(item_minute);
  return item_date;
}

const TimeZeroFormat = (time) => {
  if (time < 10) {
    return '0' + time;
  }
  return time;
}

const ArrivalItem = ({type, time, reqTime, station}) => {
  const [timeDiff, setTimeDiff] = useState(0);
  const [timeFormat, setTimeFormat] = useState('');
  const [up, setUp] = useState(0);
  const [down, setDown] = useState(0);

  useEffect(() => {
    if (!time || !reqTime) return;
    const timeFormat = 'hour' + TimeZeroFormat(time.split(':')[0]) + '_' + TimeZeroFormat(parseInt(time.split(':')[0]) + 1);
    setTimeFormat(timeFormat);

    const diff = (TimeTranslater(time) - TimeTranslater(reqTime));
    setTimeDiff(Math.round(diff / (1000 * 60)));
  }, [time, reqTime]);

  useEffect(() => {
    const url = 'http://localhost:8080/api/congestion';

    const data = {
      time: timeFormat,
      stationName: station
    };

    console.log(data);

    axios.post(url, {
      time: timeFormat,
      stationName: station
    }).then((response) => {
      const data = response.data;
      setUp(data.up);
      setDown(data.down);
    }).catch((error) => {
      console.log(error);
    });

  }, [timeFormat, station]);


  return (
    <ArrivalItemContainer>
      {
        type === 2 ? <div style={{borderTop: "solid 1px #d9d9d9", paddingBottom: 10}}></div> : null
      }
      <ArrivalItemBox>
        <p>도착 정보</p>
        <span>{time}</span>
        <span style={{color: "#DA1C1C"}}>[{timeDiff}분후]</span>
      </ArrivalItemBox>
      <ArrivalItemBox>
        <p>평균 승차 인원</p>
        <span>{Math.round(up)}명</span>
      </ArrivalItemBox>
      <ArrivalItemBox className='last'>
        <p>평균 하차 인원</p>
        <span>{Math.round(down)}명</span>
      </ArrivalItemBox>
    </ArrivalItemContainer>
  );
}

export default ArrivalItem;