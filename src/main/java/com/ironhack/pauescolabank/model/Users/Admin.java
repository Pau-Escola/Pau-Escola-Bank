package com.ironhack.pauescolabank.model.Users;

import com.ironhack.pauescolabank.enums.AuthorisationLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Admin extends User {
    @Enumerated
    private AuthorisationLevel authorisationLevel;
}
