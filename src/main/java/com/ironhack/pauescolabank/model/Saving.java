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


    public Saving() {

        this.penaltyFee = new PenaltyFee();
        this.creationMoment = LocalDate.now();
    }

    //todo mirar si puc fer algo per evitar duplicacions en el codi
    public Saving fromDTO(SavingDTO savingDTO, AccountHolder accountHolder){
        Saving saving = new Saving();
        saving.setSecretKey(savingDTO.getSecretKey());
        saving.setOwner(accountHolder);
        saving.setAccountStatus(savingDTO.getAccountStatus());
        saving.setBalance(savingDTO.getBalance());
        saving.setInterestRate(savingDTO.getInterestRate());
        saving.setMinimumBalance(savingDTO.getMinimumBalance());

        return saving;
    }

    @Override
    public void doMaintenance() {

        if(isUnderMinimumBalance()) getBalance().getMoney().subtract(getPenaltyFee().getPenaltyFee());
        if(itsTimeToAddMoney())

    }

    public boolean itsTimeToAddMoney(){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int creationYear = creationMoment.getYear();
        int creationMonth = creationMoment.getMonthValue();
        boolean isTrue = ((currentYear-creationYear == 1) && (currentMonth == creationMonth));

        return isTrue;
    }
    public void doInterestAdding(){
        BigDecimal interestGenerated = BigDecimal.valueOf(getBalance().getMoney().doubleValue()*this.interestRate);
        getBalance().getMoney().add(interestGenerated);
    }

    public boolean isUnderMinimumBalance(){
        boolean isTrue = getBalance().getMoney().intValue()< getMinimumBalance().intValue();
        return isTrue ;
    }
}
