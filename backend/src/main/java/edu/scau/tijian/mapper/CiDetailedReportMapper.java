package edu.scau.tijian.mapper;

import edu.scau.tijian.pojo.CiDetailedReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;


@Mapper
public interface CiDetailedReportMapper {
	@Select("select * from CiDetailedReport where orderId=#{orderId}")
	List<CiDetailedReport> listCiDetailedReportByOrderIdByCiId(CiDetailedReport ciDetailedReport);
}
