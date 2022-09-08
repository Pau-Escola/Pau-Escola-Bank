package com.ironhack.pauescolabank.DTO;

import com.ironhack.pauescolabank.embedded.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AccountHolderDTO {

    private String name;
    private String password;
    private String email;
    private String country;
    private String city;
    private Integer zipCode;
    private String street;
}
