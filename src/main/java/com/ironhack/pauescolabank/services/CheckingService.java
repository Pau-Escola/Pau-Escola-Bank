package com.ironhack.pauescolabank.services;

import com.ironhack.pauescolabank.DTO.CheckingDTO;
import com.ironhack.pauescolabank.model.Checking;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import com.ironhack.pauescolabank.repositories.CheckingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CheckingService {
    CheckingRepository checkingRepository;
    AccountHolderService accountHolderService;

    public CheckingService(CheckingRepository checkingRepository, AccountHolderService accountHolderService) {
        this.checkingRepository = checkingRepository;
        this.accountHolderService = accountHolderService;
    }

    public List<Checking> findAll() {
        return checkingRepository.findAll();
    }

    public Checking save(CheckingDTO checkingDTO, Long id) {
        Checking checking = new Checking();
        AccountHolder accountHolder = accountHolderService.getById(id);
        return checkingRepository.save(checking.fromDTO(checkingDTO, accountHolder));
    }

    public String delete(Long id) {
        checkingRepository.delete(
                checkingRepository.findById(id).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "There's no Checking Account with id: " + id)));
        return "Checking Account with id: " + id + " has been removed from the database";
    }

    public Checking getById(Long id) {
        return checkingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There's no Checking Account with id: " + id));
    }
}
