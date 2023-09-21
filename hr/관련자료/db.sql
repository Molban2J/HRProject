show databases;
create database hr;
use hr;
ALTER TABLE member
    DROP FOREIGN KEY member_ibfk_1;
ALTER TABLE project
    DROP FOREIGN KEY project_ibfk_1;
ALTER TABLE projectmember
    DROP FOREIGN KEY projectmember_ibfk_2;
ALTER TABLE projectmember
    DROP FOREIGN KEY projectmember_ibfk_1;
ALTER TABLE LineChart
    DROP FOREIGN KEY linechart_ibfk_1;
ALTER TABLE DWLMember
    DROP FOREIGN KEY dwlmember_ibfk_2;
ALTER TABLE dwlmember
    DROP FOREIGN KEY dwlmember_ibfk_1;
drop table Department;
drop table Member;
drop table Project;
drop table ProjectMember;
drop table LineChart;
drop table Position1;
drop table Position2;
drop table DesignWaitingList;
drop table DWLMember;
#테이블 생성 시작
create table Member
(
    m_num      int primary key auto_increment,
    department varchar(100),
    name       varchar(50),
    position1  varchar(100),
    position2  varchar(100),
    status     varchar(10),
    job_field  varchar(100)
);

create table Department
(
    dept_id   int primary key auto_increment,
    dept_name varchar(100) unique key not null
);

create table Position1
(
    position varchar(100) primary key
);

create table Position2
(
    position varchar(100) primary key
);

create table Project
(
    proj_id    int primary key auto_increment,
    proj_name  varchar(200) unique key not null,
    category   int,
    budget     int,
    start_date DATE default (current_date),
    end_date   DATE
);

create table ProjectMember
(
    proj_id    int,
    m_num      int,
    importance int
);

create table ProjCategory
(
    cat_id   int primary key auto_increment,
    category varchar(50)
);

create table LineChart
(
    chart_seq  int primary key auto_increment,
    m_num      int,
    importance int,
    chart_date DATE default (current_date)
);

create table DesignWaitingList
(
    dw_seq     int primary key auto_increment,
    proj_name  varchar(200),
    priority   int,
    start_date DATE,
    end_date   DATE,
    progress   int CHECK (progress IN (0, 20, 40, 60, 80, 100))
);

create table DWLMember
(
    dw_seq int,
    m_num  int
);

create table user
(
    id       varchar(100) primary key,
    password varchar(100),
    role     varchar(20) check ( role in ("ADMIN", "USER"))
);

create table DailyPlan
(
    dp_seq    int auto_increment primary key,
    time      timestamp default now(),
    plan_name VARCHAR(500)
);

create table DailyPlanMember
(
    dp_seq int,
    m_num  int
);

create table DailyPlanDesc
(
    dpd_seq     int auto_increment primary key,
    time        timestamp default now(),
    description varchar(500)
);
#테이블 생성 끝

#외래키 설정 시작
alter table Member
    add constraint foreign key (department) references Department (dept_name)
        on delete set null
        on update cascade;

alter table Member
    add constraint foreign key (position1) references Position1 (position)
        on delete set null
        on update set null;

alter table Member
    add constraint foreign key (position2) references Position2 (position)
        on delete set null
        on update set null;

alter table ProjectMember
    add constraint foreign key (proj_id) references Project (proj_id)
        on delete cascade
        on update cascade;

alter table ProjectMember
    add constraint foreign key (m_num) references Member (m_num)
        on delete cascade
        on update cascade;

alter table LineChart
    add constraint foreign key (m_num) references Member (m_num)
        on delete cascade
        on update cascade;

alter table DWLMember
    add constraint foreign key (m_num) references Member (m_num)
        on delete cascade
        on update cascade;

alter table DWLMember
    add constraint foreign key (dw_seq) references DesignWaitingList (dw_seq)
        on delete cascade
        on update cascade;

alter table DailyPlanMember
    add constraint foreign key (dp_seq) references DailyPlan (dp_seq)
        on delete cascade
        on update cascade;

alter table DailyPlanMember
    add constraint foreign key (m_num) references Member (m_num)
        on delete cascade
        on update cascade;

alter table Project
    add constraint foreign key (category) references ProjCategory (cat_id)
        on delete set null
        on update cascade;

#외래키 설정 끝

#Position1 데이터 주입 시작
insert into Position1(position)
values ("스탭");
insert into Position1(position)
values ("팀장");
insert into Position1(position)
values ("본부장");
#Position1 데이터 주입 끝

#Position2 데이터 주입 시작
insert into Position2(position)
values ("인턴");
insert into Position2(position)
values ("사원");
insert into Position2(position)
values ("대리");
insert into Position2(position)
values ("과장");
insert into Position2(position)
values ("차장");
insert into Position2(position)
values ("본부장");
#Position2 데이터 주입 끝

#Department 데이터 주입 시작
insert into Department(dept_name)
values ("기획팀");
insert into Department(dept_name)
values ("개발팀");
insert into Department(dept_name)
values ("총괄팀");
insert into Department(dept_name)
values ("설계팀");
insert into Department(dept_name)
values ("영업기획팀");
#Department 데이터 주입 끝

#Member 데이터 주입 시작
#본부장
insert into Member (name, position1, position2, status)
values ("이소일", "본부장", "본부장", "재직중");

#영업기획
insert into Member (department, name, position1, position2, status)
values ("영업기획팀", "조문희", "팀장", "차장", "재직중");
#기획
insert into Member (department, name, position1, position2, status)
values ("기획팀", "최형규", "팀장", "과장", "재직중");
insert into Member (department, name, position1, position2, status, job_field)
values ("기획팀", "장용수", "스탭", "대리", "재직중", "4D");
insert into Member (department, name, position1, position2, status, job_field)
values ("기획팀", "박정우", "스탭", "대리", "재직중", "3D");
insert into Member (department, name, position1, position2, status, job_field)
values ("기획팀", "김은종", "스탭", "사원", "재직중", "2D");

#설계
insert into Member (department, name, position1, position2, status)
values ("설계팀", "유재훈", "팀장", "사원", "재직중");
insert into Member (department, name, position1, position2, status, job_field)
values ("설계팀", "임소영", "스탭", "대리", "재직중", "Micro-CT");
insert into Member (department, name, position1, position2, status, job_field)
values ("설계팀", "민슬기", "스탭", "대리", "재직중", "VR/AR/HCI");
insert into Member (department, name, position1, position2, status, job_field)
values ("설계팀", "김도연", "스탭", "사원", "재직중", "Unreal");

#개발
insert into Member (department, name, position1, position2, status, job_field)
values ("개발팀", "이인석", "스탭", "인턴", "재직중", "Machine Learning");
insert into Member (department, name, position1, position2, status, job_field)
values ("개발팀", "임종민", "스탭", "인턴", "재직중", "Back-End");
insert into Member (department, name, position1, position2, status, job_field)
values ("개발팀", "오승찬", "스탭", "인턴", "재직중", "Unity 3D");

#제작
insert into Member (department, name, position1, position2, status, job_field)
values ("총괄팀", "김용희", "스탭", "차장", "재직중", "QA");
insert into Member (department, name, position1, position2, status, job_field)
values ("총괄팀", "유황재", "스탭", "과장", "재직중", "2D");
insert into Member (department, name, position1, position2, status, job_field)
values ("총괄팀", "문예담", "스탭", "사원", "재직중", "GPT");
#Member 데이터 주입 끝

#ProjCategory 데이터 주입
insert into ProjCategory (category)
values ("행정");
insert into ProjCategory (category)
values ("연구 개발");
insert into ProjCategory (category)
values ("자체 개발");
insert into ProjCategory (category)
values ("용역");
insert into ProjCategory (category)
values ("마케팅");
#ProjCategory 데이터 주입끝

#Project 데이터 주입
insert into project (proj_name, start_date, category)
values ("해부VR", "2023-08-01", 4);
insert into project (proj_name, start_date)
values ("브래이킹 댄스", "2023-07-01");
insert into project (proj_name, start_date, category)
values ("숨플", "2023-04-01", 3);
insert into project (proj_name, start_date, category)
values ("제주 수목원", "2023-08-10", 4);
#Project 데이터 주입끝

#ProjectMember 데이터 주입
insert into ProjectMember (proj_id, m_num, importance)
values (1, 5, 20);
insert into ProjectMember (proj_id, m_num, importance)
values (1, 10, 50);
insert into ProjectMember (proj_id, m_num, importance)
values (1, 3, 70);
insert into ProjectMember (proj_id, m_num, importance)
values (1, 1, 100);
insert into ProjectMember (proj_id, m_num, importance)
values (2, 11, 80);
insert into ProjectMember (proj_id, m_num, importance)
values (2, 16, 30);
insert into ProjectMember (proj_id, m_num, importance)
values (3, 6, 90);
insert into ProjectMember (proj_id, m_num, importance)
values (3, 5, 50);
insert into ProjectMember (proj_id, m_num, importance)
values (3, 9, 50);
insert into ProjectMember (proj_id, m_num, importance)
values (4, 7, 50);
insert into ProjectMember (proj_id, m_num, importance)
values (4, 14, 60);
insert into ProjectMember (proj_id, m_num, importance)
values (4, 12, 90);
#ProjectMember 데이터 주입끝

#LineChart 임시 데이터 주입 시작
insert into LineChart (m_num, importance, chart_date)
values (1, (select importance from ProjectMember pm where pm.m_num = 1), NOW());

#delete from LineChart where m_num = 5;

INSERT INTO LineChart (m_num, importance, chart_date)
SELECT 5, SUM(importance), NOW()
FROM ProjectMember
WHERE m_num = 5
GROUP BY m_num;

drop procedure loopInsert;

DELIMITER $$
CREATE PROCEDURE loopInsert()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 25
        DO
            INSERT INTO LineChart (m_num, importance, chart_date)
            SELECT 5, SUM(importance), CONCAT("2023-08-", CAST(i AS CHAR))
            FROM ProjectMember
            WHERE m_num = 5
            GROUP BY m_num;
            SET i = i + 1;
        END WHILE;
END$$
DELIMITER ;
call loopInsert();
drop procedure loopInsert2;
DELIMITER $$
CREATE PROCEDURE loopInsert2()
BEGIN
    DECLARE i INT DEFAULT 5;
    WHILE i <= 25
        DO
            INSERT INTO LineChart (m_num, importance, chart_date)
            SELECT 1, SUM(importance), CONCAT("2023-08-", CAST(i AS CHAR))
            FROM ProjectMember
            WHERE m_num = 1
            GROUP BY m_num;
            SET i = i + 1;
        END WHILE;
END$$
DELIMITER ;
call loopInsert2();
#delete from LineChart;
#LineChart 임시 데이터 주입 끝

#DWL 임시 데이터 주입 시작
insert into DesignWaitingList
    (proj_name, priority, start_date, progress)
values ("해부VR", 1, (current_date), 40);
insert into DesignWaitingList
    (proj_name, priority, start_date, progress)
values ("김천 수변공원", 2, (current_date), 80);
insert into DesignWaitingList
    (proj_name, priority, start_date, progress)
values ("숨플", 4, (current_date), 20);
insert into DesignWaitingList
    (proj_name, priority, start_date, progress)
values ("테스트", 6, (current_date), 40);
insert into DesignWaitingList
    (proj_name, priority, start_date, progress)
values ("test", 3, (current_date), 0);
insert into DesignWaitingList
    (proj_name, priority, start_date, progress)
values ("test3", 7, (current_date), 60);
#DWL 임시 데이터 주입 끝

#DWLM 임시 데이터 주입 시작
insert into DWLMember
    (dw_seq, m_num)
values (1, 5);
insert into DWLMember
    (dw_seq, m_num)
values (1, 10);
insert into DWLMember
    (dw_seq, m_num)
values (2, 5);
insert into DWLMember
    (dw_seq, m_num)
values (5, 12);
insert into DWLMember
    (dw_seq, m_num)
values (5, 6);
insert into DWLMember
    (dw_seq, m_num)
values (4, 5);
insert into DWLMember
    (dw_seq, m_num)
values (6, 5);
insert into DWLMember
    (dw_seq, m_num)
values (6, 3);
insert into DWLMember
    (dw_seq, m_num)
values (2, 9);

#DWLM 임시 데이터 주입 끝
#데이터 주입 끝

#delete from LineChart where chart_date = now();
#delete from Member;
select *
from department;
select*
from member;
select *
from project;
select *
from ProjectMember;
select *
from LineChart;
select *
from LineChart
order by chart_date;
select *
from Position1;
select *
from DWLMember;
select *
from user;

