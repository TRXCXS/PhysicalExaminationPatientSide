package edu.scau.tijian.controller;

import edu.scau.tijian.mapper.OrderMapper;
import edu.scau.tijian.pojo.Orders;
import edu.scau.tijian.pojo.User;
import edu.scau.tijian.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private final OrderService orderService;

    @PostMapping("/unarchived-order-count")
    public Integer getUnarchivedOrderCountByUserId(User user) {
        return orderService.getUnarchivedOrderCountByUserId(user.getUserId());
    }

    @RequestMapping("/saveOrders")
    public int saveOrders(@RequestBody Orders orders) {
        return orderService.saveOrders(orders);
    }
}
