package com.quodex.monteluxe.service.impl;

import com.quodex.monteluxe.dto.CategoryDTO;
import com.quodex.monteluxe.mapper.CategoryMapper;
import com.quodex.monteluxe.model.Category;
import com.quodex.monteluxe.repository.CategoryRepository;
import com.quodex.monteluxe.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO request) {
        Category existingCategory = categoryRepository.findByName(request.getName());

        if (existingCategory != null){
            throw new RuntimeException("Category Already Exist");
        }

        Category category = categoryMapper.toEntity(request);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDTO(savedCategory);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO updateCategory(String id, CategoryDTO request) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category Not Found"));
        if (category != null ){
            category.setName(request.getName());
            category.setDescription(request.getDescription());
            category.setImage(request.getImage());
            category.setLink(request.getLink());
            category.setFeatures(request.getFeatures());
            category.setStartingPrice(request.getStartingPrice());

            Category updatedCategory = categoryRepository.save(category);
            return categoryMapper.toDTO(updatedCategory);
        }
        return null;
    }

    @Override
    public void deleteCategory(String id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category Not Found"));
        if (category != null){
            categoryRepository.deleteById(id);
        }
    }


}
