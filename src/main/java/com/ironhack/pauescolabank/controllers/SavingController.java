package com.ironhack.pauescolabank.controllers;

import com.ironhack.pauescolabank.DTO.SavingDTO;
import com.ironhack.pauescolabank.embedded.Money;
import com.ironhack.pauescolabank.enums.AccountStatus;
import com.ironhack.pauescolabank.model.Checking;
import com.ironhack.pauescolabank.model.Saving;
import com.ironhack.pauescolabank.services.SavingService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts/savings")
public class SavingController {
    SavingService savingService;

    public SavingController(SavingService savingService) {
        this.savingService = savingService;
    }

    @GetMapping
    public List<Saving> getAll(){
        return savingService.findAll();
    }

    @GetMapping("/{id}")
    public Saving getById(@PathVariable Long id) {
        return savingService.getById(id);
    }
    @PostMapping("/{id}")
    public Saving create(@RequestBody @Valid SavingDTO savingDTO, @PathVariable Long id){

        return savingService.save(savingDTO, id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return savingService.delete(id);
    }

    @PutMapping("/edit_whole/{id}")
    public Saving updateAll(@PathVariable Long id, @RequestBody Saving saving){
        return savingService.updateAll(id, saving);
    }

    @PatchMapping("/update_status/{id}")
    public SavingDTO updateStatus(@PathVariable Long id,
                                    @RequestBody AccountStatus status){
        return savingService.updateStatus(id, status);
    }
    @PatchMapping("/update_balance/{id}")
    public SavingDTO updateBalance(@PathVariable Long id,
                                    @RequestBody BigDecimal money){
        return savingService.updateBalance(id, money);
    }
}
