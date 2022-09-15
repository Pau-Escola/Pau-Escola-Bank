package com.ironhack.pauescolabank.DTO;

import com.ironhack.pauescolabank.embedded.Address;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import com.ironhack.pauescolabank.model.Users.Admin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class AccountHolderDTO {

    private String name;
    private String password;
    private String email;
    private String country;
    private String city;
    private Integer zipCode;
    private String street;
    private int yearOfBirth;
    private int monthOfBirth;
    private int dayOfBirth;


    public AccountHolderDTO fromEntity(AccountHolder accountHolder){
        var accountHolderDTO = new AccountHolderDTO();
        accountHolderDTO.setName(accountHolder.getName());
        accountHolderDTO.setPassword(accountHolder.getPassword());
        accountHolderDTO.setEmail(accountHolder.getEmail());
        accountHolderDTO.setCountry(accountHolder.getAddress().getCountry());
        accountHolderDTO.setCity(accountHolder.getAddress().getCity());
        accountHolderDTO.setZipCode(accountHolder.getAddress().getZipCode());
        accountHolderDTO.setStreet(accountHolder.getAddress().getStreet());
        accountHolderDTO.setYearOfBirth(accountHolder.getBirthdate().getYear());
        accountHolderDTO.setMonthOfBirth(accountHolder.getBirthdate().getMonthValue());
        accountHolderDTO.setDayOfBirth(accountHolder.getBirthdate().getDayOfMonth());


        return accountHolderDTO;
    }
}
