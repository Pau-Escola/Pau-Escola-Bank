package com.ironhack.pauescolabank.services;

import com.ironhack.pauescolabank.DTO.HistoryLogDTO;
import com.ironhack.pauescolabank.model.HistoryLog;
import com.ironhack.pauescolabank.repositories.HistoryLogRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class HistoryLogService {
    HistoryLogRepository historyLogRepository;

    public HistoryLogService(HistoryLogRepository historyLogRepository) {
        this.historyLogRepository = historyLogRepository;
    }

    public List<HistoryLog> findAll() {
        return historyLogRepository.findAll();
    }

    public HistoryLog getById(Long id) {
        return historyLogRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "There's no History Log with id: " + id));
    }

    public HistoryLog save(HistoryLogDTO historyLogDTO) {
        HistoryLog historyLog = new HistoryLog();
        return historyLogRepository.save(historyLog.fromDTO(historyLogDTO));
    }
}
