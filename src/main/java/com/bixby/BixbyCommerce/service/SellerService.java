package com.bixby.BixbyCommerce.service;

import com.bixby.BixbyCommerce.dto.CreateSellerDTO;
import com.bixby.BixbyCommerce.dto.SellerDTO;
import com.bixby.BixbyCommerce.dto.SellerLoginDTO;
import com.bixby.BixbyCommerce.dto.UpdateSellerDTO;
import com.bixby.BixbyCommerce.model.Sellers;
import com.bixby.BixbyCommerce.repositories.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    private SellerDTO mapToDTO(Sellers sellers){
        SellerDTO dto = new SellerDTO();
        dto.setSellerID(sellers.getSellerID());
        dto.setFullName(sellers.getFullName());
        dto.setEmail(sellers.getEmail());
        dto.setAddress(sellers.getAddress());
        dto.setBusinessName(sellers.getBusinessName());
        dto.setBankingInfo(sellers.getBankingInfo());
        dto.setBusinessType(sellers.getBusinessType());
        dto.setPhoneNumber(sellers.getPhoneNumber());
        dto.setCompanyRegistration(sellers.getCompanyRegistration());
        dto.setVatNumber(sellers.getVatNumber());
        dto.setProfileImage(sellers.getProfileImage());
        return dto;
    }

    public SellerDTO createSeller(CreateSellerDTO dto){
        Sellers sellers = new Sellers();
        sellers.setFullName(dto.getFullName());
        sellers.setEmail(dto.getEmail());
        sellers.setPassword(dto.getPassword());
        sellers.setAddress(dto.getAddress());
        sellers.setBusinessName(dto.getBusinessName());
        sellers.setBankingInfo(dto.getBankingInfo());
        sellers.setBusinessType(dto.getBusinessType());
        sellers.setCompanyRegistration(dto.getCompanyRegistration());
        sellers.setPhoneNumber(dto.getPhoneNumber());
        sellers.setVatNumber(dto.getVatNumber());
        sellers.setProductCategories(dto.getProductCategories());

        Sellers saved = sellerRepository.save(sellers);
        return mapToDTO(saved);
    }

    public List<SellerDTO> getAllSellers(){
        return sellerRepository.findAll().stream().map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public SellerDTO getSellerById(Long id){
        return sellerRepository.findById(id).map(this::mapToDTO)
                .orElse(null);
    }

    public SellerDTO updateSeller(Long id, UpdateSellerDTO dto){
        return sellerRepository.findById(id).map(sellers -> {
            if (dto.getAddress()!=null){
                sellers.setAddress(dto.getAddress());
            }
            if (dto.getEmail()!=null){
                sellers.setEmail(dto.getEmail());
            }
            if (dto.getPassword()!=null){
                sellers.setPassword(dto.getPassword());
            }
            if (dto.getBusinessType()!=null){
                       sellers.setBusinessName(dto.getBusinessName());
            }
            if (dto.getBusinessName()!=null){
                sellers.setBusinessName(dto.getBusinessName());
            }
            if (dto.getBankingInfo()!=null){
                sellers.setBankingInfo(dto.getBankingInfo());
            }
            if (dto.getPhoneNumber()!=null){
                sellers.setPhoneNumber(dto.getPhoneNumber());
            }
            if (dto.getCompanyRegistration()!=null){
                sellers.setCompanyRegistration(dto.getCompanyRegistration());
            }
            if (dto.getVatNumber()!=null){
                sellers.setVatNumber(dto.getVatNumber());
            }
            if (dto.getProfileImage()!=null){
                sellers.setProfileImage(dto.getProfileImage());
            }
            if (dto.getBusinessType()!=null){
                sellers.setBusinessType(dto.getBusinessType());
            }
            Sellers updated = sellerRepository.save(sellers);
            return mapToDTO(updated);
        }).orElse(null);
    }

    public boolean deleteSellerById(Long id){
        if (sellerRepository.existsById(id)){
            sellerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public String login(SellerLoginDTO dto) {
        Sellers sellers = sellerRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        if (!sellers.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return "Login successful for " + sellers.getFullName();
    }


}
