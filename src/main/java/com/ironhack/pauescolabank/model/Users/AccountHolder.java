package com.ironhack.pauescolabank.model.Users;

import com.ironhack.pauescolabank.model.Account;
import com.ironhack.pauescolabank.embedded.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
@Entity
@NoArgsConstructor
@Getter
@Setter
public class AccountHolder extends User {
    private LocalDate birthdate;
    @Embedded
    private Address address;
    private String email;
    private Double rating;
    @OneToMany
    private List<Account> accounts;
}
