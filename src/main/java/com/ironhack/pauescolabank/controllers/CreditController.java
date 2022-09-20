package com.ironhack.pauescolabank.controllers;

import com.ironhack.pauescolabank.DTO.CreditDTO;
import com.ironhack.pauescolabank.embedded.Money;
import com.ironhack.pauescolabank.enums.AccountStatus;
import com.ironhack.pauescolabank.model.Checking;
import com.ironhack.pauescolabank.model.Credit;
import com.ironhack.pauescolabank.model.Saving;
import com.ironhack.pauescolabank.services.CreditService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts/credits")
public class CreditController {
    CreditService creditService;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping
    public List<Credit> getAll(){
        return creditService.findAll();
    }

    @GetMapping("/{id}")
    public Credit getById(@PathVariable Long id) {
        return creditService.getById(id);
    }
    @PostMapping("/{id}")
    public Credit create(@RequestBody @Valid CreditDTO creditDTO, @PathVariable Long id){
        return creditService.save(creditDTO, id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return creditService.delete(id);
    }

    @PutMapping("/edit_whole/{id}")
    public Credit updateAll(@PathVariable Long id, @RequestBody Credit credit){
        return creditService.updateAll(id, credit);
    }

    @PatchMapping("/update_status/{id}")
    public CreditDTO updateStatus(@PathVariable Long id,
                                    @RequestBody AccountStatus status){
        return creditService.updateStatus(id, status);
    }
    @PatchMapping("/update_balance/{id}")
    public CreditDTO updateBalance(@PathVariable Long id,
                                    @RequestBody BigDecimal money){
        return creditService.updateBalance(id, money);
    }
}
