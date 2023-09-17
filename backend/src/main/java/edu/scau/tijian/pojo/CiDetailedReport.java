package edu.scau.tijian.pojo;

import lombok.Data;

@Data
public class CiDetailedReport {

	private Integer cidrId;              //检查项明细报告主键
	private String name;                 //检查项明细名称
	private String unit;                 //检查项明细单位
	private Double minrange;             //检查项细明正常值范围中的最小值
	private Double maxrange;             //检查项细明正常值范围中的最大值
	private String normalValue;          //检查项细明正常值（非数字型）
	private String normalValueString;    //检查项验证范围说明文字
	private Integer type;                //检查项明细类型（1：数值围范验证型；2：数值相等验证型；3：无需验证型；4：描述型；5：其它）
	private String value;                //检查项目明细值
	private Integer isError;             //此项是否异常（0：无异常；1：异常）
	private Integer ciId;                //所属检查项报告编号
	private Integer orderId;             //所属预约编号
}
