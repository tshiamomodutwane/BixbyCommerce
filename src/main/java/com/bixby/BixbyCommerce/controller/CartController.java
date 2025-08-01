package com.bixby.BixbyCommerce.controller;

import com.bixby.BixbyCommerce.dto.CartDTO;
import com.bixby.BixbyCommerce.dto.CreateCartDTO;
import com.bixby.BixbyCommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/customer/{customerId}")
    public ResponseEntity<CartDTO> createCart(@RequestBody CreateCartDTO dto, @PathVariable Long id){
        Optional<CartDTO> createCart = cartService.createCart(dto, id);
        return createCart.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable Long id){
        Optional<CartDTO> cart = cartService.getCartById(id);
        return cart.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id){
        Optional<CartDTO> cart = cartService.getCartById(id);

        if (cart.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }


}
