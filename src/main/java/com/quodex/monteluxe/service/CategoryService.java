package com.quodex.monteluxe.service;

import com.quodex.monteluxe.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    public CategoryDTO createCategory(CategoryDTO request);


    List<CategoryDTO> getAllCategories();

    CategoryDTO updateCategory(String id, CategoryDTO request);

    void deleteCategory(String id);
}
