package com.ironhack.pauescolabank.model.Users;

import com.ironhack.pauescolabank.DTO.ThirdPartyDTO;
import com.ironhack.pauescolabank.utilities.ClientAccountJsonFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;


@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ThirdParty extends User {
    //todo aquest es un altre json que haura de passar a hashmap, tindrá el num de compte extern i el client a qui va associat
    private String clientAccountJson;

    public ThirdParty fromDTO(ThirdPartyDTO thirdPartyDTO){
        ClientAccountJsonFormatter clientAccountJsonFormatter = new ClientAccountJsonFormatter();
        ThirdParty thirdParty = new ThirdParty();
        thirdParty.setFirstName(thirdPartyDTO.getName());
        thirdParty.setPassword(thirdPartyDTO.getPassword());
        thirdParty.setClientAccountJson(clientAccountJsonFormatter.toEntity(thirdPartyDTO.getClientAccountJson()));

        return thirdParty;
    }
}
