package com.ironhack.pauescolabank.model.Users;

import com.ironhack.pauescolabank.DTO.AdminDTO;
import com.ironhack.pauescolabank.enums.AuthorisationLevel;
import com.ironhack.pauescolabank.model.HistoryLog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import java.util.List;

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
        admin.setName(adminDTO.getName());
        admin.setPassword(adminDTO.getPassword());
        admin.setLogHistory(adminDTO.getLogHistory());
        admin.setPendingOrders(adminDTO.getPendingOrders());

        return admin;
    }

}
