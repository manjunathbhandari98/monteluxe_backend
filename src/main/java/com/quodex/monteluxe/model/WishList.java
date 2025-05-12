package com.quodex.monteluxe.model;

import com.quodex.monteluxe.util.IdGenerator;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "wishlists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WishList {

    @Id
    private String id;

    @Column(nullable = false)
    private String userId; // Reference to the user who owns this wishlist

    @ElementCollection
    @CollectionTable(name = "wishlist_products", joinColumns = @JoinColumn(name = "wishlist_id"))
    @Column(name = "product_id")
    private List<String> productIds; // Just storing product ID strings

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = IdGenerator.generateGuestCartId().replace("CART", "WISH"); // ML-WISH-XXXX
        }
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}
