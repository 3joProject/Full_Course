package com.fullcourse.tour.like;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourLikeVO {
	
	private int tourLikeNum;
	private String tourLikeCategory;
	private int tourLikeMemberNum;
	private int tourLikeTourNum;

}
