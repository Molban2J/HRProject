package com.hr.domain;

import lombok.*;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LineChart {
    int chart_seq;  //차트 시퀀스 no
    int m_num;  //멤버 식별id
    int importance; //중요도(누적)
    Date chart_date;  //날짜
}
