import React,{useEffect, useState} from 'react';
import styled from '@emotion/styled';
import Select from 'react-select'
import axios from 'axios';

const ElivatorContainer = styled.div`
  display: flex;
  flex-direction: column;
  border-radius: 10px;
  background-color: #fff;
  padding: 20px;
  gap: 0 10px;
  margin-top: 10px;
  text-align: left;
`;

const Title = styled.p`
  font-size: 18px;
  font-family: 'NanumSquareNeoExtraBold';
  color: #3689FD;
`;

const ElivatorItemWrapper = styled.div`
  display: flex;
  flex-direction: column;
  padding: 10px 0;
  &:nth-of-type(2n) {
    border-top: solid 1px #d9d9d9;
    padding-bottom: 0;
  }
`;

const ElivatorItem = styled.div`
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
const Elivator = ({station}) => {
  const [startStation, endStation] = station;
  return (
    <>
      {startStation && endStation ? (
        <ElivatorContainer>
        <Title>엘리베이터 정보</Title>

        <ElivatorItemWrapper>
          <ElivatorItem>
            <p>호기</p>
            <span>1호기</span>
          </ElivatorItem>
          <ElivatorItem>
            <p>설치 위치</p>
            <span>1,2번출구측</span>
          </ElivatorItem>
          <ElivatorItem>
            <p>운행 구간</p>
            <span>지하1층-지상</span>
          </ElivatorItem>
        </ElivatorItemWrapper>

        <ElivatorItemWrapper>
          <ElivatorItem>
            <p>호기</p>
            <span>2호기</span>
          </ElivatorItem>
          <ElivatorItem>
            <p>설치 위치</p>
            <span>1,2번출구측</span>
          </ElivatorItem>
          <ElivatorItem>
            <p>운행 구간</p>
            <span>지하1층-지상</span>
          </ElivatorItem>
        </ElivatorItemWrapper>

      </ElivatorContainer>
      ) : null}
    </>

  );
}

export default Elivator;