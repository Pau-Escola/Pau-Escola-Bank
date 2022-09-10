package com.ironhack.pauescolabank.services;

import com.ironhack.pauescolabank.DTO.AdminDTO;
import com.ironhack.pauescolabank.model.Users.Admin;
import com.ironhack.pauescolabank.repositories.AdminRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AdminService {
    AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    //todo aquest métode només hauria de ser accessible pel superadmin
    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    //todo aquest métode només hauria de ser accessible pel superadmin
    public String delete(Long id) {
        adminRepository.delete(
                adminRepository.findById(id).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "There's no Admin with id: " + id)));
        return "Admin with id: " + id + " has been removed from the database";
    }
}
