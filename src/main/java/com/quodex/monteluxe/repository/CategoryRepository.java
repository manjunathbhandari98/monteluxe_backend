package com.quodex.monteluxe.repository;

import com.quodex.monteluxe.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Category findByName(String name);
}
