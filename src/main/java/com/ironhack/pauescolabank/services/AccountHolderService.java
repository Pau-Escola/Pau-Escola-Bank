package com.ironhack.pauescolabank.services;

import com.ironhack.pauescolabank.repositories.AccountHolderRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountHolderService {
    AccountHolderRepository accountHolderRepository;

    public AccountHolderService(AccountHolderRepository accountHolderRepository) {
        this.accountHolderRepository = accountHolderRepository;
    }
}
