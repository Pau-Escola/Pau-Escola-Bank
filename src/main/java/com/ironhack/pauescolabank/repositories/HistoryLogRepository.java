package com.ironhack.pauescolabank.repositories;

import com.ironhack.pauescolabank.model.HistoryLog;
import com.ironhack.pauescolabank.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryLogRepository extends JpaRepository<HistoryLog, Long> {
}
