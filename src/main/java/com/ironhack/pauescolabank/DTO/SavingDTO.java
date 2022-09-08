package com.ironhack.pauescolabank.DTO;

import com.ironhack.pauescolabank.embedded.Money;
import com.ironhack.pauescolabank.embedded.PenaltyFee;
import com.ironhack.pauescolabank.enums.AccountStatus;
import com.ironhack.pauescolabank.model.Checking;
import com.ironhack.pauescolabank.model.HistoryLog;
import com.ironhack.pauescolabank.model.Saving;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class SavingDTO {

    private String secretKey;
    private AccountHolder owner;
    private AccountStatus accountStatus;
    private Money balance;
    private HistoryLogDTO log;
    private PenaltyFee penaltyFee;
    private double interestRate;
    private BigDecimal minimumBalance;

    public SavingDTO fromEntity(Saving saving){
        SavingDTO savingDTO = new SavingDTO(
                saving.getSecretKey(),
                saving.getOwner(),
                saving.getAccountStatus(),
                saving.getBalance(),
                log.fromEntity(saving.getLog()),
                saving.getPenaltyFee(),
                saving.getInterestRate(),
                saving.getMinimumBalance()
        );
        return savingDTO;
    }
}
