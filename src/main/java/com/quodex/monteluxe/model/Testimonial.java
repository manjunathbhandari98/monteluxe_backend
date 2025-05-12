package com.quodex.monteluxe.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "testimonials")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Testimonial {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    private String location;

    @Column(nullable = false)
    private int rating; // Assuming values like 1-5

    @Column(columnDefinition = "TEXT", nullable = false)
    private String quote;

    private String avatar; // Recommended: store image URL
}

