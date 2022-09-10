package com.ironhack.pauescolabank.model.Users;

import com.ironhack.pauescolabank.DTO.AccountHolderDTO;
import com.ironhack.pauescolabank.model.Account;
import com.ironhack.pauescolabank.embedded.Address;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class AccountHolder extends User {
    private LocalDate birthdate;
    @Embedded
    private Address address;
    private String email;
    private Double rating;
    @OneToMany
    private List<Account> accounts;

    public AccountHolder fromDTO(AccountHolderDTO accountHolderDTO){
      AccountHolder accountHolder = new AccountHolder();
      accountHolder.setName(accountHolderDTO.getName());
      accountHolder.setPassword(accountHolderDTO.getPassword());
      accountHolder.setEmail(accountHolderDTO.getEmail());
      accountHolder.setAddress(new Address(
              accountHolderDTO.getCountry(),
              accountHolderDTO.getCity(),
              accountHolderDTO.getZipCode(),
              accountHolderDTO.getStreet()
      ));
      return accountHolder;
    }

}
