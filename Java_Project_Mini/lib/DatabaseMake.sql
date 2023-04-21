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


CREATE TABLE deptlist (deptno number(3), deptName varchar2(100 char), deptref number(3));

ALTER TABLE deptlist RENAME COLUMN deptref TO deptnodeno;

ALTER TABLE deptlist MODIFY deptno primary key;

ALTER TABLE deptlist MODIFY deptname UNIQUE;

ALTER TABLE account ADD foreign key (dept_no) references deptlist(deptno);

CREATE TABLE position (
posno number(3) CONSTRAINT ps_posno_pk primary key,
posname varchar(100 char) CONSTRAINT ps_posname_uq UNIQUE
);

CREATE TABLE paymentContent(

    name varchar(10 char),
    empno varchar(10 char) CONSTRAINT pmc_empno_pk PRIMARY KEY);
    
ALTER TABLE paymentcontent ADD (
    basesal number(20) default 0);
    
   -- ALTER TABLE paymentcontent ADD check (basesal >= 0 ) (basesal);
    ALTER TABLE paymentcontent ADD (
    bonus number(20) check(bonus>=0)); 
    
    
    ALTER TABLE paymentcontent ADD (
    overtimepay number(20) default 0,
    meals number(20) default 0);
    
    
    
    ALTER TABLE paymentcontent ADD (
    vehicleMaintenance number(20) default 0,
    severancepay number(20) default 0,
    incometax number(20) default 0,
    residenttax number(20) default 0,
    healthInsurancePremium number(20) default 0,
    nationalPension number(20) default 0,
    employmentInsurance number(20) default 0,
    otherdeductions number(20) default 0,
    deductionsreason varchar(100)
    
);


ALTER TABLE account ADD CONSTRAINT acc_posno_fk foreign key (pos_no) references position(posno);

ALTER TABLE paymentcontent ADD CONSTRAINT pay_empno_fk foreign key (empno) references account(emp_no);

ALTER TABLE account ADD CONSTRAINT acc_deptno_fk foreign key (dept_no) references deptlist(deptno);



CREATE TABLE schedule (
    empno varchar(10 char) CONSTRAINT sch_empno_pk primary key,
    startday timestamp NOT NULL,
    endday timestamp NOT NULL,
    title varchar(100 char),
    content varchar(1000 char)
    
);

ALTER TABLE schedule ADD CONSTRAINT sch_empno_fk foreign key (empno) references account(emp_no);

ALTER TABLE schedule ADD (contentno number(10));
