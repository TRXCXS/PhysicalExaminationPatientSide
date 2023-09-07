package edu.scau.tijian.mapper;

import edu.scau.tijian.pojo.Checkitem;
import edu.scau.tijian.pojo.Setmeal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SetmealMapper {


    List<Setmeal> getSetmealByType(Integer type);
}
