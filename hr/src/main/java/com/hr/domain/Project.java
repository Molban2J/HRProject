package com.hr.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Project {

    int proj_id;

    String proj_name;   //프로젝트 명
    //ProjectStat stat;   //프로젝트 상태(진행중,완료)
    Date start_date;   //시작 날짜
    Date end_date;     //종료 날짜
    Integer proj_head;   //프로젝트 책임자 id  fk
}
