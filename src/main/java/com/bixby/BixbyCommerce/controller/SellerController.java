package com.bixby.BixbyCommerce.controller;

import com.bixby.BixbyCommerce.dto.*;
import com.bixby.BixbyCommerce.service.SellerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/sellers")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @PostMapping
    public ResponseEntity<SellerDTO> createSeller(@Valid @RequestBody CreateSellerDTO dto){
        SellerDTO created = sellerService.createSeller(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public List<SellerDTO> getAllSellers(){
        return sellerService.getAllSellers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerDTO> getSellerById(@PathVariable Long id){
        SellerDTO seller = sellerService.getSellerById(id);
        if (seller == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(seller);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SellerDTO> updateSeller(@PathVariable Long id, @Valid @RequestBody UpdateSellerDTO dto){
        SellerDTO updated = sellerService.updateSeller(id,dto);
        if (updated==null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updated);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id){
        if (sellerService.deleteSellerById(id)){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody SellerLoginDTO loginDTO){
        return sellerService.login(loginDTO);
    }

}
