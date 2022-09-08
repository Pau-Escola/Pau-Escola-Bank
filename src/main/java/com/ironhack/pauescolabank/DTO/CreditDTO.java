package com.ironhack.pauescolabank.DTO;

import com.ironhack.pauescolabank.embedded.Money;
import com.ironhack.pauescolabank.embedded.PenaltyFee;
import com.ironhack.pauescolabank.enums.AccountStatus;
import com.ironhack.pauescolabank.model.Credit;
import com.ironhack.pauescolabank.model.HistoryLog;
import com.ironhack.pauescolabank.model.Saving;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CreditDTO {

    private String secretKey;
    private AccountHolder owner;
    private AccountStatus accountStatus;
    private Money balance;
    private HistoryLogDTO log;
    private PenaltyFee penaltyFee;
    private double interestRate;
    private BigDecimal moneyOwed;
    private BigDecimal creditLimit;


    public CreditDTO fromEntity(Credit credit){
        CreditDTO creditDTO = new CreditDTO(
                credit.getSecretKey(),
                credit.getOwner(),
                credit.getAccountStatus(),
                credit.getBalance(),
                log.fromEntity(credit.getLog()),
                credit.getPenaltyFee(),
                credit.getInterestRate(),
                credit.getMoneyOwed(),
                credit.getCreditLimit()
        );
        return creditDTO;
    }
}
