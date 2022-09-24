package com.ironhack.pauescolabank.model;

import com.ironhack.pauescolabank.DTO.CheckingDTO;
import com.ironhack.pauescolabank.embedded.Money;
import com.ironhack.pauescolabank.embedded.MonthlyManteinanceFee;
import com.ironhack.pauescolabank.embedded.PenaltyFee;
import com.ironhack.pauescolabank.enums.AccountStatus;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Checking extends Account {
    @Embedded
    private MonthlyManteinanceFee monthlyManteinanceFee;
    @Embedded
    private PenaltyFee penaltyFee;
    private boolean isLessThan25;

    private final BigDecimal minimumBalance = BigDecimal.valueOf(250);


    public Checking(AccountHolder accountHolder) {
        this.setOwner(accountHolder);
        checkAge();
        if (isLessThan25) {
            this.monthlyManteinanceFee = null;
            this.penaltyFee = null;
        } else {
            this.monthlyManteinanceFee = new MonthlyManteinanceFee();
            this.penaltyFee = new PenaltyFee();
        }

    }


    @Override
    public void setBalance(BigDecimal balance) {
        if (this.getBalance() == null) super.balance = new Money(balance);
        this.getBalance().setMoney(balance);
    }

    public Checking fromDTO(CheckingDTO checkingDTO, AccountHolder accountHolder) {
        Checking checking = new Checking(accountHolder);
        checking.setSecretKey(checkingDTO.getSecretKey());
        checking.setAccountStatus(AccountStatus.ACTIVE);
        checking.setBalance(checkingDTO.getBalance());

        return checking;

    }

    public void checkAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int age = currentYear - this.getOwner().getBirthdate().getYear();
        this.setLessThan25(age < 25);
    }

    @Override
    public void doMaintenance() {
        this.checkAge();
        if (!this.isLessThan25) {
            getBalance().setMoney(
                    getBalance().getMoney().subtract(
                            getMonthlyManteinanceFee().getMonthlyMaintenanceFee()));
            if (isUnderMinimumBalance())
                getBalance().setMoney(
                        getBalance().getMoney().subtract(
                                getPenaltyFee().getPenaltyFee()));
        }

    }

    public boolean isUnderMinimumBalance() {
        boolean isTrue = getBalance().getMoney().intValue() < getMinimumBalance().intValue();
        return isTrue;
    }
}
