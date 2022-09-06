package com.ironhack.pauescolabank.model.Users;

import com.ironhack.pauescolabank.model.HistoryLog;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;

@Entity
@NoArgsConstructor
@Getter
@Setter
public abstract class User {
    @Id
    private Long id;
    private String name;
    private String password;
    @OneToMany
    private HashMap<LocalDate, HistoryLog> logHistory;
    @CreationTimestamp
    private Instant createdAt;
}
