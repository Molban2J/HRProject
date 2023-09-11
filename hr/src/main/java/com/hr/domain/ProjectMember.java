package com.hr.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProjectMember {

    int proj_id;        //프로젝트 name    fk
    int m_num;          //참여자 id      fk
    int importance;     //중요도
}
