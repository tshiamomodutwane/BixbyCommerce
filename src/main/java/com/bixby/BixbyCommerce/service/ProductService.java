package com.bixby.BixbyCommerce.service;

import com.bixby.BixbyCommerce.dto.CreateProductDTO;
import com.bixby.BixbyCommerce.dto.ProductDTO;
import com.bixby.BixbyCommerce.dto.UpdateProductDTO;
import com.bixby.BixbyCommerce.model.Category;
import com.bixby.BixbyCommerce.model.Products;
import com.bixby.BixbyCommerce.repositories.CategoryRepository;
import com.bixby.BixbyCommerce.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    private ProductDTO mapToDTO(Products products){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductID(products.getProductID());
        productDTO.setName(products.getName());
        productDTO.setDescription(products.getDescription());
        productDTO.setPrice(products.getPrice());
        return productDTO;
    }

    public ProductDTO createProduct(CreateProductDTO dto){
        Products products = new Products();
        products.setName(dto.getName());
        products.setDescription(dto.getDescription());
        products.setPrice(dto.getPrice());

        Category category = categoryRepository.findByName(dto.getCategoryName())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        products.setCategory(category);

        products.setImageURL(dto.getImageURL());

        Products saved = productRepository.save(products);
        return mapToDTO(saved);
    }

    public List<ProductDTO> getAllProducts(){
        return productRepository.findAll().stream().map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id){
        return productRepository.findById(id).map(this::mapToDTO)
                .orElse(null);
    }

    public ProductDTO updateProduct(Long id, UpdateProductDTO dto){
        return productRepository.findById(id).map(products -> {
            if (dto.getName()!=null){
                products.setName(dto.getName());
            }
            if (dto.getDescription()!=null){
                products.setDescription(dto.getDescription());
            }
            if (dto.getPrice()!=null){
                products.setPrice(dto.getPrice());
            }
            if (dto.getQuantity()!=null){
                products.setQuantity(dto.getQuantity());
            }
            Products updated = productRepository.save(products);
            return mapToDTO(updated);
        }).orElse(null);
    }

    public boolean deleteProductById(Long id){
        if (productRepository.existsById(id)){
            productRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
