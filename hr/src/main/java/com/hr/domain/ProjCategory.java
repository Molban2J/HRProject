package com.hr.domain;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjCategory {
    int cat_id;     //카테고리 id pk
    String category;    //카테고리 명
}
