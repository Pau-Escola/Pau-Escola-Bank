package com.ironhack.pauescolabank.services;

import com.ironhack.pauescolabank.repositories.CreditRepository;
import org.springframework.stereotype.Service;

@Service
public class CreditService {
    CreditRepository creditRepository;

    public CreditService(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }
}
