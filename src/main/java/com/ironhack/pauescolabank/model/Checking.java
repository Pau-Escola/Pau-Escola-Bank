package com.ironhack.pauescolabank.model;

import com.ironhack.pauescolabank.embedded.Fee;
import com.ironhack.pauescolabank.embedded.Money;
import com.ironhack.pauescolabank.embedded.MonthlyManteinanceFee;
import com.ironhack.pauescolabank.embedded.PenaltyFee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Checking extends Account {
    @Embedded
    private MonthlyManteinanceFee monthlyManteinanceFee;
    @Embedded
    private PenaltyFee penaltyFee;
    private boolean isLessThan25;

    private BigDecimal minimumBalance;
    @Override
    public void doMaintenance() {

    }
}
