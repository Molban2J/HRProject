package com.hr.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Optional;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    int dept_id;    //부서 id

    String dept_name;   //부서명

    Integer dept_head;   //부서장   fk
}
