package com.fullcourse.tour;

public enum TourRegionTypeEnum {
//    ALL("all","전국")
     SEOUL("서울", "서울특별시")  // 불러올때 코드, 보낼때라벨
    , BUSAN ("부산", "부산광역시")
    , DAEGU ("대구", "대구광역시")
    , INCHEON ("인천", "인천광역시")
    , GWANGJU ("광주", "광주광역시")
    , DAEJEON ("대전", "대전광역시")
    , ULSAN ("울산", "울산광역시")
    , SEJONG ("세종", "세종특별자치시")
    , GYEONGGI ("경기", "경기도")
    , GANGWON ("강원", "강원특별자치도")
    , CHUNGBUN ("충북", "충청북도")
    , CHUNGNAM ("충남", "충청남도") 
    , GYEONBUK ("경북", "경상북도")
    , GYEONNAM ("경남", "경상남도")
    , JEONBUK ("전북", "전북특별자치도")
    , JEONNAM ("전남", "전라남도")
    , JEJU ("제주", "제주특별자치도");

    private String code;
    private String label;

    // default constructor
    TourRegionTypeEnum(String code, String label) {
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
