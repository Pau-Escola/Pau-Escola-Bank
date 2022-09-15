package com.ironhack.pauescolabank.model.Users;

import com.ironhack.pauescolabank.DTO.AccountHolderDTO;
import com.ironhack.pauescolabank.model.Account;
import com.ironhack.pauescolabank.embedded.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
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

    public AccountHolder(String name, String password, LocalDate birthdate, Address address, String email, List<Account> accounts) {
        super(name, password);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        this.birthdate = birthdate;
        this.address = address;
        this.email = email;
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
                accountHolderDTO.getName(), accountHolderDTO.getPassword(),
                dateFromDTO, addressFromDTO, accountHolderDTO.getEmail(), null);

      return accountHolder;
    }

}
