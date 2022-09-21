package com.ironhack.pauescolabank.requests;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountHolderRequest {
    String firstName;
    String lastName;
    String email;
    String password;
    String country;
    String city;
    int zipCode;
    String street;
    int yearOfBirth;
    int monthOfBirth;
    int dayOfBirth;
}
