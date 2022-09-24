package com.ironhack.pauescolabank.services.security;


import com.ironhack.pauescolabank.config.KeycloakProvider;
import com.ironhack.pauescolabank.embedded.Address;
import com.ironhack.pauescolabank.model.Users.Admin;
import com.ironhack.pauescolabank.model.Users.ThirdParty;
import com.ironhack.pauescolabank.requests.CreateAccountHolderRequest;
import com.ironhack.pauescolabank.model.Users.AccountHolder;
import com.ironhack.pauescolabank.requests.CreateAdminRequest;
import com.ironhack.pauescolabank.requests.CreateThirdpartyRequest;
import com.ironhack.pauescolabank.services.AccountHolderService;
import com.ironhack.pauescolabank.services.AdminService;
import com.ironhack.pauescolabank.services.ThirdPartyService;
import lombok.extern.java.Log;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;


@Service
@Log
public class KeycloakAdminClientService {
    private final KeycloakProvider kcProvider;

    private final AccountHolderService accountHolderService;
    private final AdminService adminService;
    private final ThirdPartyService thirdPartyService;


    @Value("${keycloak.realm}")
    public String realm;
    @Value(("${keycloak.resource}"))
    public String clientId;


    public KeycloakAdminClientService(KeycloakProvider keycloakProvider,
                                      AccountHolderService accountHolderService,
                                      AdminService adminService,
                                      ThirdPartyService thirdPartyService) {
        this.kcProvider = keycloakProvider;
        this.accountHolderService = accountHolderService;
        this.adminService = adminService;
        this.thirdPartyService = thirdPartyService;
    }

    private static CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

    public Response createKeycloakAHUser(CreateAccountHolderRequest user) {
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


            var addressToAdd = new Address(user.getCountry(), user.getCity(), user.getZipCode(),user.getStreet());

            var dateOfBirth = LocalDate.of(user.getYearOfBirth(),user.getMonthOfBirth(), user.getDayOfBirth());
            var accountHolder = new AccountHolder(user.getFirstName(), user.getLastName(), kcUser.getUsername(), user.getPassword(),dateOfBirth,addressToAdd,null);
            accountHolder.setKeycloakId(createdUser.getId());

            var createdAccountHolder = accountHolderService.save(accountHolder); // simulate the call to store the ah to the db
            log.info("Account holder: " + createdAccountHolder + " has been created!");
        }

        return response;

    }

    public Response createKeycloakAdminUser(CreateAdminRequest user) {
        var adminKeycloak = kcProvider.getInstance();
        UsersResource usersResource = kcProvider.getInstance().realm(realm).users();
        CredentialRepresentation credentialRepresentation = createPasswordCredentials(user.getPassword());

        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(user.getFirstName()+user.getLastName());
        kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
        kcUser.setFirstName(user.getFirstName());
        kcUser.setLastName(user.getLastName());
        kcUser.setEmail(user.getEmail());
        kcUser.setEnabled(true);
        kcUser.setEmailVerified(false);

//        Change this to change the group logic
        kcUser.setGroups(List.of("admin"));


        Response response = usersResource.create(kcUser);

        if (response.getStatus() == 201) {
            List<UserRepresentation> userList = adminKeycloak.realm(realm).users().search(kcUser.getUsername()).stream()
                    .filter(userRep -> userRep.getUsername().equals(kcUser.getUsername())).toList();
            var createdUser = userList.get(0);
            log.info("Admin with id: " + createdUser.getId() + " created");

//            TODO you may add you logic to store and connect the keycloak user to the local user here

            var admin = new Admin();
            admin.setFirstName(user.getFirstName());
            admin.setLastName(user.getLastName());
            admin.setKeycloakId(createdUser.getId());

            var createdAdmin = adminService.save(admin); // simulate the call to store the ah to the db
            log.info("Admin with username:  " + createdAdmin.getFirstName()+createdAdmin.getLastName() + " has been created!");
        }

        return response;

    }

    public Response createKeycloakThirdPartyUser(CreateThirdpartyRequest user) {
        var adminKeycloak = kcProvider.getInstance();
        UsersResource usersResource = kcProvider.getInstance().realm(realm).users();
        CredentialRepresentation credentialRepresentation = createPasswordCredentials(user.getPassword());

        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(user.getFirstName()+user.getLastName());
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
            log.info("Third Party Client with id: " + createdUser.getId() + " created");

//            TODO you may add you logic to store and connect the keycloak user to the local user here

            var thirdParty = new ThirdParty();

            thirdParty.setFirstName(user.getFirstName());
            thirdParty.setLastName(user.getLastName());
            thirdParty.setEmail(user.getEmail());
            thirdParty.setKeycloakId(createdUser.getId());

            var createdThirdParty =thirdPartyService.save(thirdParty); // simulate the call to store the ah to the db
            log.info("Third Party Client: " + createdThirdParty.getFirstName() + " " + createdThirdParty.getLastName() + " has been created!");
        }

        return response;

    }


}