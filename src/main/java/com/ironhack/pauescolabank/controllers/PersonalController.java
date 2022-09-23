package com.ironhack.pauescolabank.controllers;

import com.ironhack.pauescolabank.DTO.AccountHolderDTO;
import com.ironhack.pauescolabank.embedded.Address;
import com.ironhack.pauescolabank.model.Account;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import com.ironhack.pauescolabank.services.AccountHolderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/personal-space")
public class PersonalController {

    private final AccountHolderService accountHolderService;

    public PersonalController(AccountHolderService accountHolderService) {
        this.accountHolderService = accountHolderService;
    }

    @GetMapping("/get-accounts")
    public List<Account> getAllAccounts(Principal principal){
        return accountHolderService.getAccounts(principal.getName());
    }

    @PatchMapping("/update_address")
    public AccountHolder updateAddress(Principal principal, @RequestBody Address address){
        return accountHolderService.updateAddress(principal.getName(), address);
    }
}
