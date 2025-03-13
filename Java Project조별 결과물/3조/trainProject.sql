
CREATE TABLE MEMBER(
    MEM_ID VARCHAR2(50) NOT NULL PRIMARY KEY,
    MEM_PW VARCHAR2(50) NOT NULL,
    MEM_NAME VARCHAR2(50) NOT NULL,
    MEM_EMAIL VARCHAR2(50) NOT NULL,
    MEM_PHONE VARCHAR2(50) NOT NULL,
    MEM_ADDRESS VARCHAR2(50) NOT NULL,
    MEM_GENDER VARCHAR2(50) NOT NULL
);

CREATE TABLE TRAIN (
    tr_number VARCHAR2(50) NOT NULL PRIMARY KEY , -- 열차코드
    tr_name VARCHAR2(50) NOT NULL,  -- 열차이름
    tr_local VARCHAR2(50) NOT NULL,  -- 출발지
    tr_leave VARCHAR2(50) NOT NULL,  -- 출발 시간
    tr_arrive VARCHAR2(50) NOT NULL,  -- 도착 시간
    tr_free VARCHAR2(50) NULL,  -- 입석 및 자유석
    tr_local2 VARCHAR2(50) NOT NULL,  -- 도착지
    tr_price VARCHAR2(50) NOT NULL, -- 운임 요금
    tr_time VARCHAR2(50) NOT NULL,  -- 예상 소요시간
    tr_day varchar2(50) not null -- 탑승일자
);

CREATE TABLE PAY (
    TR_DAY VARCHAR2(50),
    TR_LOCAL VARCHAR2(50),
    TR_LOCAL2 VARCHAR2(50),
    TR_PRICE VARCHAR2(50),
    TR_SEAT VARCHAR2(50) 
);

ALTER TABLE PAY ADD MEM_ID VARCHAR2(50);
ALTER TABLE PAY MODIFY MEM_ID VARCHAR2(50) NOT NULL;
ALTER TABLE PAY ADD CONSTRAINT FK_PAY_MEMBER 
FOREIGN KEY (MEM_ID) REFERENCES MEMBER(MEM_ID)
ON DELETE CASCADE;


CREATE TABLE Seat (
    SEAT_NUMBER VARCHAR2(50 BYTE) NOT NULL,   -- 좌석 번호 (1, 2, 3, ..., 16)
    IS_RESERVED CHAR(1) DEFAULT 'N',          -- 예약 상태 (Y = 예약됨, N = 예약 안 됨)
    TR_NUMBER VARCHAR2(50 BYTE) NOT NULL,     -- 기차 번호 (기차 테이블의 TR_NUMBER와 연결)
    TR_NAME VARCHAR2(50 BYTE) NOT NULL,       -- 기차 이름
    CONSTRAINT PK_SEAT PRIMARY KEY (SEAT_NUMBER, TR_NUMBER),
    CONSTRAINT FK_TR_NUMBER FOREIGN KEY (TR_NUMBER) REFERENCES Train (TR_NUMBER)  -- 기차 번호 외래키
);


INSERT INTO TRAIN VALUES ('1', 'KTX', '서울', '05:13', '07:49', '선택가능', '부산', '50800원', '02:36', TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('2', 'KTX', '용산', '18:10', '21:25', '선택가능', '익산', '28700원', '03:15', TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('3', 'KTX', '서대전', '18:18', '20:07', '선택가능', '정읍', '9100원', '01:49', TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('4', 'KTX', '서울', '05:38', '06:30', '선택가능', '오송', '18500원', '00:52', TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('5', 'KTX', '서울', '06:03', '06:55', '선택가능', '오송', '15700원', '00:52', TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('6', 'KTX', '서울', '17:28', '23:20', '선택가능', '부산', '27600원', '05:52', TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('7', 'KTX', '서울', '05:58', '08:42', '선택가능', '부산', '59400원', '02:24', TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('8', 'KTX', '서울', '06:33', '09:21', '선택가능', '부산', '59800원', '02:48', TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('9', 'KTX', '서울', '14:15', '16:43', '선택가능', '부산', '59800원', '02:28', TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('10', 'KTX', '서울', '08:57', '10:11', '선택가능', '원주', '11800원', '01:14', TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('11', 'KTX', '서울', '10:59', '12:17', '선택가능', '원주', '11800원', '01:18', TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('12', 'KTX', '오송', '06:12', '07:09', '선택가능', '서울', '15700원', '00:57', TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('13', 'KTX', '오송', '06:46', '07:42', '선택가능', '용산', '16400원', '00:56', TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('14', 'KTX', '익산', '11:44', '15:49', '선택가능', '용산', '15900원', '04:05', TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('15', 'KTX', '서울', '16:28', '18:58', '선택가능', '부산', '59800원', '02:30', TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('16', 'KTX', '서울', '19:35', '22:09', '선택가능', '부산', '59800원', '02:34', TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('17', 'KTX', '서울', '19:48', '23:08', '선택가능', '부산', '48800원', '03:20', TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('18', 'KTX', '서울', '19:48', '23:08', '선택가능', '부산', '48800원', '03:20', TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('19', '새마을호', '용산', '17:52', '18:24', '선택가능', '수원', '4800원', '00:32',TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('20', '새마을호', '용산', '17:52', '18:00', '선택가능', '영등포', '4800원', '00:08',TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('21', '새마을호', '서대전', '21:11', '23:27', '선택가능', '밀양', '14000원', '02:16',TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('22', 'KTX', '부산', '17:50', '19:23', '선택가능', '대전', '36200원', '01:33',TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('23', 'KTX', '영등포', '19:07', '20:41', '선택가능', '대전', '16300원', '01:34',TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('24','KTX', '광명', '18:32', '20:54', '선택가능', '부산', '57700원', '02:22',TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('25', '무궁화호', '서울', '17:28', '23:20', '선택가능', '부산', '28600원', '05:52',TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('26', '무궁화호', '용산', '18:10', '21:25', '선택가능', '익산', '23700원', '03:15',TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('27', '무궁화호', '서대전', '18:18', '20:07', '선택가능', '정읍', '8100원', '01:49',TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('28', '무궁화호', '평택', '15:01', '19:39', '선택가능', '부산', '23000원', '04:38',TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('29', 'KTX', '익산', '11:05', '12:28', '선택가능', '서울', '32200원', '01:23', TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('30', 'KTX', '익산', '12:00', '13:33', '선택가능', '서울', '32300원', '01:33', TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TRAIN VALUES ('31', 'KTX', '익산', '14:01', '15:36', '선택가능', '서울', '32300원', '01:35', TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));


INSERT INTO Seat VALUES ('1', 'N', '1', 'KTX');
INSERT INTO Seat VALUES ('2', 'N', '1', 'KTX');
INSERT INTO Seat VALUES ('3', 'N', '1', 'KTX');
INSERT INTO Seat VALUES ('4', 'N', '1', 'KTX');
INSERT INTO Seat VALUES ('5', 'N', '1', 'KTX');
INSERT INTO Seat VALUES ('6', 'N', '1', 'KTX');
INSERT INTO Seat VALUES ('7', 'N', '1', 'KTX');
INSERT INTO Seat VALUES ('8', 'N', '1', 'KTX');
INSERT INTO Seat VALUES ('9', 'N', '1', 'KTX');
INSERT INTO Seat VALUES ('10', 'N', '1', 'KTX');
INSERT INTO Seat VALUES ('11', 'N', '1', 'KTX');
INSERT INTO Seat VALUES ('12', 'N', '1', 'KTX');
INSERT INTO Seat VALUES ('13', 'N', '1', 'KTX');
INSERT INTO Seat VALUES ('14', 'N', '1', 'KTX');
INSERT INTO Seat VALUES ('15', 'N', '1', 'KTX');
INSERT INTO Seat VALUES ('16', 'N', '1', 'KTX');
INSERT INTO Seat VALUES ('1', 'N', '2', 'KTX');
INSERT INTO Seat VALUES ('2', 'N', '2', 'KTX');
INSERT INTO Seat VALUES ('3', 'N', '2', 'KTX');
INSERT INTO Seat VALUES ('4', 'N', '2', 'KTX');
INSERT INTO Seat VALUES ('5', 'N', '2', 'KTX');
INSERT INTO Seat VALUES ('6', 'N', '2', 'KTX');
INSERT INTO Seat VALUES ('7', 'N', '2', 'KTX');
INSERT INTO Seat VALUES ('8', 'N', '2', 'KTX');
INSERT INTO Seat VALUES ('9', 'N', '2', 'KTX');
INSERT INTO Seat VALUES ('10', 'N', '2', 'KTX');
INSERT INTO Seat VALUES ('11', 'N', '2', 'KTX');
INSERT INTO Seat VALUES ('12', 'N', '2', 'KTX');
INSERT INTO Seat VALUES ('13', 'N', '2', 'KTX');
INSERT INTO Seat VALUES ('14', 'N', '2', 'KTX');
INSERT INTO Seat VALUES ('15', 'N', '2', 'KTX');
INSERT INTO Seat VALUES ('16', 'N', '2', 'KTX');
INSERT INTO Seat VALUES ('1', 'N', '3', 'KTX');
INSERT INTO Seat VALUES ('2', 'N', '3', 'KTX');
INSERT INTO Seat VALUES ('3', 'N', '3', 'KTX');
INSERT INTO Seat VALUES ('4', 'N', '3', 'KTX');
INSERT INTO Seat VALUES ('5', 'N', '3', 'KTX');
INSERT INTO Seat VALUES ('6', 'N', '3', 'KTX');
INSERT INTO Seat VALUES ('7', 'N', '3', 'KTX');
INSERT INTO Seat VALUES ('8', 'N', '3', 'KTX');
INSERT INTO Seat VALUES ('9', 'N', '3', 'KTX');
INSERT INTO Seat VALUES ('10', 'N', '3', 'KTX');
INSERT INTO Seat VALUES ('11', 'N', '3', 'KTX');
INSERT INTO Seat VALUES ('12', 'N', '3', 'KTX');
INSERT INTO Seat VALUES ('13', 'N', '3', 'KTX');
INSERT INTO Seat VALUES ('14', 'N', '3', 'KTX');
INSERT INTO Seat VALUES ('15', 'N', '3', 'KTX');
INSERT INTO Seat VALUES ('16', 'N', '3', 'KTX');
INSERT INTO Seat VALUES ('1', 'N', '4', 'KTX');
INSERT INTO Seat VALUES ('2', 'N', '4', 'KTX');
INSERT INTO Seat VALUES ('3', 'N', '4', 'KTX');
INSERT INTO Seat VALUES ('4', 'N', '4', 'KTX');
INSERT INTO Seat VALUES ('5', 'N', '4', 'KTX');
INSERT INTO Seat VALUES ('6', 'N', '4', 'KTX');
INSERT INTO Seat VALUES ('7', 'N', '4', 'KTX');
INSERT INTO Seat VALUES ('8', 'N', '4', 'KTX');
INSERT INTO Seat VALUES ('9', 'N', '4', 'KTX');
INSERT INTO Seat VALUES ('10', 'N', '4', 'KTX');
INSERT INTO Seat VALUES ('11', 'N', '4', 'KTX');
INSERT INTO Seat VALUES ('12', 'N', '4', 'KTX');
INSERT INTO Seat VALUES ('13', 'N', '4', 'KTX');
INSERT INTO Seat VALUES ('14', 'N', '4', 'KTX');
INSERT INTO Seat VALUES ('15', 'N', '4', 'KTX');
INSERT INTO Seat VALUES ('16', 'N', '4', 'KTX');


INSERT INTO Seat VALUES ('1', 'N', '19', '새마을호');
INSERT INTO Seat VALUES ('2', 'N', '19', '새마을호');
INSERT INTO Seat VALUES ('3', 'N', '19', '새마을호');
INSERT INTO Seat VALUES ('4', 'N', '19', '새마을호');
INSERT INTO Seat VALUES ('5', 'N', '19', '새마을호');
INSERT INTO Seat VALUES ('6', 'N', '19', '새마을호');
INSERT INTO Seat VALUES ('7', 'N', '19', '새마을호');
INSERT INTO Seat VALUES ('8', 'N', '19', '새마을호');
INSERT INTO Seat VALUES ('9', 'N', '19', '새마을호');
INSERT INTO Seat VALUES ('10', 'N', '19', '새마을호');
INSERT INTO Seat VALUES ('11', 'N', '19', '새마을호');
INSERT INTO Seat VALUES ('12', 'N', '19', '새마을호');
INSERT INTO Seat VALUES ('13', 'N', '19', '새마을호');
INSERT INTO Seat VALUES ('14', 'N', '19', '새마을호');
INSERT INTO Seat VALUES ('15', 'N', '19', '새마을호');
INSERT INTO Seat VALUES ('16', 'N', '19', '새마을호');


commit;


