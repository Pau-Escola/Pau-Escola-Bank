package com.ironhack.pauescolabank.services;

import com.ironhack.pauescolabank.DTO.CreditDTO;
import com.ironhack.pauescolabank.DTO.SavingDTO;
import com.ironhack.pauescolabank.embedded.Money;
import com.ironhack.pauescolabank.enums.AccountStatus;
import com.ironhack.pauescolabank.model.Credit;
import com.ironhack.pauescolabank.model.Saving;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import com.ironhack.pauescolabank.repositories.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CreditService {
    CreditRepository creditRepository;
    AccountHolderService accountHolderService;

    @Autowired
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


    public CreditDTO updateStatus(Long id, AccountStatus status) {
        Credit credit = creditRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There's no Credit Account with id: " + id));
        credit.setAccountStatus(status);
        var creditDTO = new CreditDTO();
        return creditDTO.fromEntity(creditRepository.save(credit));
    }

    public CreditDTO updateBalance(Long id, BigDecimal balance) {
        Credit credit = creditRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There's no Credit Account with id: " + id));
        credit.setBalance(balance);
        var creditDTO = new CreditDTO();
        return creditDTO.fromEntity(creditRepository.save(credit));
    }

    public Credit updateAll(Long id, Credit credit) {
        var creditToUpdate = creditRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There's no Credit Account with id " + id));
        if (credit.getOwner() != null)
            creditToUpdate.setOwner(credit.getOwner());

        if (credit.getInterestRate() != null)
            creditToUpdate.setInterestRate(credit.getInterestRate());
        if (credit.getMoneyOwed() != null)
            creditToUpdate.setMoneyOwed(credit.getMoneyOwed());
        if (credit.getCreditLimit() != null)
            creditToUpdate.setCreditLimit(credit.getCreditLimit());

        return creditRepository.save(creditToUpdate);
    }
}
