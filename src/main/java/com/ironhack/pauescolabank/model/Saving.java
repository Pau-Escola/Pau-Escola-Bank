package com.ironhack.pauescolabank.model;

import com.ironhack.pauescolabank.DTO.SavingDTO;
import com.ironhack.pauescolabank.embedded.PenaltyFee;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Saving extends Account {
    @Embedded
    private PenaltyFee penaltyFee;
    private Double interestRate;

    private BigDecimal minimumBalance;

    public Saving() {
        this.penaltyFee = new PenaltyFee();
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

    }
}
