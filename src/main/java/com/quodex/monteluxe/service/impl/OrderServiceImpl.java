package com.quodex.monteluxe.service.impl;

import com.quodex.monteluxe.dto.OrderDTO;
import com.quodex.monteluxe.mapper.OrderMapper;
import com.quodex.monteluxe.model.Orders;
import com.quodex.monteluxe.model.User;
import com.quodex.monteluxe.repository.OrderRepository;
import com.quodex.monteluxe.repository.UserRepository;
import com.quodex.monteluxe.service.OrderService;
import lombok.AllArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;


    @Override
    public OrderDTO placeOrder(String userId, OrderDTO orderDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
        orderDTO.setUserId(userId);
        Orders orders = orderMapper.toEntity(orderDTO);
        Orders newOrder = orderRepository.save(orders);
        return orderMapper.toDTO(newOrder);
    }
}
