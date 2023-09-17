package edu.scau.tijian.service;

import edu.scau.tijian.pojo.Orders;

import java.util.List;

public interface OrderService {
    Integer getUnarchivedOrderCountByUserId(String userId);

    int saveOrders(Orders orders);

    List<Orders> listOrdersByUserByState(Orders orders);

    int deleteOrderByOrderId(int orderId);

    List<Orders> listOrdersWithHospitalByUserByState(Orders orders);
}
