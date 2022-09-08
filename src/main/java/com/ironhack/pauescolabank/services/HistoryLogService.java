package com.ironhack.pauescolabank.services;

import com.ironhack.pauescolabank.repositories.HistoryLogRepository;
import org.springframework.stereotype.Service;

@Service
public class HistoryLogService {
    HistoryLogRepository historyLogRepository;

    public HistoryLogService(HistoryLogRepository historyLogRepository) {
        this.historyLogRepository = historyLogRepository;
    }
}
