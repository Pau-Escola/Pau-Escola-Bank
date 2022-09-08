package com.ironhack.pauescolabank.DTO;

import com.ironhack.pauescolabank.enums.LogType;
import com.ironhack.pauescolabank.model.HistoryLog;
import com.ironhack.pauescolabank.utilities.LogJsonFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class HistoryLogDTO {
    private String logInJsonFormatted;
    private LogType logType;
    private OrderDTO orderDTO;

    public HistoryLogDTO fromEntity(HistoryLog log) {
        LogJsonFormatter logJsonFormatter = new LogJsonFormatter();
        HistoryLogDTO historyLogDTO = new HistoryLogDTO(
                logJsonFormatter.toDTO(log.getLogInJson()),
                log.getLogType(),
                orderDTO.fromEntity(log.getOrder())
        );
        return historyLogDTO;
    }
}
