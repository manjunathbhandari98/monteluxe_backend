package com.quodex.monteluxe.model;

import com.quodex.monteluxe.util.IdGenerator;
import com.quodex.monteluxe.util.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orders {

    @Id
    private String id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private OrderStatus status; // e.g., PENDING, SHIPPED, DELIVERED, CANCELLED

    @Column(nullable = false)
    private double totalAmount;

    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItem> items;

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
