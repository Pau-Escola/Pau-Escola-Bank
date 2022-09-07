package com.ironhack.pauescolabank.model;

import com.ironhack.pauescolabank.embedded.Money;
import com.ironhack.pauescolabank.embedded.PenaltyFee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Credit extends Account {
    @Embedded
    private PenaltyFee penaltyFee;
    private double interestRate;
    private BigDecimal moneyOwed;
    private BigDecimal creditLimit;

    @Override
    public void doMaintenance() {

    }
}
