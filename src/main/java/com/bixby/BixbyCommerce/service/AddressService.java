package com.bixby.BixbyCommerce.service;

import com.bixby.BixbyCommerce.dto.AddressDTO;
import com.bixby.BixbyCommerce.model.Addresses;
import com.bixby.BixbyCommerce.model.Customers;
import com.bixby.BixbyCommerce.repositories.AddressRepository;
import com.bixby.BixbyCommerce.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;


    public Addresses addAddress(AddressDTO dto) {
        Optional<Customers> customerOpt = customerRepository.findById(dto.getCustomerId());
        if (customerOpt.isEmpty()) {
            throw new IllegalArgumentException("Customer not found with ID: " + dto.getCustomerId());
        }

        Addresses address = new Addresses();
        address.setCity(dto.getCity());
        address.setStreet(dto.getStreet());
        address.setZipCode(dto.getZipCode());
        address.setType(dto.getType());
        address.setCustomers(customerOpt.get());

        return addressRepository.save(address);
    }

    public List<Addresses> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Optional<Addresses> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    public boolean deleteById(Long id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


    public Optional<Addresses> updateAddress(Long id, AddressDTO dto) {
        return addressRepository.findById(id).map(existingAddress -> {
            if (dto.getCity() != null) {
                existingAddress.setCity(dto.getCity());
            }
            if (dto.getStreet() != null) {
                existingAddress.setStreet(dto.getStreet());
            }
            if (dto.getZipCode() != null) {
                existingAddress.setZipCode(dto.getZipCode());
            }
            if (dto.getType() != null) {
                existingAddress.setType(dto.getType());
            }
            if (dto.getCustomerId() != null) {
                Optional<Customers> customerOpt = customerRepository.findById(dto.getCustomerId());
                customerOpt.ifPresent(existingAddress::setCustomers);
            }
            return addressRepository.save(existingAddress);
        });
    }
}
