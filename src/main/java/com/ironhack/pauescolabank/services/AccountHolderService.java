package com.ironhack.pauescolabank.services;

import com.ironhack.pauescolabank.DTO.AccountHolderDTO;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import com.ironhack.pauescolabank.repositories.AccountHolderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AccountHolderService {
    AccountHolderRepository accountHolderRepository;

    public AccountHolderService(AccountHolderRepository accountHolderRepository) {
        this.accountHolderRepository = accountHolderRepository;
    }

    //todo shauri de fer que retornes dtos i no entities
    public List<AccountHolder> findAll() {
        return accountHolderRepository.findAll();
    }

    public AccountHolder save(AccountHolderDTO accountHolderDTO) {
        AccountHolder accountHolder = new AccountHolder();
        return accountHolderRepository.save(accountHolder.fromDTO(accountHolderDTO));
    }

    public String delete(Long id) {
        accountHolderRepository.delete(
                accountHolderRepository.findById(id).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "There's no Account Holder with id: " + id)));
        return "Account Holder with id: " + id + " has been removed from the database";
    }

    public AccountHolder getById(Long id) {
        return accountHolderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There's no Account Holder with id: " + id));
    }
}
