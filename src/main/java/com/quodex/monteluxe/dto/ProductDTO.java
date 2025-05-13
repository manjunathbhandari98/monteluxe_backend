package com.quodex.monteluxe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String id;
    private String name;
    private String slug;
    private String gender;
    private double price;
    private String currency;
    private String image;
    private List<String> images;
    private String description;
    private String caseSize;
    private String caseMaterial;
    private String crystalType;
    private String waterResistance;
    private String movement;
    private String strapMaterial;
    private List<String> features;
    private String categoryId;
}
