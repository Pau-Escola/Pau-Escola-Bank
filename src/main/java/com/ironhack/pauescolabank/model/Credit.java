package com.ironhack.pauescolabank.model;

import com.ironhack.pauescolabank.DTO.CreditDTO;
import com.ironhack.pauescolabank.embedded.Money;
import com.ironhack.pauescolabank.embedded.PenaltyFee;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Credit extends Account {
    @Embedded
    private PenaltyFee penaltyFee;
    private double interestRate;
    private BigDecimal moneyOwed;
    private BigDecimal creditLimit;

    public Credit fromDTO(CreditDTO creditDTO){
        Credit credit = new Credit();
        credit.setSecretKey(creditDTO.getSecretKey());
        credit.setOwner(creditDTO.getOwner());
        credit.setAccountStatus(creditDTO.getAccountStatus());
        credit.setBalance(creditDTO.getBalance());
        credit.setLog(getLog().fromDTO(creditDTO.getLog()));
        credit.setPenaltyFee(creditDTO.getPenaltyFee());
        credit.setInterestRate(creditDTO.getInterestRate());
        credit.setMoneyOwed(creditDTO.getMoneyOwed());
        credit.setCreditLimit(creditDTO.getCreditLimit());

        return credit;
    }

    @Override
    public void doMaintenance() {

    }
}
