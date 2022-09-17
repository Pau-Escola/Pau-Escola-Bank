package com.ironhack.pauescolabank.model;

import com.ironhack.pauescolabank.DTO.SavingDTO;
import com.ironhack.pauescolabank.embedded.PenaltyFee;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Saving extends Account {
    @Embedded
    private PenaltyFee penaltyFee;
    @DecimalMax("0.5")
    @DecimalMin("0.0025")
    private Double interestRate;
    @Range(min=100, max = 1000)
    private BigDecimal minimumBalance;
    private LocalDate creationMoment;
    private BigDecimal creditorNumber;
    private LocalDate lastInterestPayment;


    public Saving() {

        this.penaltyFee = new PenaltyFee();
        this.creationMoment = LocalDate.now();
        this.creditorNumber = BigDecimal.valueOf(0);
        this.lastInterestPayment = creationMoment;
    }

    //todo mirar si puc fer algo per evitar duplicacions en el codi
    public Saving fromDTO(SavingDTO savingDTO, AccountHolder accountHolder){
        Saving saving = new Saving();
        saving.setSecretKey(savingDTO.getSecretKey());
        saving.setOwner(accountHolder);
        saving.setAccountStatus(savingDTO.getAccountStatus());
        saving.updateBalance(savingDTO.getBalance().getMoney());
        saving.setInterestRate(savingDTO.getInterestRate());
        saving.setMinimumBalance(savingDTO.getMinimumBalance());

        return saving;
    }
    public void updateBalance(BigDecimal bd){
        int dayMultiplier = getDayCount(getBalance().getDateTracker().getDayOfYear());
        creditorNumber = creditorNumber.add(bd.multiply(BigDecimal.valueOf(dayMultiplier)));
        getBalance().setMoney(bd);
    }

    private int getDayCount(int dayOfYear) {
        var newTimeTracker = LocalDate.now();

        int count = newTimeTracker.getDayOfYear()- dayOfYear;

        if(newTimeTracker.getYear() != getBalance().getDateTracker().getYear()){
        count = 365 - dayOfYear + newTimeTracker.getDayOfYear();
        }

        getBalance().setDateTracker(newTimeTracker);
        return count;

    }

    @Override
    public void doMaintenance() {

        if(isUnderMinimumBalance()) applyPenaltyFee();
        if(itsTimeToAddMoney()) doInterestAdding();

    }

    private BigDecimal applyPenaltyFee() {
        return getBalance().getMoney().subtract(getPenaltyFee().getPenaltyFee());
    }

    public boolean itsTimeToAddMoney(){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int lastUpdatedYear = lastInterestPayment.getYear();
        int lastUpdatedMonth = lastInterestPayment.getMonthValue();
        boolean isTrue = ((currentYear-lastUpdatedYear == 1) && (currentMonth == lastUpdatedMonth));
        if (isTrue) lastInterestPayment = LocalDate.now();
        return isTrue;
    }
    public void doInterestAdding(){
        int creditorNumberValue = creditorNumber.intValue()+ getBalance().getMoney().intValue()*getDayCount(LocalDate.now().getDayOfYear());
        var moneyGenerated = BigDecimal.valueOf(creditorNumberValue*interestRate/365);
        var totalMoney = getBalance().getMoney().add(moneyGenerated);
        updateBalance(totalMoney);
        this.creditorNumber = BigDecimal.valueOf(0);
    }

    public boolean isUnderMinimumBalance(){
        boolean isTrue = getBalance().getMoney().intValue()< getMinimumBalance().intValue();
        return isTrue ;
    }
}
