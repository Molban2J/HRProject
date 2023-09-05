package com.hr.domain;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {

    int m_num;          //primary key

    String department;  //부서id foreign key

    String name;        //이름

    String position1;    //직책(팀장 등)

    String position2;        //직급(인턴, 사원, 대리 등)

    String status;      //재직 상태

    String job_field;   //직무 분야
    public Member(String department, String name, String position1, String position2, String status, String job_field) {
        this.department = department;
        this.name = name;
        this.position1 = position1;
        this.position2 = position2;
        this.status = status;
        this.job_field = job_field;
    }
}
