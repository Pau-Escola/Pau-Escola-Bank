package com.ironhack.pauescolabank.model;

import com.ironhack.pauescolabank.enums.LogType;
import com.ironhack.pauescolabank.model.Users.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
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
}
