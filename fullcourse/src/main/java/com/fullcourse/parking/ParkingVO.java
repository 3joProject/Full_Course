package com.fullcourse.parking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingVO {

//	prkSttusInfo 테이블 컬럼
	private String prkCenterId;
	private String prkPlceNm;
	private String prkPlceAdres;
	private String prkPlceEntrcLa;
	private String prkPlceEntrcLo;
	private String prkCmprtCo;
	
//	prkOprInfo 테이블 컬럼
//	private String prkInfoId;
//	private String sunOpenTime;
//	private String sunEndTime;
//	private String monOpenTime;
//	private String monEndTime;
//	private String tueOpenTime;
//	private String tueEndTime;
//	private String wedOpenTime;
//	private String wedEndTime;
//	private String thuOpenTime;
//	private String thuEndTime;
//	private String friOpenTime;
//	private String friEndTime;
//	private String satOpenTime;
//	private String satEndTime;
//	private String holidayOpenTime;
//	private String holidayEndTime;
//	private String opertnBsFreeTime;
//	private String parkingChrgeBsTime;
//	private int parkingChrgeBsChrg;
//	private int parkingChrgeAditUnitTime;
//	private int parkingChrgeAditUnitChrge;
//	private int parkingChrgeOnedayChrge;
//	private int parkingChrgeMonUnitchrg;
	
//	prkOprInfo 테이블 컬럼
	private String prkRealTimeId;
	private String pkfcParkingLotsTotal;
	private String pkfcAvailableParkingLotsTotla;
	

	
	
	
}
