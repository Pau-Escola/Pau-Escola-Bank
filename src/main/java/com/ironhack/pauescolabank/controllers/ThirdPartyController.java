package com.ironhack.pauescolabank.controllers;

import com.ironhack.pauescolabank.DTO.ThirdPartyDTO;
import com.ironhack.pauescolabank.model.Users.ThirdParty;
import com.ironhack.pauescolabank.services.ThirdPartyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/thirdparties")
public class ThirdPartyController {
    ThirdPartyService thirdPartyService;

    public ThirdPartyController(ThirdPartyService thirdPartyService) {
        this.thirdPartyService = thirdPartyService;
    }

    @GetMapping
    public List<ThirdParty> getAll(){
        return thirdPartyService.findAll();
    }
    @GetMapping("/{id}")
    public ThirdParty getById(@PathVariable Long id) {
        return thirdPartyService.getById(id);
    }

}
