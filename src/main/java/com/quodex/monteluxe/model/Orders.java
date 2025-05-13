package com.quodex.monteluxe.model;

import com.quodex.monteluxe.dto.OrderItemDTO;
import com.quodex.monteluxe.util.IdGenerator;
import com.quodex.monteluxe.util.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orders {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status; // PENDING, SHIPPED, etc.

    @Column(nullable = false)
    private double totalAmount;

    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItem> items;

    @Column(name = "shipping_address_id")
    private String shippingAddressId;

    @Column(name = "payment_method")
    private String paymentMethod;

    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = IdGenerator.generateOrderId();
        }
        if (this.orderDate == null) {
            this.orderDate = LocalDateTime.now();
        }
    }
}
