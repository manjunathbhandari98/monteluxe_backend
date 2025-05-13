package com.quodex.monteluxe.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static com.quodex.monteluxe.util.IdGenerator.generateCategoryId;
import static com.quodex.monteluxe.util.IdGenerator.generateUserId;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @Column(name = "category_id", nullable = false, unique = true)
    private String id;

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

    @PrePersist
    public void generateId() {
        if (this.id == null || this.id.isEmpty()) {
            this.id = generateCategoryId();
        }
    }

}
