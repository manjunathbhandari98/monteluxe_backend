package com.quodex.monteluxe.service.impl;

import com.quodex.monteluxe.dto.ProductDTO;
import com.quodex.monteluxe.mapper.ProductMapper;
import com.quodex.monteluxe.model.Category;
import com.quodex.monteluxe.model.Product;
import com.quodex.monteluxe.repository.CategoryRepository;
import com.quodex.monteluxe.repository.ProductRepository;
import com.quodex.monteluxe.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static com.quodex.monteluxe.util.SlugUtil.generateSlug;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper,
                              CategoryRepository categoryRepository
    ){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductsByCategory(String categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category Not Found"));

        List<Product> products = productRepository.findByCategory(category);
        return products.stream().map(productMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public ProductDTO getProductById(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found"));
        if (product != null){
            return productMapper.toDTO(product);
        }
        return null;
    }

    @Override
    public ProductDTO createNewProduct(ProductDTO productDTO) {
        Product existingProduct = productRepository.findByName(productDTO.getName());

        if (existingProduct != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product already exists with name: " + productDTO.getName());
        }

        Product product = productMapper.toEntity(productDTO);

        // Generate slug from name if not provided
        if (product.getSlug() == null || product.getSlug().isEmpty()) {
            product.setSlug(generateSlug(product.getName()));
        }

        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    @Override
    public void deleteProduct(String id){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found"));

        if (product != null ) {
            productRepository.deleteById(id);
        }
    }

    @Override
    public ProductDTO updateProduct(String id, ProductDTO request) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found"));

        if (product != null ) {
           product.setName(request.getName());
           product.setDescription(request.getDescription());
            product.setSlug(generateSlug(product.getName()));
           product.setCaseMaterial(request.getCaseMaterial());
           product.setCaseSize(request.getCaseSize());
           product.setCurrency(request.getCurrency());
           product.setCrystalType(request.getCrystalType());
           product.setGender(request.getGender());
           product.setFeatures(request.getFeatures());
           product.setImage(request.getImage());
           product.setImages(request.getImages());
           product.setMovement(request.getMovement());
           product.setPrice(request.getPrice());
           product.setStrapMaterial(request.getStrapMaterial());
           product.setWaterResistance(request.getWaterResistance());

           Product updatedProduct = productRepository.save(product);
           return productMapper.toDTO(updatedProduct);
        }
        return null;
    }


}
