package com.ironhack.pauescolabank.services;

import com.ironhack.pauescolabank.DTO.SavingDTO;
import com.ironhack.pauescolabank.model.Saving;
import com.ironhack.pauescolabank.repositories.SavingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SavingService {
    SavingRepository savingRepository;

    public SavingService(SavingRepository savingRepository) {
        this.savingRepository = savingRepository;
    }

    public List<Saving> findAll() {
        return savingRepository.findAll();
    }

    public Saving save(SavingDTO savingDTO) {
        Saving saving = new Saving();
        return savingRepository.save(saving.fromDTO(savingDTO));
    }

    public String delete(Long id) {
        savingRepository.delete(
                savingRepository.findById(id).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "There's no Saving Account with id: " + id)));
        return "Saving Account with id: " + id + " has been removed from the database";
    }

    public Saving getById(Long id) {
        return savingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There's no Saving Account with id: " + id));
    }
}
