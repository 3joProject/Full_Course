package com.fullcourse.route;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RouteVO {
	
	private int routeNum;
	private String routeName;
	private String routeUserId;
	private String routeTraffic;
	private String routeTime;
	private String routeStartLocation;
	private String routeEndLocation;
	//private String routeUserId;
	private double routeStartLatitude;
	private double routeStartLongitude;
	private double routeEndLatitude;
	private double routeEndLongitude;
	private double routeDistance;
	private int routeOrder;
	
}
