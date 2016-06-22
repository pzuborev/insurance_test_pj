package org.demo.controller;

import org.demo.controller.response.RestResponseData;
import org.demo.controller.response.Status;
import org.demo.dto.TokenDto;
import org.demo.dto.UserDto;
import org.demo.service.MyUserService;
import org.demo.service.RestAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/token")
public class TokenController {
    @Autowired
    private RestAuthService restAuthService;

    @Autowired
    private MyUserService userService;

    private RestResponseData<TokenDto> tokenAsResponse(UserDto userdto) {
        return new RestResponseData<TokenDto>(
                Status.Success(),
                restAuthService.getToken(userdto));
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public RestResponseData<TokenDto> token(@PathVariable("username") String username, @RequestParam String password) {
        UserDto userdto = new UserDto();
        userdto.setUsername(username);
        userdto.setPassword(password);

        return tokenAsResponse(userdto);
    }

    @RequestMapping(method = RequestMethod.POST)
    public RestResponseData<TokenDto> token(@RequestBody UserDto userdto) {
        System.out.println("********************** TokenController.token");
        return tokenAsResponse(userdto);
    }




}
