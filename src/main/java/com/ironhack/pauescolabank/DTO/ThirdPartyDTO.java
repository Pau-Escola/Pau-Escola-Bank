package com.ironhack.pauescolabank.DTO;

import com.ironhack.pauescolabank.model.Users.ThirdParty;
import com.ironhack.pauescolabank.utilities.ClientAccountJsonFormatter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
        thirdPartyDTO.setName(thirdParty.getFirstName());
        thirdPartyDTO.setPassword(thirdParty.getPassword());
        thirdPartyDTO.setClientAccountJson(clientAccountJsonFormatter.toDTO(thirdParty.getClientAccountJson()));

        return thirdPartyDTO;
    }
}
