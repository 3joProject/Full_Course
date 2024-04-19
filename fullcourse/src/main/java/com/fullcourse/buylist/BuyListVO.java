package com.fullcourse.buylist;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyListVO {

	private int buyNum;
    private int buyMnum;
    private int buyId;
    private int buyInventory;
    private int buyTotalPrice;
    private Date buyDate;
    private BuyStatus buyStatus;
	
    public enum BuyStatus {
        결제완료, 결제대기, 결제취소
    }
}
