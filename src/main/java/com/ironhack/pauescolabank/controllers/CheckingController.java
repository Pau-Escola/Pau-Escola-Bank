package com.ironhack.pauescolabank.controllers;

import com.ironhack.pauescolabank.DTO.CheckingDTO;
import com.ironhack.pauescolabank.embedded.Money;
import com.ironhack.pauescolabank.enums.AccountStatus;
import com.ironhack.pauescolabank.enums.AuthorisationLevel;
import com.ironhack.pauescolabank.model.Checking;
import com.ironhack.pauescolabank.model.Users.Admin;
import com.ironhack.pauescolabank.services.CheckingService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts/checkings")
public class CheckingController  {
    CheckingService checkingService;

    public CheckingController(CheckingService checkingService) {
        this.checkingService = checkingService;
    }


    @GetMapping
    public List<Checking> getAll() {
        return checkingService.findAll();
    }


    @GetMapping("/{id}")
    public Checking getById(@PathVariable Long id) {
        return checkingService.getById(id);
    }

    @PostMapping("/{id}")
    public Checking create(@RequestBody @Valid CheckingDTO checkingDTO, @PathVariable Long id) {
        return checkingService.save(checkingDTO, id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return checkingService.delete(id);
    }



    @PatchMapping("/update_status/{id}")
    public CheckingDTO updateStatus(@PathVariable Long id,
                                     @RequestBody AccountStatus status){
        return checkingService.updateStatus(id, status);
    }
    @PatchMapping("/update_balance/{id}")
    public CheckingDTO updateBalance(@PathVariable Long id,
                                     @RequestBody BigDecimal money){
        return checkingService.updateBalance(id, money);
    }
}
