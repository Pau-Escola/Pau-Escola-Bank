package com.ironhack.pauescolabank.services;

import com.ironhack.pauescolabank.DTO.CheckingDTO;
import com.ironhack.pauescolabank.embedded.Money;
import com.ironhack.pauescolabank.enums.AccountStatus;
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

    public CheckingDTO updateStatus(Long id, AccountStatus status) {
        Checking checking = checkingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "There's no Checking Account with id: " + id));
        checking.setAccountStatus(status);
        CheckingDTO checkingDTO = new CheckingDTO();
        return checkingDTO.fromEntity(checkingRepository.save(checking));
    }

    public CheckingDTO updateBalance(Long id, Money balance) {
        Checking checking = checkingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There's no Checking Account with id: " + id));
        checking.setBalance(balance);
        CheckingDTO checkingDTO = new CheckingDTO();
        return checkingDTO.fromEntity(checkingRepository.save(checking));
    }

    public Checking updateAll(Long id, Checking checking) {
            var checkingToUpdate = checkingRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "There's no Checking Account with id " + id ));
            if(checking.getOwner() != null)
                checkingToUpdate.setOwner(checking.getOwner());
            if(checking.getAccountStatus() != null)
                checkingToUpdate.setAccountStatus(checking.getAccountStatus());
            if(checking.getBalance() != null)
                checkingToUpdate.setBalance(checking.getBalance());
            if(checking.getPenaltyFee() != null)
                checkingToUpdate.setPenaltyFee(checking.getPenaltyFee());
            if(checking.getMonthlyManteinanceFee() != null)
                checkingToUpdate.setMonthlyManteinanceFee(checking.getMonthlyManteinanceFee());
            if(checking.getMinimumBalance() != null)
                checkingToUpdate.setMinimumBalance(checking.getMinimumBalance());

            return checkingRepository.save(checkingToUpdate);

    }
}
