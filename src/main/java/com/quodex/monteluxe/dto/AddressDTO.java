package com.quodex.monteluxe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private String id ;

    private String userId;

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
