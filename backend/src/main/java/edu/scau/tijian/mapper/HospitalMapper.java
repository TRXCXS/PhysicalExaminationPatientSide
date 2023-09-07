package edu.scau.tijian.mapper;

import edu.scau.tijian.pojo.Hospital;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HospitalMapper {

    @Select("SELECT * FROM hospital WHERE state=#{state}")
    public List<Hospital> getHospitalsByState(Integer state);
}
