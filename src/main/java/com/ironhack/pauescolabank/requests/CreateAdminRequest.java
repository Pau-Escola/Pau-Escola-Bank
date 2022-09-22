package com.ironhack.pauescolabank.requests;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAdminRequest {
    String firstName;
    String lastName;
    String email;
    String password;

}
