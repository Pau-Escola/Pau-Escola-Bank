package com.ironhack.pauescolabank.controllers.security;



import com.ironhack.pauescolabank.config.KeycloakProvider;
import com.ironhack.pauescolabank.requests.CreateAccountHolderRequest;
import com.ironhack.pauescolabank.requests.CreateAdminRequest;
import com.ironhack.pauescolabank.requests.CreateThirdpartyRequest;
import com.ironhack.pauescolabank.requests.LoginRequest;
import com.ironhack.pauescolabank.services.AccountHolderService;
import com.ironhack.pauescolabank.services.AdminService;
import com.ironhack.pauescolabank.services.ThirdPartyService;
import com.ironhack.pauescolabank.services.security.KeycloakAdminClientService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/users-management")
public class UserController {
    private final KeycloakAdminClientService kcAdminClient;

    private final KeycloakProvider kcProvider;
    private final AdminService adminService;
    private final AccountHolderService accountHolderService;
    private final ThirdPartyService thirdPartyService;


    public UserController(KeycloakAdminClientService kcAdminClient,
                          KeycloakProvider kcProvider,
                          AdminService adminService,
                          AccountHolderService accountHolderService,
                          ThirdPartyService thirdPartyService) {
        this.kcProvider = kcProvider;
        this.kcAdminClient = kcAdminClient;
        this.adminService = adminService;
        this.accountHolderService = accountHolderService;
        this.thirdPartyService = thirdPartyService;
    }


    @RolesAllowed({"admin", "moderator"})
    @PostMapping(value = "/create/accountholder")
    public ResponseEntity<?> createAccountHolder(@RequestBody CreateAccountHolderRequest user) {
        Response createdResponse = kcAdminClient.createKeycloakAHUser(user);
        return ResponseEntity.status(createdResponse.getStatus()).build();

    }

    @RolesAllowed("admin")
    @PostMapping(value = "/create/admin")
    public ResponseEntity<?> createAdmin(@RequestBody CreateAdminRequest user) {
        Response createdResponse = kcAdminClient.createKeycloakAdminUser(user);
        return ResponseEntity.status(createdResponse.getStatus()).build();

    }

    @RolesAllowed({"admin"})
    @PostMapping(value = "/create/thirdparty")
    public ResponseEntity<?> createThirdParty(@RequestBody CreateThirdpartyRequest user) {
        Response createdResponse = kcAdminClient.createKeycloakThirdPartyUser(user);
        return ResponseEntity.status(createdResponse.getStatus()).build();

    }

    @DeleteMapping("/delete/admin/{id}")
    public String deleteAdmin(@PathVariable Long id){return adminService.delete(id);}
    @DeleteMapping("/delete/accountholder/{id}")
    public String deleteAH(@PathVariable Long id){
        return accountHolderService.delete(id);
    }
    @DeleteMapping("/delete/thirdparty/{id}")
    public String deleteTP(@PathVariable Long id){
        return thirdPartyService.delete(id);
    }

    @PostMapping("/get-token")
    public ResponseEntity<AccessTokenResponse> login(@NotNull @RequestBody LoginRequest loginRequest) {
        Keycloak keycloak = kcProvider.newKeycloakBuilderWithPasswordCredentials(loginRequest.getUsername(), loginRequest.getPassword()).build();

        AccessTokenResponse accessTokenResponse = null;
        try {
            accessTokenResponse = keycloak.tokenManager().getAccessToken();
            return ResponseEntity.status(HttpStatus.OK).body(accessTokenResponse);
        } catch (BadRequestException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(accessTokenResponse);
        }

    }


}
