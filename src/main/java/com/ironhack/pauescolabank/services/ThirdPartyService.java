package com.ironhack.pauescolabank.services;

import com.ironhack.pauescolabank.repositories.ThirdPartyRepository;
import org.springframework.stereotype.Service;

@Service
public class ThirdPartyService {
    ThirdPartyRepository thirdPartyRepository;

    public ThirdPartyService(ThirdPartyRepository thirdPartyRepository) {
        this.thirdPartyRepository = thirdPartyRepository;
    }
}
