package edu.scau.tijian.mapper;

import edu.scau.tijian.pojo.Checkitem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CheckitemMapper {
    @Select("SELECT * FROM checkitem WHERE ciId = #{ciId}")
    public Checkitem getCheckitemById(Integer ciId);
}
