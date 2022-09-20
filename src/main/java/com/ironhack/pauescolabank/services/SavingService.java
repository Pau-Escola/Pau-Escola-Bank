package com.ironhack.pauescolabank.services;

import com.ironhack.pauescolabank.DTO.CheckingDTO;
import com.ironhack.pauescolabank.DTO.SavingDTO;
import com.ironhack.pauescolabank.embedded.Money;
import com.ironhack.pauescolabank.enums.AccountStatus;
import com.ironhack.pauescolabank.model.Checking;
import com.ironhack.pauescolabank.model.Saving;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import com.ironhack.pauescolabank.repositories.AccountHolderRepository;
import com.ironhack.pauescolabank.repositories.SavingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SavingService {
    SavingRepository savingRepository;
    AccountHolderService accountHolderService;

    public SavingService(
            SavingRepository savingRepository,
            AccountHolderService accountHolderService) {
        this.savingRepository = savingRepository;
        this.accountHolderService = accountHolderService;

    }

    public List<Saving> findAll() {
        return savingRepository.findAll();
    }

    public Saving save(SavingDTO savingDTO, Long id) {
        Saving saving = new Saving();
        AccountHolder accountHolder = accountHolderService.getById(id);
        return savingRepository.save(saving.fromDTO(savingDTO, accountHolder));
    }

    public String delete(Long id) {
        savingRepository.delete(
                savingRepository.findById(id).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "There's no Saving Account with id: " + id)));
        return "Saving Account with id: " + id + " has been removed from the database";
    }

    public Saving getById(Long id) {
        return savingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There's no Saving Account with id: " + id));
    }

    public SavingDTO updateStatus(Long id, AccountStatus status) {
        Saving saving = savingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There's no Saving Account with id: " + id));
        saving.setAccountStatus(status);
        var savingDTO = new SavingDTO();
        return savingDTO.fromEntity(savingRepository.save(saving));
    }

    public SavingDTO updateBalance(Long id, BigDecimal balance) {
        Saving saving = savingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There's no Saving Account with id: " + id));
        saving.setBalance(balance);
        var savingDTO = new SavingDTO();
        return savingDTO.fromEntity(savingRepository.save(saving));
    }

    public Saving updateAll(Long id, Saving saving) {
        var savingToUpdate = savingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There's no Saving Account with id " + id ));
        if(saving.getOwner() != null)
            savingToUpdate.setOwner(saving.getOwner());
        if(saving.getInterestRate() != null)
            savingToUpdate.setInterestRate(saving.getInterestRate());
        if(saving.getMinimumBalance() != null)
            savingToUpdate.setMinimumBalance(saving.getMinimumBalance());

        return savingRepository.save(savingToUpdate);

    }
}
