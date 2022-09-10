package com.ironhack.pauescolabank.services;

import com.ironhack.pauescolabank.DTO.ThirdPartyDTO;
import com.ironhack.pauescolabank.model.Users.ThirdParty;
import com.ironhack.pauescolabank.repositories.ThirdPartyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ThirdPartyService {
    ThirdPartyRepository thirdPartyRepository;

    public ThirdPartyService(ThirdPartyRepository thirdPartyRepository) {
        this.thirdPartyRepository = thirdPartyRepository;
    }

    public List<ThirdParty> findAll() {
        return thirdPartyRepository.findAll();
    }

    public ThirdParty save(ThirdPartyDTO thirdPartyDTO) {
        ThirdParty thirdParty = new ThirdParty();
        return thirdPartyRepository.save(thirdParty.fromDTO(thirdPartyDTO));

    }

    public String delete(Long id) {
        thirdPartyRepository.delete(
                thirdPartyRepository.findById(id).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "There's no Third Party Bank with id: " + id)));


        return "Third Party Bank with id: " + id + " has been removed from the database";

    }

    public ThirdParty getById(Long id) {
        return thirdPartyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "There's no Third Party Bank with id: " + id));
    }
}
