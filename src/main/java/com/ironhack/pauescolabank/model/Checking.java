package com.ironhack.pauescolabank.model;

import com.ironhack.pauescolabank.DTO.CheckingDTO;
import com.ironhack.pauescolabank.DTO.CreditDTO;
import com.ironhack.pauescolabank.embedded.Fee;
import com.ironhack.pauescolabank.embedded.Money;
import com.ironhack.pauescolabank.embedded.MonthlyManteinanceFee;
import com.ironhack.pauescolabank.embedded.PenaltyFee;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Checking extends Account {
    @Embedded
    private MonthlyManteinanceFee monthlyManteinanceFee;
    @Embedded
    private PenaltyFee penaltyFee;
    private boolean isLessThan25;

    private BigDecimal minimumBalance;

    public Checking fromDTO(CheckingDTO checkingDTO){
        Checking checking = new Checking();
        checking.setSecretKey(checkingDTO.getSecretKey());
        checking.setOwner(checkingDTO.getOwner());
        checking.setAccountStatus(checkingDTO.getAccountStatus());
        checking.setBalance(checkingDTO.getBalance());
        checking.setLog(getLog().fromDTO(checkingDTO.getLog()));
        checking.setPenaltyFee(checkingDTO.getPenaltyFee());
        checking.setMonthlyManteinanceFee(checkingDTO.getMonthlyManteinanceFee());
        checking.setMinimumBalance(checkingDTO.getMinimumBalance());

        return checking;
    }
    @Override
    public void doMaintenance() {

    }
}
