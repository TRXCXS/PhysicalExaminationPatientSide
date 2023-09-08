package edu.scau.tijian.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import edu.scau.tijian.pojo.Hospital;

import java.util.List;

@Mapper
public interface HospitalMapper {

	@Select("SELECT * FROM hospital WHERE state=#{state}")
	public List<Hospital> getHospitalsByState(Integer state);

	//查询正常营业的医院
	@Select("select * from hospital where state=#{state} order by hpId")
	public List<Hospital> listHospital(Integer state);
	//根据主键查询医院信息
	@Select("select * from hospital where hpId=#{hpId}")
	public Hospital getHospitalById(Integer hpId);
}
