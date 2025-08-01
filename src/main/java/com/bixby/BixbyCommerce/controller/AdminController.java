package com.bixby.BixbyCommerce.controller;

import com.bixby.BixbyCommerce.dto.AdminDTO;
import com.bixby.BixbyCommerce.dto.AdminLoginDTO;
import com.bixby.BixbyCommerce.dto.CreateAdminDTO;
import com.bixby.BixbyCommerce.dto.UpdateAdminDTO;
import com.bixby.BixbyCommerce.model.Admins;
import com.bixby.BixbyCommerce.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<AdminDTO> createAdmin(@Valid @RequestBody CreateAdminDTO dto){
        AdminDTO created = adminService.createAdmin(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public List<AdminDTO> getAllAdmins(){
        return adminService.getAllAdmins();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Integer id){
        AdminDTO adminDTO = adminService.getAdminById(id);
        if (adminDTO == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(adminDTO);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable Integer id, @Valid @RequestBody UpdateAdminDTO dto){
        AdminDTO adminDTO = adminService.updateAdmin(id,dto);

        if (adminDTO == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(adminDTO);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Integer id){
        if (adminService.deleteAdmin(id)){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/login")
    public String login(@RequestBody AdminLoginDTO dto){
        return adminService.login(dto);
    }
}
