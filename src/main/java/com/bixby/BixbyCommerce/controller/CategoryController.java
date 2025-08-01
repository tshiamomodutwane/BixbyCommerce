package com.bixby.BixbyCommerce.controller;

import com.bixby.BixbyCommerce.dto.CategoryDTO;
import com.bixby.BixbyCommerce.dto.CreateCategoryDTO;
import com.bixby.BixbyCommerce.dto.UpdateCategoryDTO;
import com.bixby.BixbyCommerce.model.Category;
import com.bixby.BixbyCommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController{
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(CreateCategoryDTO dto){
        CategoryDTO created = categoryService.createCategory(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id){
        CategoryDTO categoryDTO = categoryService.getCategoryById(id);
        if (categoryDTO==null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(categoryDTO);
        }
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(Long id, UpdateCategoryDTO dto){
        CategoryDTO updated = categoryService.updateCategory(id,dto);
        if(updated == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updated);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(Long id){
        if (categoryService.deleteCategory(id)){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
