package com.ironhack.pauescolabank.controllers.security;


import com.ironhack.pauescolabank.services.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;

@RestController
public class HelloController {

    @GetMapping("/public/hello")
    public ResponseEntity<String> helloPublic() {
        return ResponseEntity.ok("Hello public user");
    }

    @Autowired
    AccountHolderService accountHolderService;

    @GetMapping("/members/hello")
    public ResponseEntity<String> helloMember(Principal principal) {
        var accountHolder = accountHolderService.get(principal.getName());
        if (accountHolder == null) {
            return ResponseEntity.ok("Who are you? that's impossible!!!!");
        }
        return ResponseEntity.ok("Hello dear member " + accountHolder.getFirstName() + " , your assigned accountHolder is " + accountHolder);
    }

    @GetMapping("/moderators/hello")
    public ResponseEntity<String> helloModerator() {
        return ResponseEntity.ok("Hello Moderator");
    }


    @GetMapping("/admins/hello")
    public ResponseEntity<String> helloAdmin() {
        return ResponseEntity.ok("Nice day, admin");
    }

    @RolesAllowed("member")
    @GetMapping("/public/hello-fake-public")
    public ResponseEntity<String> helloCustom() {
        return ResponseEntity.ok("Nice day, it appears to be public but not");
    }


}
