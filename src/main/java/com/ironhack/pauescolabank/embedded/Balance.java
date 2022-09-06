package com.ironhack.pauescolabank.embedded;

import com.ironhack.pauescolabank.model.HistoryLog;

import javax.persistence.Embeddable;

@Embeddable
public class Balance {
    private Money balance;
    private HistoryLog moneyMovement;
    private Money minimumBalance;
    private Money debt;
}
