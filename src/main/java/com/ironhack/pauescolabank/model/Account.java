package com.ironhack.pauescolabank.model;

import com.ironhack.pauescolabank.embedded.Balance;
import com.ironhack.pauescolabank.embedded.Money;
import com.ironhack.pauescolabank.enums.AccountStatus;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
 public abstract class Account {
    @Id
    private Long id;
    private String secretKey;
    @ManyToOne
    private AccountHolder owner;
   //todo fer lo dels atributs overrides si cal
    /* @OneToOne
    private AccountHolder secondaryOwner;*/
    @Enumerated
    private AccountStatus accountStatus;
    @Embedded
    private Money balance;
    @OneToOne
    private HistoryLog log;
   @CreationTimestamp
   private Instant createdAt;
   @UpdateTimestamp
   private Instant lastUpdateTime;

    abstract public void doMaintenance();

}
