package com.ironhack.pauescolabank.DTO;

import com.ironhack.pauescolabank.embedded.Money;
import com.ironhack.pauescolabank.embedded.MonthlyManteinanceFee;
import com.ironhack.pauescolabank.embedded.PenaltyFee;
import com.ironhack.pauescolabank.enums.AccountStatus;
import com.ironhack.pauescolabank.model.Checking;
import com.ironhack.pauescolabank.model.HistoryLog;
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
public class CheckingDTO {

    private String secretKey;
    private AccountHolder owner;
    private AccountStatus accountStatus;
    private Money balance;
    private HistoryLogDTO log;
    private PenaltyFee penaltyFee;
    private MonthlyManteinanceFee monthlyManteinanceFee;
    private BigDecimal minimumBalance;


    public CheckingDTO fromEntity(Checking checking){
        CheckingDTO checkingDTO = new CheckingDTO(
                checking.getSecretKey(),
                checking.getOwner(),
                checking.getAccountStatus(),
                checking.getBalance(),
                log.fromEntity(checking.getLog()),
                checking.getPenaltyFee(),
                checking.getMonthlyManteinanceFee(),
                checking.getMinimumBalance()
        );
        return checkingDTO;
    }
}
