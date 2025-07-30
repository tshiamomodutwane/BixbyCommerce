package com.bixby.BixbyCommerce.service;

import com.bixby.BixbyCommerce.dto.CreateCustomerDTO;
import com.bixby.BixbyCommerce.dto.CustomerDTO;
import com.bixby.BixbyCommerce.dto.UpdateCustomerDTO;
import com.bixby.BixbyCommerce.model.Customers;
import com.bixby.BixbyCommerce.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    //mapping customers entity to dto
    private CustomerDTO maptoDTO(Customers customers){
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customers.getCustomerID());
        dto.setName(customers.getName());
        dto.setEmail(customers.getEmail());
        return dto;
    }

    public CustomerDTO createCustomer(CreateCustomerDTO dto){
        Customers customers = new Customers();
        customers.setName(dto.getName());
        customers.setEmail(dto.getEmail());
        customers.setPassword(dto.getPassword());

        Customers saved = customerRepository.save(customers);
        return maptoDTO(saved);
    }

    public List<CustomerDTO> getAllCustomers(){
        return customerRepository.findAll()
                .stream().map(this::maptoDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(Long id){
        return customerRepository.findById(id)
                .map(this::maptoDTO)
                .orElse(null);
    }

    public CustomerDTO updateCustomer(Long id, UpdateCustomerDTO dto){
        return customerRepository.findById(id).map(customers -> {
            if (dto.getName()!=null){
                customers.setName(dto.getName());
            }
            if (dto.getEmail()!=null){
                customers.setEmail(dto.getEmail());
            }
            if (dto.getPassword()!=null){
                customers.setPassword(dto.getPassword());
            }
            Customers updated = customerRepository.save(customers);
            return maptoDTO(updated);
        }).orElse(null);
    }

    public boolean deleteCustomer(Long id){
        if (customerRepository.existsById(id)){
            customerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }



}
