package com.ironhack.pauescolabank.model.Users;

import com.ironhack.pauescolabank.DTO.AdminDTO;
import com.ironhack.pauescolabank.enums.AuthorisationLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Admin extends User {
    @Enumerated
    private AuthorisationLevel authorisationLevel;

    public Admin fromDTO(AdminDTO adminDTO){
        Admin admin = new Admin();
        admin.setFirstName(adminDTO.getName());
        admin.setPassword(adminDTO.getPassword());
        admin.setLogHistory(adminDTO.getLogHistory());
        admin.setPendingOrders(adminDTO.getPendingOrders());

        return admin;
    }

}
