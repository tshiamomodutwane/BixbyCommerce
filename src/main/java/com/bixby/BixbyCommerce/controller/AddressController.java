package com.bixby.BixbyCommerce.controller;

import com.bixby.BixbyCommerce.dto.AddressDTO;
import com.bixby.BixbyCommerce.dto.UpdateAddressDTO;
import com.bixby.BixbyCommerce.model.Addresses;
import com.bixby.BixbyCommerce.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<Addresses> createAddress(@RequestBody AddressDTO dto){
        Addresses created = addressService.addAddress(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public List<Addresses> getAllAddresses(){
        return addressService.getAllAddresses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) {
        Optional<Addresses> addressOpt = addressService.getAddressById(id);

        if (addressOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        AddressDTO dto = addressService.mapToDTO(addressOpt.get());
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @RequestBody AddressDTO dto) {
        Optional<Addresses> updated = addressService.updateAddress(id, dto);
        if (updated.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            AddressDTO dto1 = addressService.mapToDTO(updated.get());
            return ResponseEntity.ok(dto1);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id){
        if (addressService.deleteById(id)){
            return ResponseEntity.noContent().build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }


}
