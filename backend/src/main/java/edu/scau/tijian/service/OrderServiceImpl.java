package edu.scau.tijian.service;

import edu.scau.tijian.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Autowired
    private final OrderMapper orderMapper;

    @Override
    public Integer getUnarchivedOrderCountByUserId(String userId) {
        return orderMapper.getUnarchivedOrderCountByUserId(userId);
    }
}
