package com.fullcourse.festival;

public enum FestivalDurationTypeEnum {
//    ALL("all","전국")
     JAN("1월", "202401")  // 불러올때 코드, 보낼때라벨
    , FEB ("2월", "202402")
    , MAR ("3월", "202403")
    , APR ("4월", "202404")
    , MAY ("5월", "202405")
    , Jun ("6월", "202406")
    , Jul ("7월", "202407")
    , Aug ("8월", "202408")
    , Sept ("9월", "202409")
    , Oct ("10월", "202410")
    , Nov ("11월", "202411")
    , Dec ("12월", "202412"); 
    
    private String code;
    private String label;

    // default constructor
    FestivalDurationTypeEnum(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return this.code;
    }

    public String getLabel() {
        return this.label;
    }
}
