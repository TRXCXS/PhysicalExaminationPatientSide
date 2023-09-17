package edu.scau.tijian.pojo;

import lombok.Data;

import java.util.List;

@Data
public class CiReport {

	private Integer cirId;
	private Integer ciId;
	private String ciName;
	private Integer orderId;
	//一对多
	private List<CiDetailedReport> detailedreportList;
}
