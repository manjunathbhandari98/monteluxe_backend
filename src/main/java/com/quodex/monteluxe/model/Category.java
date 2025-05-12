package com.quodex.monteluxe.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static com.quodex.monteluxe.util.IdGenerator.generateCategoryId;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @Column(name = "category_id", nullable = false, unique = true)
    private String id = generateCategoryId();

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @ElementCollection
    @CollectionTable(name = "category_features", joinColumns = @JoinColumn(name = "category_id"))
    @Column(name = "feature")
    private List<String> features;

    private String startingPrice;

    private String image;

    private String link;

}
