package edu.scau.tijian.controller;

import edu.scau.tijian.pojo.Orders;
import edu.scau.tijian.pojo.User;
import edu.scau.tijian.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private final OrderService orderService;
    Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @PostMapping("/unarchived-order-count")
    public Integer getUnarchivedOrderCountByUserId(@RequestBody User user) {
        LOGGER.info(user.toString());
        return orderService.getUnarchivedOrderCountByUserId(user.getUserId());
    }

    @RequestMapping("/saveOrders")
    public int saveOrders(@RequestBody Orders orders) {
        return orderService.saveOrders(orders);
    }

    @RequestMapping("/listOrdersByUserIdByState")
    public List<Orders> listOrdersByUserIdByState(@RequestBody Orders orders) {
        return orderService.listOrdersByUserByState(orders);
    }

    @RequestMapping("/cancelOrders")
    public int cancelOrders(@RequestBody Orders orders) {
        return orderService.deleteOrderByOrderId(orders.getOrderId());
    }

    @RequestMapping("/listCireport")
    public List<Orders> listOrdersWithHospitalByUserByState(@RequestBody Orders orders) {
        return orderService.listOrdersWithHospitalByUserByState(orders);
    }
}
