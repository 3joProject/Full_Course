package com.fullcourse.wishlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishlistVO {

	private int wishListNum;
	private int wishListId;
	private int wishListTourId;
	private int wishListFestivalId;
	
}
