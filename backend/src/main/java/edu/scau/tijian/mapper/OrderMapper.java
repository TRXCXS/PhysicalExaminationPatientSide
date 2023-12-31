package edu.scau.tijian.mapper;

import edu.scau.tijian.dto.CalendarResponseDto;
import edu.scau.tijian.dto.OrdersMapperDto;
import edu.scau.tijian.pojo.Orders;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {

    @Select("SELECT COUNT(*) " +
            "FROM orders " +
            "WHERE state = 1 AND userId = #{userId}")
    Integer getUnarchivedOrderCountByUserId(String userId);

    //根据parameList参数，查询30天预约日期中，每一天的已预约人数
    List<CalendarResponseDto> listOrdersAppointmentNumber(List<OrdersMapperDto> list);

    @Insert("INSERT INTO orders VALUES(" +
            "null, #{orderDate}, " +
            "#{userId}, #{hpId}, " +
            "#{smId}, 1" +
            ")")
    int saveOrders(Orders orders);

    List<Orders> listOrdersByUserByState(Orders orders);

    @Delete("DELETE FROM orders WHERE orderId=#{orderId}")
    int deleteOrderByOrderId(int orderId);

    List<Orders> listOrdersWithHospitalByUserByState(Orders orders);
}
