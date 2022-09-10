package com.ironhack.pauescolabank.controllers;

import com.ironhack.pauescolabank.DTO.AccountHolderDTO;
import com.ironhack.pauescolabank.DTO.AdminDTO;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import com.ironhack.pauescolabank.model.Users.Admin;
import com.ironhack.pauescolabank.services.AccountHolderService;
import com.ironhack.pauescolabank.services.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/admins")
public class AdminController {
    AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public List<Admin> getAll(){
        return adminService.findAll();
    }

    @PostMapping
    public Admin create(@RequestBody Admin admin){
        return adminService.save(admin);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return adminService.delete(id);
    }
}
