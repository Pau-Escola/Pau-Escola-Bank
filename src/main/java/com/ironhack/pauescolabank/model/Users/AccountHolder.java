package com.ironhack.pauescolabank.model.Users;

import com.ironhack.pauescolabank.DTO.AccountHolderDTO;
import com.ironhack.pauescolabank.model.Account;
import com.ironhack.pauescolabank.embedded.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
@Entity
@NoArgsConstructor
@Getter
@Setter

public class AccountHolder extends User {
    @Past
    private LocalDate birthdate;
    @Embedded
    private Address address;
    @Email
    private String email;
    private Double rating;
    @OneToMany
    private List<Account> accounts;
    @AssertTrue
    private boolean isOver18;


    public AccountHolder(String firstName, String lastName,  String email, String password, LocalDate birthdate, Address address,  List<Account> accounts) {
        super(firstName, lastName, email, password);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        this.birthdate = birthdate;
        this.address = address;
        this.rating = 95.0;
        this.accounts = accounts;
        this.isOver18 = (currentYear - birthdate.getYear())>=18? true: false;
    }

    public AccountHolder fromDTO(AccountHolderDTO accountHolderDTO){
        var dateFromDTO = LocalDate.of(
                accountHolderDTO.getYearOfBirth(),
                accountHolderDTO.getMonthOfBirth(),
                accountHolderDTO.getDayOfBirth());
        var addressFromDTO = new Address(
                accountHolderDTO.getCountry(),
                accountHolderDTO.getCity(),
                accountHolderDTO.getZipCode(),
                accountHolderDTO.getStreet()
        );
        AccountHolder accountHolder = new AccountHolder(
                accountHolderDTO.getFirstName(), accountHolderDTO.getLastName(), accountHolderDTO.getEmail(),  accountHolderDTO.getPassword(),
                dateFromDTO, addressFromDTO, null);

      return accountHolder;
    }

}
