package com.fullcourse.festival.like;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FestivalLikeVO {
	
	private int festivalLikeNum;
	private String festivalLikeCategory;
	private int festivalLikeMemberNum;
	private int festivalLikeFestivalNum;

}
