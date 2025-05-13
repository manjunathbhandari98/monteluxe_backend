package com.quodex.monteluxe.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class OrderItem {

    private String productId;
    private int quantity;
    private double price;
}
