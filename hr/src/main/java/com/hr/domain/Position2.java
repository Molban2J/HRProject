package com.hr.domain;

public enum Position2 {  //직급
    INTERN("인턴"), //인턴
    STAFF("사원"),  //사원
    AM("대리"),     //대리 (Assistant Manager)
    GM("과장"),     //과장 (General Manager)
    DGM("차장"),    //차장 (Deputy General Manager)
    HM("본부장")      //본부장 (Head Manager)
    ;

    private String position2;
    Position2(String position2) {
        this.position2 = position2;
    }

    public String getPosition2() {
        return position2;
    }
}
