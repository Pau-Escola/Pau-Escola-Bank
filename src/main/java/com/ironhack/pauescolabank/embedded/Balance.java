package com.ironhack.pauescolabank.embedded;

import com.ironhack.pauescolabank.model.HistoryLog;

import javax.persistence.Embeddable;

@Embeddable
public class Balance {
    private Money balance;

}
