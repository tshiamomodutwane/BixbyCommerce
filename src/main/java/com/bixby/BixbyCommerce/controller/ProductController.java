package com.bixby.BixbyCommerce.controller;

import com.bixby.BixbyCommerce.dto.CreateProductDTO;
import com.bixby.BixbyCommerce.dto.ProductDTO;
import com.bixby.BixbyCommerce.dto.UpdateProductDTO;
import com.bixby.BixbyCommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody CreateProductDTO dto){
        ProductDTO created = productService.createProduct(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id){
        ProductDTO productDTO = productService.getProductById(id);
        if (productDTO==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody UpdateProductDTO dto){
        ProductDTO updated = productService.updateProduct(id,dto);
        if (updated==null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updated);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        if (productService.deleteProductById(id)){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
