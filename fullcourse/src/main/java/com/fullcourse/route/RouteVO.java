package com.fullcourse.route;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RouteVO {
	
	private int routeNum;
	private String routeTraffic;
	private String routeTime;
	private String routeStartLocation;
	private String routeEndLocation;
	private String userId;
	private double startlatitude;
	private double startlongitude;
	private double endlatitude;
	private double endlongitude;
	private double routeDistance;
	private int routeOrder;
	
	
}
