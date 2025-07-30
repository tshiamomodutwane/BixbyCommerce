package com.bixby.BixbyCommerce.controller;

import com.bixby.BixbyCommerce.dto.CreateCustomerDTO;
import com.bixby.BixbyCommerce.dto.CustomerDTO;
import com.bixby.BixbyCommerce.dto.CustomerLoginDTO;
import com.bixby.BixbyCommerce.dto.UpdateCustomerDTO;
import com.bixby.BixbyCommerce.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CreateCustomerDTO dto){
        CustomerDTO created = customerService.createCustomer(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id){
        CustomerDTO customer = customerService.getCustomerById(id);
        if (customer == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @Valid @RequestBody UpdateCustomerDTO dto){
     CustomerDTO updated = customerService.updateCustomer(id, dto);
     if (updated==null){
         return ResponseEntity.notFound().build();
     }
     return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        if (customerService.deleteCustomer(id)){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/login")
    public String login(@RequestBody CustomerLoginDTO loginDTO){
        return customerService.login(loginDTO);
    }

}
