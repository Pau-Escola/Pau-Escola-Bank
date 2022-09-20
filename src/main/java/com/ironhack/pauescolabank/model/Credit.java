package com.ironhack.pauescolabank.model;

import com.ironhack.pauescolabank.DTO.CreditDTO;
import com.ironhack.pauescolabank.embedded.Money;
import com.ironhack.pauescolabank.embedded.PenaltyFee;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Entity

@Getter
@Setter
@AllArgsConstructor
public class Credit extends Account {
    @Embedded
    private PenaltyFee penaltyFee;
    @DecimalMax("0.2")
    @DecimalMin("0.1")
    private Double interestRate;
    private BigDecimal moneyOwed;
    @Range(min = 100, max = 100000)
    private BigDecimal creditLimit;

    public Credit() {
        this.penaltyFee = new PenaltyFee();
    }

    public Credit fromDTO(CreditDTO creditDTO, AccountHolder accountHolder){
        Credit credit = new Credit();
        credit.setSecretKey(creditDTO.getSecretKey());
        credit.setOwner(accountHolder);
        credit.setAccountStatus(creditDTO.getAccountStatus());
        credit.setBalance(creditDTO.getBalance());
        credit.setInterestRate(creditDTO.getInterestRate());
        credit.setMoneyOwed(creditDTO.getMoneyOwed());
        credit.setCreditLimit(creditDTO.getCreditLimit());

        return credit;
    }

    @Override
    public void setBalance(BigDecimal balance) {
        if (this.getBalance() ==  null)super.balance=new Money(balance);
        this.getBalance().setMoney(balance);
    }

    @Override
    public void doMaintenance() {

    }
}
