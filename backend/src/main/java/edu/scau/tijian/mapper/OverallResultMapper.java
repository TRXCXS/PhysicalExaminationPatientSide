package edu.scau.tijian.mapper;

import edu.scau.tijian.pojo.OverallResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;


@Mapper
public interface OverallResultMapper {

	@Select("select * from overallresult where orderId=#{orderId} order by orId")
	List<OverallResult> listOverallResultByOrderId(Integer orderId);
}
