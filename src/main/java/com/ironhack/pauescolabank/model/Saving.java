package com.ironhack.pauescolabank.model;

import com.ironhack.pauescolabank.embedded.Money;
import com.ironhack.pauescolabank.embedded.PenaltyFee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Saving extends Account {
    @Embedded
    private PenaltyFee penaltyFee;
    private double interestRate;
    private BigDecimal minimumBalance;

    @Override
    public void doMaintenance() {

    }
}
