package com.ironhack.pauescolabank.services.security;


import com.ironhack.pauescolabank.config.KeycloakProvider;
import com.ironhack.pauescolabank.embedded.Address;
import com.ironhack.pauescolabank.requests.CreateAccountHolderRequest;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import com.ironhack.pauescolabank.services.AccountHolderService;
import lombok.extern.java.Log;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;


@Service
@Log
public class KeycloakAdminClientService {
    private final KeycloakProvider kcProvider;

    private final AccountHolderService accountHolderService;


    @Value("${keycloak.realm}")
    public String realm;
    @Value(("${keycloak.resource}"))
    public String clientId;


    public KeycloakAdminClientService(KeycloakProvider keycloakProvider, AccountHolderService accountHolderService) {
        this.kcProvider = keycloakProvider;
        this.accountHolderService = accountHolderService;
    }

    private static CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

    public Response createKeycloakUser(CreateAccountHolderRequest user) {
        var adminKeycloak = kcProvider.getInstance();
        UsersResource usersResource = kcProvider.getInstance().realm(realm).users();
        CredentialRepresentation credentialRepresentation = createPasswordCredentials(user.getPassword());

        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(user.getEmail());
        kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
        kcUser.setFirstName(user.getFirstName());
        kcUser.setLastName(user.getLastName());
        kcUser.setEmail(user.getEmail());
        kcUser.setEnabled(true);
        kcUser.setEmailVerified(false);

//        Change this to change the group logic
        kcUser.setGroups(List.of("members"));


        Response response = usersResource.create(kcUser);

        if (response.getStatus() == 201) {
            List<UserRepresentation> userList = adminKeycloak.realm(realm).users().search(kcUser.getUsername()).stream()
                    .filter(userRep -> userRep.getUsername().equals(kcUser.getUsername())).toList();
            var createdUser = userList.get(0);
            log.info("User with id: " + createdUser.getId() + " created");

//            TODO you may add you logic to store and connect the keycloak user to the local user here

            var accountHolder = new AccountHolder();
            var addressToAdd = new Address(user.getCountry(), user.getCity(), user.getZipCode(),user.getStreet());
            accountHolder.setFirstName(user.getFirstName());
            accountHolder.setLastName(user.getLastName());
            accountHolder.setAddress(addressToAdd);
            accountHolder.setKeycloakId(createdUser.getId());

            var createdAccountHolder = accountHolderService.save(accountHolder); // simulate the call to store the ah to the db
            log.info("Account holder: " + createdAccountHolder + " has been created!");
        }

        return response;

    }


}