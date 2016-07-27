package org.demo.controller.security;

import org.demo.service.security.MyAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
public class AuthenticationController {
    @Autowired
    private MyAuthenticationService authenticationService;

    @RequestMapping(value = "/logout")
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity logout(@RequestParam("token") String token) {
        authenticationService.logout(token);
        return new ResponseEntity(HttpStatus.OK);
    }
}
