package com.ironhack.pauescolabank.model;

import com.ironhack.pauescolabank.enums.OrderStatus;
import com.ironhack.pauescolabank.model.Users.User;
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
public class Order {
    @Id
    private Long id;
    @ManyToOne
    private User requester_id;
    private String request;

    @Enumerated
    private OrderStatus orderStatus;
    @OneToOne
    private HistoryLog statusLog;
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant lastUpdateTime;
}
