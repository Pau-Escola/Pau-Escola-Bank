package com.ironhack.pauescolabank.model.Users;

import com.ironhack.pauescolabank.model.HistoryLog;
import com.ironhack.pauescolabank.model.Order;
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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String password;
    @OneToMany
    private List<HistoryLog> logHistory;
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant lastUpdateTime;
    @OneToMany
    private List<Order> pendingOrders;
}
