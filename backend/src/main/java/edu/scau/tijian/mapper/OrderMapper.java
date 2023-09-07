package edu.scau.tijian.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderMapper {

    @Select("SELECT COUNT(1) " +
            "FROM orders " +
            "WHERE state = 1 AND userId = #{userId}")
    Integer getUnarchivedOrderCountByUserId(String userId);


}
