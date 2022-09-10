package com.ironhack.pauescolabank.services;

import com.ironhack.pauescolabank.DTO.CreditDTO;
import com.ironhack.pauescolabank.model.Credit;
import com.ironhack.pauescolabank.repositories.CreditRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CreditService {
    CreditRepository creditRepository;

    public CreditService(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    public List<Credit> findAll() {
        return creditRepository.findAll();
    }

    public Credit save(CreditDTO creditDTO) {
        Credit credit = new Credit();
        return creditRepository.save(credit.fromDTO(creditDTO));
    }

    public String delete(Long id) {
        creditRepository.delete(
                creditRepository.findById(id).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "There's no Credit Account with id: " + id)));
        return "Credit Account with id: " + id + " has been removed from the database";
    }

    public Credit getById(Long id) {
        return creditRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There's no Credit Account with id: " + id));
    }
}
