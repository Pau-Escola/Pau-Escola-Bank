package com.ironhack.pauescolabank.controllers;

import com.ironhack.pauescolabank.DTO.CreditDTO;
import com.ironhack.pauescolabank.model.Checking;
import com.ironhack.pauescolabank.model.Credit;
import com.ironhack.pauescolabank.services.CreditService;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public Credit create(@RequestBody CreditDTO creditDTO){
        return creditService.save(creditDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return creditService.delete(id);
    }
}
