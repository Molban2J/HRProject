package com.hr.domain;

public enum Position1 {  //직책
    TEAMMANAGER("팀장"),    //팀장
    STAFF("스탭"),           //스탭
    HM("본부장")                 //본부장
    ;

    private String position;
    Position1(String position) {
        this.position = position;
    }

    public String getPosition1() {
        return position;
    }
}
