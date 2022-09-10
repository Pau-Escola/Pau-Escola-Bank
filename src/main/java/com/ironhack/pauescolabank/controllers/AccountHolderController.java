package com.ironhack.pauescolabank.controllers;

import com.ironhack.pauescolabank.DTO.AccountHolderDTO;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import com.ironhack.pauescolabank.services.AccountHolderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/accountholders")
public class AccountHolderController {
    AccountHolderService accountHolderService;

    public AccountHolderController(AccountHolderService accountHolderService) {
        this.accountHolderService = accountHolderService;
    }

    @GetMapping
    public List<AccountHolder> getAll(){
        return accountHolderService.findAll();
    }
    @GetMapping("/{id}")
    public AccountHolder getById(@PathVariable Long id) {
        return accountHolderService.getById(id);
    }
    @PostMapping
    public AccountHolder create(@RequestBody AccountHolderDTO accountHolderDTO){
        return accountHolderService.save(accountHolderDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return accountHolderService.delete(id);
    }

    @PutMapping("/edit_whole/{id}")
    public AccountHolder updatePut(@PathVariable Long id, @RequestBody AccountHolder accountHolder){
        return accountHolderService.updatePut(id, accountHolder);
    }

    @PatchMapping("/update_status/{id}")
    public Checking updateNamePatch(@PathVariable Long id,
                                    @RequestBody AccountStatus status){
        return checkingService.updateStatus(id, status);
    }
    @PatchMapping("/update_balance/{id}")
    public Checking updateNamePatch(@PathVariable Long id,
                                    @RequestBody Money money){
        return checkingService.updateBalance(id, money);
    }

}
