package com.fullcourse.admin.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportVO {

	private int reportNum;
	private String reportCategory;
	private String reportContent;
	private String reportReportedId;
	private String reportReportId;
	private String reportReview;
	private String reportStatus;
	
}
