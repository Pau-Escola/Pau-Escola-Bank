package com.ironhack.pauescolabank.model;

import com.ironhack.pauescolabank.DTO.CheckingDTO;
import com.ironhack.pauescolabank.DTO.CreditDTO;
import com.ironhack.pauescolabank.embedded.Fee;
import com.ironhack.pauescolabank.embedded.Money;
import com.ironhack.pauescolabank.embedded.MonthlyManteinanceFee;
import com.ironhack.pauescolabank.embedded.PenaltyFee;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
@Entity

@Getter
@Setter
@AllArgsConstructor
public class Checking extends Account {
    @Embedded
    private MonthlyManteinanceFee monthlyManteinanceFee;
    @Embedded
    private PenaltyFee penaltyFee;
    private boolean isLessThan25;

    private final BigDecimal minimumBalance = BigDecimal.valueOf(250);


    public Checking() {
    this.monthlyManteinanceFee = new MonthlyManteinanceFee();
    this.penaltyFee = new PenaltyFee();
    }

    public Checking fromDTO(CheckingDTO checkingDTO, AccountHolder accountHolder){
        Checking checking = new Checking();
        checking.setSecretKey(checkingDTO.getSecretKey());
        checking.setOwner(accountHolder);
        checking.setAccountStatus(checkingDTO.getAccountStatus());
        checking.setBalance(checkingDTO.getBalance());

        return checking;
    }
    @Override
    public void doMaintenance() {

    }
}
