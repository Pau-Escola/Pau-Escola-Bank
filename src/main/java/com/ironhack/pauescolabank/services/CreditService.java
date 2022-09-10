package com.ironhack.pauescolabank.services;

import com.ironhack.pauescolabank.DTO.CreditDTO;
import com.ironhack.pauescolabank.model.Credit;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import com.ironhack.pauescolabank.repositories.CreditRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CreditService {
    CreditRepository creditRepository;
    AccountHolderService accountHolderService;

    public CreditService(CreditRepository creditRepository, AccountHolderService accountHolderService) {
        this.creditRepository = creditRepository;
        this.accountHolderService = accountHolderService;
    }

    public List<Credit> findAll() {
        return creditRepository.findAll();
    }

    public Credit save(CreditDTO creditDTO, Long id) {
        Credit credit = new Credit();
        AccountHolder accountHolder = accountHolderService.getById(id);
        return creditRepository.save(credit.fromDTO(creditDTO, accountHolder));
    }

    public String delete(Long id) {
        creditRepository.delete(
                creditRepository.findById(id).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "There's no Credit Account with id: " + id)));
        return "Credit Account with id: " + id + " has been removed from the database";
    }

    public Credit getById(Long id) {
        return creditRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There's no Credit Account with id: " + id));
    }
}
