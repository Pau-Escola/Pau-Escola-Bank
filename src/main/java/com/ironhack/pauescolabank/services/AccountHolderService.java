package com.ironhack.pauescolabank.services;

import com.ironhack.pauescolabank.DTO.AccountHolderDTO;
import com.ironhack.pauescolabank.embedded.Address;
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


    public AccountHolderDTO updateAddress(Long id, Address address) {
        var accountHolder = accountHolderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There's no Account Holder with id: " + id));
        accountHolder.setAddress(address);
        var accountHolderDTO = new AccountHolderDTO();
        return accountHolderDTO.fromEntity(accountHolderRepository.save(accountHolder));
    }

    /*public AccountHolderDTO addAccount(Long user_id, Long account_id) {
        var accountHolder = accountHolderRepository.findById(user_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There's no Account Holder with id: " + user_id));
        var account = accountRepository.findById(user_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There's no Account Holder with id: " + user_id));
        accountHolder.setBalance(balance);
        CheckingDTO checkingDTO = new CheckingDTO();
        return checkingDTO.fromEntity(checking);
    }*/

    /*public AccountHolder updateAll(Long id, AccountHolder accountHolder) {
        var accountHolderToUpdate = accountHolderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There's no Account Holder with id " + id));
        if (accountHolder.getOwner() != null)
            accountHolderToUpdate.setOwner(accountHolder.getOwner());
        if (accountHolder.getAccountStatus() != null)
            accountHolderToUpdate.setAccountStatus(accountHolder.getAccountStatus());
        if (accountHolder.getBalance() != null)
            accountHolderToUpdate.setBalance(accountHolder.getBalance());
        if (accountHolder.getPenaltyFee() != null)
            accountHolderToUpdate.setPenaltyFee(accountHolder.getPenaltyFee());
        if (accountHolder.getInterestRate() != null)
            accountHolderToUpdate.setInterestRate(accountHolder.getInterestRate());
        if (accountHolder.getMoneyOwed() != null)
            accountHolderToUpdate.setMoneyOwed(accountHolder.getMoneyOwed());
        if (accountHolder.getCreditLimit() != null)
            accountHolderToUpdate.setCreditLimit(accountHolder.getCreditLimit()));

        return accountHolderRepository.save(accountHolderToUpdate);

    }*/
}
