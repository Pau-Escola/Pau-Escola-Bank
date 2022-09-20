package com.ironhack.pauescolabank.DTO;

import com.ironhack.pauescolabank.embedded.Money;
import com.ironhack.pauescolabank.enums.AccountStatus;
import com.ironhack.pauescolabank.model.Account;
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
public abstract class AccountDTO {

    private String secretKey;
    private Long owner_id;
    private AccountStatus accountStatus;
    private BigDecimal balance;

    public AccountDTO(String secretKey, AccountStatus accountStatus, BigDecimal balance) {
        this.secretKey = secretKey;
        this.accountStatus = accountStatus;
        this.balance = balance;
    }
}
