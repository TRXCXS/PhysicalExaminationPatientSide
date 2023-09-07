package edu.scau.tijian.mapper;

import edu.scau.tijian.pojo.Setmealdetailed;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface SetmealdetailedMapper {
    List<Setmealdetailed> getSetmealdetailedBySmId(Integer smId);
}
