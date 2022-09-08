package com.ironhack.pauescolabank.services;

import com.ironhack.pauescolabank.repositories.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
}
