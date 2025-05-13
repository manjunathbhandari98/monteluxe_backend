package com.quodex.monteluxe.dto;

import com.quodex.monteluxe.model.OrderItem;
import com.quodex.monteluxe.util.OrderStatus;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private String id;
    private String userId;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private double totalAmount;
    private String shippingAddressId;
    private String paymentMethod;
    private List<OrderItemDTO> items;
}

