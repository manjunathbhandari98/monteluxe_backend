package com.quodex.monteluxe.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class CartItem {

    private String productId;
    private String productName;
    private int quantity;
    private double price;
    private String image; // optional - for quick access/display in frontend
}
