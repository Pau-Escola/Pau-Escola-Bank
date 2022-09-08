package com.ironhack.pauescolabank.services;

import com.ironhack.pauescolabank.repositories.SavingRepository;
import org.springframework.stereotype.Service;

@Service
public class SavingService {
    SavingRepository savingRepository;

    public SavingService(SavingRepository savingRepository) {
        this.savingRepository = savingRepository;
    }
}
