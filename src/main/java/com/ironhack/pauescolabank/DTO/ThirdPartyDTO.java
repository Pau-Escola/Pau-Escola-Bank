package com.ironhack.pauescolabank.DTO;

import com.ironhack.pauescolabank.model.HistoryLog;
import com.ironhack.pauescolabank.model.Order;
import com.ironhack.pauescolabank.model.Users.ThirdParty;
import com.ironhack.pauescolabank.utilities.ClientAccountJsonFormatter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ThirdPartyDTO {

    private String name;
    private String password;
    private String clientAccountJson;

    public ThirdPartyDTO fromEntity(ThirdParty thirdParty){
        ClientAccountJsonFormatter clientAccountJsonFormatter = new ClientAccountJsonFormatter();
        ThirdPartyDTO thirdPartyDTO = new ThirdPartyDTO();
        thirdPartyDTO.setName(thirdParty.getName());
        thirdPartyDTO.setPassword(thirdParty.getPassword());
        thirdPartyDTO.setClientAccountJson(clientAccountJsonFormatter.toDTO(thirdParty.getClientAccountJson()));

        return thirdPartyDTO;
    }
}
