package com.ironhack.pauescolabank.model.Users;

import com.ironhack.pauescolabank.model.Users.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.HashMap;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ThirdParty extends User {
    //todo aquest es un altre json que s'haura de passar a hashmap, tindrá el num de compte extern i el client a qui va associat
    private String clientAccountJson;
}
