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
public class SavingDTO extends AccountDTO {


    private PenaltyFee penaltyFee;
    private double interestRate;
    private BigDecimal minimumBalance;

    public SavingDTO(
            String secretKey,
            Long owner_id,
            AccountStatus accountStatus,
            BigDecimal balance,
            PenaltyFee penaltyFee,
            double interestRate,
            BigDecimal minimumBalance) {
        super(secretKey, owner_id, accountStatus, balance);
        setPenaltyFee(penaltyFee);
        setInterestRate(interestRate);
        setMinimumBalance(minimumBalance);
    }

    public SavingDTO(
            String secretKey,
            AccountStatus accountStatus,
            BigDecimal balance,
            PenaltyFee penaltyFee,
            double interestRate,
            BigDecimal minimumBalance) {
        super(secretKey, accountStatus, balance);
        setPenaltyFee(penaltyFee);
        setInterestRate(interestRate);
        setMinimumBalance(minimumBalance);
    }


    public SavingDTO fromEntity(Saving saving){
        SavingDTO savingDTO = new SavingDTO(
                saving.getSecretKey(),
                saving.getOwner().getId(),
                saving.getAccountStatus(),
                saving.getBalance().getMoney(),
                saving.getPenaltyFee(),
                saving.getInterestRate(),
                saving.getMinimumBalance()
        );
        return savingDTO;
    }
}
