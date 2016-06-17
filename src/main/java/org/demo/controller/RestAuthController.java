package org.demo.controller;

import org.demo.dto.TokenDto;
import org.demo.dto.UserDto;
import org.demo.service.RestAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestAuthController {
    @Autowired
    private RestAuthService restAuthService;

    @RequestMapping(path = "/restlogin1", method = RequestMethod.POST)
    public TokenDto restlogin (@RequestParam String username, @RequestParam String password) {
         UserDto userdto = new UserDto();
         userdto.setUsername(username);
         userdto.setPassword(password);
        return restAuthService.getToken(userdto);
    }

    @RequestMapping(path = "/restlogin2", method = RequestMethod.POST)
    public TokenDto restlogin (@RequestBody UserDto userdto) {
        return restAuthService.getToken(userdto);
    }
}
