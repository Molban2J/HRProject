package com.hr.domain;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Criteria {
    private int pageNum;

    private int amount;

    public Criteria() {
        this(1, 5);
    }

}
