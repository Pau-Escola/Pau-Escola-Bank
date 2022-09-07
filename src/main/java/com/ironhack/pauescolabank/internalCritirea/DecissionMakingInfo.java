package com.ironhack.pauescolabank.internalCritirea;

import com.ironhack.pauescolabank.model.HistoryLog;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

public class DecissionMakingInfo {
    private List<HistoryLog> historyLogList;
    private LogStatistic generatedStat;
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant lastUpdateTime;
}
