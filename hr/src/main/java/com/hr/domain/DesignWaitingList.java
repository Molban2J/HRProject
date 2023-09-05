package com.hr.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DesignWaitingList {
    int dw_seq;
    String proj_name;
    int priority;
    Date start_date;
    Date end_date;
    int progress;
}
