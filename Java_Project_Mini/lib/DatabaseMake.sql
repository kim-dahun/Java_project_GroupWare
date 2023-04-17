CREATE TABLE account 
(eid number(6),
id varchar2(20 char) NOT NULL,
password varchar2(40 char) NOT NULL,
name varchar(20 char) NOT NULL
);

ALTER TABLE account
ADD phone varchar(20 char) default '010-0000-0000',
ADD email varchar2(30 char) default 'sangwork@sangsang.com';

ALTER TABLE account
ADD (dept_no number(3),
dept_name varchar(100),
emp_no varchar2(10 char),
pos_no number(4),
pos_name varchar2(40 char),
isadmin varchar2(10 char) default 'false');

// Account 테이블 생성

SELECT * FROM account;

ALTER TABLE account
modify 
email unique;

ALTER TABLE account
modify
isadmin check (isadmin in ('false','true'));
