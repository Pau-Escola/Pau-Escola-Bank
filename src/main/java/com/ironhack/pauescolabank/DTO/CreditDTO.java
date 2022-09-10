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
public class CreditDTO extends AccountDTO {


    private PenaltyFee penaltyFee;
    private double interestRate;
    private BigDecimal moneyOwed;
    private BigDecimal creditLimit;

    public CreditDTO(
            String secretKey,
            Long owner_id,
            AccountStatus accountStatus,
            Money balance,
            PenaltyFee penaltyFee,
            double interestRate,
            BigDecimal moneyOwed,
            BigDecimal creditLimit) {
        super(secretKey, owner_id, accountStatus, balance);
        setPenaltyFee(penaltyFee);
        setInterestRate(interestRate);
        setMoneyOwed(moneyOwed);
        setCreditLimit(creditLimit);
    }

    public CreditDTO(
            String secretKey,

            AccountStatus accountStatus,
            Money balance,
            PenaltyFee penaltyFee,
            double interestRate,
            BigDecimal moneyOwed,
            BigDecimal creditLimit) {
        super(secretKey, accountStatus, balance);
        setPenaltyFee(penaltyFee);
        setInterestRate(interestRate);
        setMoneyOwed(moneyOwed);
        setCreditLimit(creditLimit);
    }


    public CreditDTO fromEntity(Credit credit) {
        CreditDTO creditDTO = new CreditDTO(
                credit.getSecretKey(),
                credit.getOwner().getId(),
                credit.getAccountStatus(),
                credit.getBalance(),
                credit.getPenaltyFee(),
                credit.getInterestRate(),
                credit.getMoneyOwed(),
                credit.getCreditLimit()
        );
        return creditDTO;
    }
}
