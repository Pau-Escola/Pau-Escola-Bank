package com.ironhack.pauescolabank.DTO;

import com.ironhack.pauescolabank.model.Users.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class AccountHolderDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String country;
    private String city;
    private Integer zipCode;
    private String street;
    private int yearOfBirth;
    private int monthOfBirth;
    private int dayOfBirth;


    public AccountHolderDTO fromEntity(AccountHolder accountHolder){
        var accountHolderDTO = new AccountHolderDTO();
        accountHolderDTO.setFirstName(accountHolder.getFirstName());
        accountHolderDTO.setLastName(accountHolder.getLastName());
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
