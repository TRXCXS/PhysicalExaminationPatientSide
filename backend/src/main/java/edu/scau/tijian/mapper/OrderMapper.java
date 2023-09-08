package edu.scau.tijian.mapper;

import edu.scau.tijian.dto.CalendarResponseDto;
import edu.scau.tijian.dto.OrdersMapperDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {

    @Select("SELECT COUNT(1) " +
            "FROM orders " +
            "WHERE state = 1 AND userId = #{userId}")
    Integer getUnarchivedOrderCountByUserId(String userId);

    //根据parameList参数，查询30天预约日期中，每一天的已预约人数
    List<CalendarResponseDto> listOrdersAppointmentNumber(List<OrdersMapperDto> list);
}
