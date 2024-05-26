import React from 'react';
import ArrivalItem from '../components/ArrivalItem';
import styled from '@emotion/styled';

const ArrivalWrapper = styled.div`
  margin-top: 5px;
  background-color: #fff;
  border-radius: 10px;
  padding: 20px;
  text-align: left;
`;

const Logo = styled.img`
  height: 35px;
  display: flex;
  margin-bottom: 15px;
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

const ArrivalPage = () => {
  const nowDate = new Date();
  const nowHour = nowDate.getHours();
  const nowMinute = nowDate.getMinutes();
  const nowTime = nowHour + ':' + nowMinute;

    return (
      <div className="App">
      <Logo src='/assets/images/logo.png' />
      <TitleWrapper>
        <Title>열차 도착 정보</Title>
        <Time>{TimeZeroFormat(nowTime)} 기준 정보</Time>
      </TitleWrapper>
      <ArrivalWrapper>
        <ArrivalItem />
        <ArrivalItem />
        <ArrivalItem />
        <ArrivalItem />
        <ArrivalItem />
      </ArrivalWrapper>
    </div>

    );
}

export default ArrivalPage;