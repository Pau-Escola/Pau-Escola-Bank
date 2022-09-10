package com.ironhack.pauescolabank.controllers;

import com.ironhack.pauescolabank.DTO.HistoryLogDTO;
import com.ironhack.pauescolabank.model.HistoryLog;
import com.ironhack.pauescolabank.services.HistoryLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/historylogs")
public class HistoryLogController {
    HistoryLogService historyLogService;

    public HistoryLogController(HistoryLogService historyLogService) {
        this.historyLogService = historyLogService;
    }

    @GetMapping
    public List<HistoryLog> getAll(){
        return historyLogService.findAll();
    }
    @GetMapping("/{id}")
    public HistoryLog getById(@PathVariable Long id){
        return historyLogService.getById(id);
    }
    @PostMapping
    public HistoryLog create(@RequestBody HistoryLogDTO historyLogDTO){
        return historyLogService.save(historyLogDTO);
    }


    //todo no hi haura delete

}
