package com.ironhack.pauescolabank.model;

import com.ironhack.pauescolabank.enums.LogType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class HistoryLog {
    @Id
    private Long id;
    //todo que faig??
    private HashMap<LocalDate, String> log;
    @Enumerated
    private LogType logType;
    //todo donar-li un parell de voltes
    private List<Long> relatedIds;
    @CreationTimestamp
    private Instant createdAt;
}
