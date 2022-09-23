package com.ironhack.pauescolabank.controllers;

import com.ironhack.pauescolabank.DTO.AccountHolderDTO;
import com.ironhack.pauescolabank.embedded.Address;
import com.ironhack.pauescolabank.model.Account;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import com.ironhack.pauescolabank.services.AccountHolderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users/accountholders")
public class AccountHolderController {
    AccountHolderService accountHolderService;

    public AccountHolderController(AccountHolderService accountHolderService) {
        this.accountHolderService = accountHolderService;
    }

    @RolesAllowed({"admin", "moderator"})
    @GetMapping
    public List<AccountHolder> getAll(){
        return accountHolderService.findAll();
    }

    @RolesAllowed({"admin", "moderator"})
    @GetMapping("/{id}")
    public AccountHolder getById(@PathVariable Long id) {
        return accountHolderService.getById(id);
    }

    @RolesAllowed({"admin", "moderator"})
    @PatchMapping("/update_address/{id}")
    public AccountHolderDTO updateAddress(@PathVariable Long id,
                                          @RequestBody Address address){
        return accountHolderService.updateAddress(id, address);
    }
   /* @PatchMapping("/add_account/{user_id}")
    public List<Account> addAccount(@PathVariable Long user_id,
                                         @RequestBody Long account_id{
        return accountHolderService.addAccount(user_id, account_id);
    }*/

}
