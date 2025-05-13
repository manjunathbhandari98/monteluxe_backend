package com.quodex.monteluxe.repository;

import com.quodex.monteluxe.model.Category;
import com.quodex.monteluxe.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByCategory(Category category);


    Product findByName(String name);
}
