package org.demo.controller;

import org.demo.controller.response.ResponseData;
import org.demo.controller.response.Status;
import org.demo.dto.TokenDto;
import org.demo.dto.UserDto;
import org.demo.service.MyUserService;
import org.demo.service.RestAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestAuthController {
    @Autowired
    private RestAuthService restAuthService;

    @Autowired
    private MyUserService userService;

    private ResponseData<TokenDto> tokenAsResponse(UserDto userdto) {
        return new ResponseData<TokenDto>(
                Status.Success(),
                restAuthService.getToken(userdto));
    }

    @RequestMapping(path = "/token", method = RequestMethod.GET)
    public ResponseData<TokenDto> token(@RequestParam String username, @RequestParam String password) {
        UserDto userdto = new UserDto();
        userdto.setUsername(username);
        userdto.setPassword(password);

        return tokenAsResponse(userdto);
    }

    @RequestMapping(path = "/token/get", method = RequestMethod.POST)
    public ResponseData<TokenDto> token(@RequestBody UserDto userdto) {
        return tokenAsResponse(userdto);
    }




}
