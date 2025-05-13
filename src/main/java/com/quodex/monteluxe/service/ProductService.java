package com.quodex.monteluxe.service;

import com.quodex.monteluxe.dto.ProductDTO;

import java.util.List;

public interface ProductService {

        List<ProductDTO> getAllProducts();

        List<ProductDTO> getProductsByCategory(String categoryId);

        ProductDTO getProductById(String id);

        ProductDTO createNewProduct(ProductDTO productDTO);

        void deleteProduct(String id);

        ProductDTO updateProduct(String id, ProductDTO request);
}
