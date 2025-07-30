package com.bixby.BixbyCommerce.service;

import com.bixby.BixbyCommerce.dto.CategoryDTO;
import com.bixby.BixbyCommerce.dto.CreateCategoryDTO;
import com.bixby.BixbyCommerce.dto.ProductDTO;
import com.bixby.BixbyCommerce.dto.UpdateCategoryDTO;
import com.bixby.BixbyCommerce.model.Category;
import com.bixby.BixbyCommerce.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    private CategoryDTO mapToDTO(Category category){
        CategoryDTO dto = new CategoryDTO();
        dto.setCategoryID(category.getCategoryID());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setCreatedAt(category.getCreatedAt());

        List<ProductDTO> productDTOS = category.getProducts()
                .stream().map(products -> {
                    ProductDTO p = new ProductDTO();
                    p.setProductID(products.getProductID());
                    p.setName(products.getName());
                    p.setDescription(products.getDescription());
                    p.setImageURL(products.getImageURL());
                    return p;
                }).collect(Collectors.toList());
        dto.setProducts(productDTOS);
        return dto;
    }

    public CategoryDTO createCategory(CreateCategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setCreatedAt(java.time.LocalDateTime.now());

        Category saved = categoryRepository.save(category);
        return mapToDTO(saved);
    }

    public List<CategoryDTO> getAllCategories(){
        return categoryRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Category not found"));
        return mapToDTO(category);
    }

    public CategoryDTO updateCategory(Long id, UpdateCategoryDTO dto){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Category not found"));

        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        Category updated = categoryRepository.save(category);
        return mapToDTO(updated);
    }

    public void deleteCategory(Long id){
        if (!categoryRepository.existsById(id)){
            throw new RuntimeException("Category not found");
        }
        categoryRepository.deleteById(id);
    }
}
