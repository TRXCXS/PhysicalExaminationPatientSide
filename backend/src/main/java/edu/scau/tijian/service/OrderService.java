package edu.scau.tijian.service;

import edu.scau.tijian.pojo.Orders;

public interface OrderService {
    Integer getUnarchivedOrderCountByUserId(String userId);

    int saveOrders(Orders orders);
}
