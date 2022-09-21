package com.ironhack.pauescolabank.DTO;

import com.ironhack.pauescolabank.model.HistoryLog;
import com.ironhack.pauescolabank.model.Order;
import com.ironhack.pauescolabank.model.Users.Admin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class AdminDTO {

    private String name;
    private String password;
    private List<HistoryLog> logHistory;
    private List<Order> pendingOrders;

    public AdminDTO fromEntity(Admin admin){
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setName(admin.getFirstName());
        adminDTO.setPassword(admin.getPassword());
        adminDTO.setLogHistory(admin.getLogHistory());
        adminDTO.setPendingOrders(admin.getPendingOrders());

        return adminDTO;
    }
}
