package com.ironhack.pauescolabank.internal;

import com.ironhack.pauescolabank.model.HistoryLog;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;

public class DecissionMakingInfo {
    private List<HistoryLog> historyLogList;
    private LogStatistic generatedStat;
    @CreationTimestamp
    private Instant createdAt;

}
