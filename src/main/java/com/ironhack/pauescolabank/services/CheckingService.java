package com.ironhack.pauescolabank.services;

import com.ironhack.pauescolabank.repositories.CheckingRepository;
import org.springframework.stereotype.Service;

@Service
public class CheckingService {
    CheckingRepository checkingRepository;

    public CheckingService(CheckingRepository checkingRepository) {
        this.checkingRepository = checkingRepository;
    }
}
