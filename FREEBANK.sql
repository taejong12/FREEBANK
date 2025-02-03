--시스템 스키마

-- 사용자 생성
CREATE USER C##FREEBANK IDENTIFIED BY 1234;

-- CONNECT: 사용자가 DB에 접속할 수 있는 권한
-- RESOURCE: 데이터를 생성, 수정, 삭제할 수 있는 권한
GRANT CONNECT, RESOURCE TO C##FREEBANK;

--system 에서 유저 권한 줘야 에러 없이 DB 에 저장 가능
--QUOTA : 테이블 공간 사용 
ALTER USER C##FREEBANK DEFAULT TABLESPACE USERS QUOTA UNLIMITED ON USERS;

--------------------------------------------------------------------------------------------
--FREEBANK 스키마

-- 쇼핑몰 테이블 생성
CREATE TABLE FREEBANKSHOP (
    FREEBANKSHOP_ID NUMBER PRIMARY KEY,                     -- 상품 고유번호(PK)
    FREEBANKSHOP_NAME VARCHAR2(50) NOT NULL,                -- 상품명
    FREEBANKSHOP_CONTENTS VARCHAR2(255) NOT NULL,           -- 상품설명
    FREEBANKSHOP_PRICE NUMBER NOT NULL,                     -- 상품가격
    FREEBANKSHOP_ADMINID VARCHAR2(50) NOT NULL,             -- 상품관리자
    FREEBANKSHOP_CREATE DATE DEFAULT SYSDATE,               -- 상품등록일(기본값: 현재 날짜)
    FREEBANKSHOP_UPDATE DATE DEFAULT SYSDATE                -- 상품수정일 (기본값: 현재 날짜)

);

CREATE SEQUENCE FREEBANKSHOP_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

