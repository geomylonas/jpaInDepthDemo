INSERT  INTO Course (ID,FULLNAME,CREATED_TIME,UPDATED_TIME) VALUES (1000,'JPA CLASS',sysdate(),sysdate());
INSERT  INTO Course (ID,FULLNAME,CREATED_TIME,UPDATED_TIME) VALUES (1001,'SRPING CLASS',sysdate(),sysdate());
INSERT  INTO Course (ID,FULLNAME,CREATED_TIME,UPDATED_TIME) VALUES (1002,'HIBERNATE CLASS',sysdate(),sysdate());
INSERT  INTO Course (ID,FULLNAME,CREATED_TIME,UPDATED_TIME) VALUES (1003,'EMPTY CLASS',sysdate(),sysdate());

INSERT INTO PASSPORT(ID,NUMBER) VALUES(40001,'A12345');
INSERT INTO PASSPORT(ID,NUMBER) VALUES(40002,'B12345');
INSERT INTO PASSPORT(ID,NUMBER) VALUES(40003,'C12345');

INSERT INTO STUDENT(ID,NAME,PASSPORT_ID) VALUES(20001,'GEORGE',40001);
INSERT INTO STUDENT(ID,NAME,PASSPORT_ID) VALUES(20002,'JIM',40002);
INSERT INTO STUDENT(ID,NAME,PASSPORT_ID) VALUES(20003,'PETROS',40003);



INSERT INTO REVIEW(ID,RATING,DESCRIPTION,COURSE_ID) VALUES(50001,'5','GREAT CLASS',1000);
INSERT INTO REVIEW(ID,RATING,DESCRIPTION,COURSE_ID) VALUES(50002,'5','AWESOME CLASS',1001);
INSERT INTO REVIEW(ID,RATING,DESCRIPTION,COURSE_ID) VALUES(50003,'5','INCREDEBLE CLASS',1002);

INSERT INTO STUDENT_COURSE(STUDENT_ID,COURSE_ID) VALUES(20001,1000);
INSERT INTO STUDENT_COURSE(STUDENT_ID,COURSE_ID) VALUES(20001,1002);
INSERT INTO STUDENT_COURSE(STUDENT_ID,COURSE_ID) VALUES(20001,1001);
INSERT INTO STUDENT_COURSE(STUDENT_ID,COURSE_ID) VALUES(20002,1000);
INSERT INTO STUDENT_COURSE(STUDENT_ID,COURSE_ID) VALUES(20003,1002);