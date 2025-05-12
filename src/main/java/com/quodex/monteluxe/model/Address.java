package com.quodex.monteluxe.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

import static com.quodex.monteluxe.util.IdGenerator.generateAddressId;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @Column(name = "address_id", nullable = false, unique = true)
    private String id = generateAddressId();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String fullName;

    private String phone;

    private String email;

    private String street;

    private String city;

    private String state;

    private String country;

    private String postalCode;

    private boolean isDefault;

}
