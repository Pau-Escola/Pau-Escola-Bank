package com.ironhack.pauescolabank.model;

import com.ironhack.pauescolabank.DTO.HistoryLogDTO;
import com.ironhack.pauescolabank.enums.LogType;
import com.ironhack.pauescolabank.model.Users.User;
import com.ironhack.pauescolabank.utilities.LogJsonFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class HistoryLog {
    @Id
    @GeneratedValue
    private Long id;
    //todo aquest json serà el log propiament dit s'haurà de poder traduir a un hash map
    private String logInJson;
    @Enumerated
    private LogType logType;
    @ManyToOne
    private User requester;
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant lastUpdateTime;
    @OneToOne
    private Order order;

    public HistoryLog fromDTO(HistoryLogDTO log) {
        LogJsonFormatter logJsonFormatter = new LogJsonFormatter();
        HistoryLog logEntity = new HistoryLog();
       logEntity.setLogInJson(logJsonFormatter.toEntity(log.getLogInJsonFormatted()));
       logEntity.setLogType(log.getLogType());
       logEntity.setOrder(order.fromDTO(log.getOrderDTO()));

       return logEntity;
    }
}
