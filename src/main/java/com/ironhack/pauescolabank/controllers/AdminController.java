package com.ironhack.pauescolabank.controllers;

import com.ironhack.pauescolabank.DTO.AccountHolderDTO;
import com.ironhack.pauescolabank.DTO.AdminDTO;
import com.ironhack.pauescolabank.enums.AuthorisationLevel;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import com.ironhack.pauescolabank.model.Users.Admin;
import com.ironhack.pauescolabank.services.AccountHolderService;
import com.ironhack.pauescolabank.services.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users/admins")
public class AdminController {
    AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RolesAllowed({"admin", "moderator"})
    @GetMapping
    public List<Admin> getAll(){
        return adminService.findAll();
    }


    @RolesAllowed({"admin", "moderator"})
    @GetMapping("/{id}")
    public Admin getById(@PathVariable Long id){
        return adminService.findById(id);
    }


    /*@PutMapping("/edit_whole/{id}")
    public Admin updatePut(@PathVariable Long id, @RequestBody Admin admin){
        return adminService.updatePut(id, admin);
    }

    @PatchMapping("/update_authorisation_level/{id}")
    public Vegetable updateNamePatch(@PathVariable Long id,
                                     @RequestBody AuthorisationLevel authorisationLevel){
        return adminService.updateAuthorisationLevel(id, authorisationLevel);
    }*/
}
