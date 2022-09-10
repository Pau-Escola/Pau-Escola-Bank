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
public class CheckingDTO extends AccountDTO {


    private PenaltyFee penaltyFee;
    private MonthlyManteinanceFee monthlyManteinanceFee;
    private BigDecimal minimumBalance;

    public CheckingDTO(
            String secretKey,
            AccountHolder owner,
            AccountStatus accountStatus,
            Money balance,
            HistoryLogDTO log,
            PenaltyFee penaltyFee,
            MonthlyManteinanceFee monthlyManteinanceFee,
            BigDecimal minimumBalance) {
        super(secretKey, owner, accountStatus, balance, log);
        setPenaltyFee(penaltyFee);
        setMonthlyManteinanceFee(monthlyManteinanceFee);
        setMinimumBalance(minimumBalance);
    }


    public CheckingDTO fromEntity(Checking checking){
        CheckingDTO checkingDTO = new CheckingDTO(
                checking.getSecretKey(),
                checking.getOwner(),
                checking.getAccountStatus(),
                checking.getBalance(),
                this.getLog().fromEntity(checking.getLog()),
                checking.getPenaltyFee(),
                checking.getMonthlyManteinanceFee(),
                checking.getMinimumBalance()
        );
        return checkingDTO;
    }
}
