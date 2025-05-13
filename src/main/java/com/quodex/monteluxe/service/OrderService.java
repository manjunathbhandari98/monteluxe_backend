package com.quodex.monteluxe.service;

import com.quodex.monteluxe.dto.OrderDTO;

public interface OrderService {
    OrderDTO placeOrder(String userId, OrderDTO orderDTO);
}
