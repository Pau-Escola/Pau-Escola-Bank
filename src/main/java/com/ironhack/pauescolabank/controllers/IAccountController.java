package com.ironhack.pauescolabank.controllers;

import com.ironhack.pauescolabank.DTO.AccountDTO;
import com.ironhack.pauescolabank.DTO.CheckingDTO;
import com.ironhack.pauescolabank.model.Checking;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IAccountController {
    @GetMapping
    List<Checking> getAll();

    @GetMapping("/{id}")
    Checking getById(@PathVariable Long id);



    @DeleteMapping("/{id}")
    String delete(@PathVariable Long id);
}
