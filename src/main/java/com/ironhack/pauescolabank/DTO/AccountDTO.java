package com.ironhack.pauescolabank.DTO;

import com.ironhack.pauescolabank.embedded.Money;
import com.ironhack.pauescolabank.enums.AccountStatus;
import com.ironhack.pauescolabank.model.Account;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public abstract class AccountDTO {

    private String secretKey;
    private Long owner_id;
    private AccountStatus accountStatus;
    private Money balance;

    public AccountDTO(String secretKey, AccountStatus accountStatus, Money balance) {
        this.secretKey = secretKey;
        this.accountStatus = accountStatus;
        this.balance = balance;
    }
}
