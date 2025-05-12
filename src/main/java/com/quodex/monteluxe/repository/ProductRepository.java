package com.quodex.monteluxe.repository;

import com.quodex.monteluxe.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
