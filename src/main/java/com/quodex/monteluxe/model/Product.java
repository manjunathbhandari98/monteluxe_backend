package com.quodex.monteluxe.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static com.quodex.monteluxe.util.IdGenerator.generateCategoryId;
import static com.quodex.monteluxe.util.IdGenerator.generateProductCode;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @Column(name = "product_id")
    private String id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private String image; // Primary product image URL

    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url")
    private List<String> images; // List of additional images (if any)

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String caseSize;

    @Column(nullable = false)
    private String caseMaterial;

    @Column(nullable = false)
    private String crystalType;

    @Column(nullable = false)
    private String waterResistance;

    @Column(nullable = false)
    private String movement;

    @Column(nullable = false)
    private String strapMaterial;

    @ElementCollection
    @CollectionTable(name = "product_features", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "feature")
    private List<String> features; // Product features

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @PrePersist
    public void generateId() {
        if (this.id == null || this.id.isEmpty()) {
            this.id = generateProductCode();
        }
    }
}
