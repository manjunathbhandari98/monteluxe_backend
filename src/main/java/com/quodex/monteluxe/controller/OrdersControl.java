package com.quodex.monteluxe.controller;

import com.quodex.monteluxe.dto.OrderDTO;
import com.quodex.monteluxe.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/orders")
public class OrdersControl {

    private final OrderService orderService;

    public OrdersControl(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<OrderDTO> placeOrder(@PathVariable String userId, @RequestBody OrderDTO orderDTO){
        try {
            OrderDTO placedOrder = orderService.placeOrder(userId, orderDTO);
            return ResponseEntity.ok(placedOrder);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
