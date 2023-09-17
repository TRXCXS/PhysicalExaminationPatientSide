package edu.scau.tijian.mapper;

import edu.scau.tijian.pojo.CiReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CiReportMapper {
	@Select("select * from ciReport where orderId=#{orderId} order by cirId")
	List<CiReport> listCiReport(Integer orderId);


}
