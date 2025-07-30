package com.bixby.BixbyCommerce.service;

import com.bixby.BixbyCommerce.dto.AdminDTO;
import com.bixby.BixbyCommerce.dto.CreateAdminDTO;
import com.bixby.BixbyCommerce.dto.UpdateCustomerDTO;
import com.bixby.BixbyCommerce.model.Admins;
import com.bixby.BixbyCommerce.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    //mapping admin entity to dto
    private AdminDTO maptoDTO(Admins admins){
        AdminDTO dto = new AdminDTO();
        dto.setId(admins.getAdminID());
        dto.setName(admins.getName());
        dto.setEmail(admins.getEmail());
        dto.setPhoneNumber(admins.getPhoneNumber());
        return dto;
    }

    public AdminDTO createAdmin(CreateAdminDTO dto){
        Admins admins = new Admins();
        admins.setName(dto.getName());
        admins.setEmail(dto.getEmail());
        admins.setPhoneNumber(dto.getPhoneNumber());
        admins.setPassword(dto.getPassword());

        Admins saved = adminRepository.save(admins);
        return maptoDTO(saved);
    }

    public List<AdminDTO> getAllAdmins(){
        return adminRepository.findAll().stream().map(this::maptoDTO)
                .collect(Collectors.toList());
    }

    public AdminDTO getAdminById(Integer id){
        return adminRepository.findById(id)
                .map(this::maptoDTO)
                .orElse(null);
    }

    public AdminDTO updateAdmin(Integer id, UpdateCustomerDTO dto){
        return adminRepository.findById(id).map(admins -> {
            if (dto.getName()!=null){
                admins.setName(dto.getName());
            }
            if (dto.getEmail()!=null){
                admins.setEmail(dto.getEmail());
            }
            if (dto.getPassword()!=null){
                admins.setPassword(dto.getPassword());
            }

            Admins updated = adminRepository.save(admins);
            return maptoDTO(updated);
        }).orElse(null);
    }

    public boolean deleteAdmin(Integer id){
        if (adminRepository.existsById(id)){
            adminRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
