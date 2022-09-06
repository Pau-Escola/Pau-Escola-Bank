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
    //todo que faig aqui???
    private HashMap<String,Long> clientList;
}
