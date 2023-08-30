package com.hr.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Importance {
    int m_num;        //사원 id     fk
    int importance;     //누적 중요도
}
