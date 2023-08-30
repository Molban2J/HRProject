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

    Integer department;  //부서id foreign key

    String name;        //이름

    Position position1;    //직책(팀장 등)

    Position2 position2;        //직급(인턴, 사원, 대리 등)

    MemberStat status;      //재직 상태

    public Member(Integer department, String name, Position position1, Position2 position2, MemberStat status) {
        this.department = department;
        this.name = name;
        this.position1 = position1;
        this.position2 = position2;
        this.status = status;
    }
}
