package com.ironhack.pauescolabank.controllers;

import com.ironhack.pauescolabank.DTO.CheckingDTO;
import com.ironhack.pauescolabank.model.Checking;
import com.ironhack.pauescolabank.services.CheckingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts/checkings")
public class CheckingController implements IAccountController {
    CheckingService checkingService;

    public CheckingController(CheckingService checkingService) {
        this.checkingService = checkingService;
    }

    @Override
    @GetMapping
    public List<Checking> getAll() {
        return checkingService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Checking getById(@PathVariable Long id) {
        return checkingService.getById(id);
    }

    @PostMapping
    public Checking create(@RequestBody CheckingDTO checkingDTO) {
        return checkingService.save(checkingDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return checkingService.delete(id);
    }
}
