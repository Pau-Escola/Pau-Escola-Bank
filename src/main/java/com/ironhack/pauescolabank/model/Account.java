package com.ironhack.pauescolabank.model;

import com.ironhack.pauescolabank.embedded.Balance;
import com.ironhack.pauescolabank.embedded.Fee;
import com.ironhack.pauescolabank.embedded.InterestRate;
import com.ironhack.pauescolabank.enums.account.AccountStatus;
import com.ironhack.pauescolabank.enums.account.AccountType;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Account {
    @Id
    private Long id;
    private String secretKey;
    @ManyToOne
    private AccountHolder owner;
    //todo Aquest crec que s'hauria de fer amb lo d'embedable??
    //private AccountHolder secondaryOwner;
    @Embedded
    private List<Fee> fees;
    @Enumerated
    private AccountStatus accountStatus;
    @Embedded
    private Balance balance;
    @Embedded
    private InterestRate interest;
    @Enumerated
    private AccountType accountType;
    @OneToOne
    private HistoryLog log;
    @CreationTimestamp
    private Instant createdAt;

}
