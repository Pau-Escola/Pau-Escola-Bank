package com.ironhack.pauescolabank.model;

import com.ironhack.pauescolabank.DTO.CreditDTO;
import com.ironhack.pauescolabank.DTO.SavingDTO;
import com.ironhack.pauescolabank.embedded.Money;
import com.ironhack.pauescolabank.embedded.PenaltyFee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Saving extends Account {
    @Embedded
    private PenaltyFee penaltyFee;
    private double interestRate;
    private BigDecimal minimumBalance;


    //todo mirar si puc fer algo per evitar duplicacions en el codi
    public Saving fromDTO(SavingDTO savingDTO){
        Saving saving = new Saving();
        saving.setSecretKey(savingDTO.getSecretKey());
        saving.setOwner(savingDTO.getOwner());
        saving.setAccountStatus(savingDTO.getAccountStatus());
        saving.setBalance(savingDTO.getBalance());
        saving.setLog(getLog().fromDTO(savingDTO.getLog()));
        saving.setPenaltyFee(savingDTO.getPenaltyFee());
        saving.setInterestRate(savingDTO.getInterestRate());
        saving.setMinimumBalance(savingDTO.getMinimumBalance());

        return saving;
    }

    @Override
    public void doMaintenance() {

    }
}
