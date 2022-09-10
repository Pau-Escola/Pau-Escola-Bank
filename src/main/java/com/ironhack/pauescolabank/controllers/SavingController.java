package com.ironhack.pauescolabank.controllers;

import com.ironhack.pauescolabank.DTO.SavingDTO;
import com.ironhack.pauescolabank.model.Saving;
import com.ironhack.pauescolabank.services.SavingService;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public Saving create(@RequestBody SavingDTO savingDTO){
        return savingService.save(savingDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return savingService.delete(id);
    }
}
