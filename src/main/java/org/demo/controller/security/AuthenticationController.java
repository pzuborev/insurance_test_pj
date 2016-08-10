package org.demo.controller.security;

import org.demo.service.security.MyAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(value = "*")
public class AuthenticationController {
    @Autowired
    private MyAuthenticationService authenticationService;

    @RequestMapping(value = "/logout")
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity logout(HttpServletRequest request) {
        System.out.println("*** logout controller");
        String token = request.getHeader("token");
        System.out.println("token = " + token);

        authenticationService.logout(token);
        return new ResponseEntity(HttpStatus.OK);
    }
}
