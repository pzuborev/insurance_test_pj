package org.demo.controller;

import org.demo.dto.TokenDto;
import org.demo.dto.UserDto;
import org.demo.exceptions.TokenException;
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

    private RestResponse<TokenDto> tokenAsResponse(UserDto userdto) {
        try {
            return new RestResponse<TokenDto>(
                    Status.Success(),
                    restAuthService.getToken(userdto));
        } catch (TokenException e) {
            return new RestResponse<TokenDto>(Status.Failure(e));
        }
    }

    @RequestMapping(path = "/token", method = RequestMethod.GET)
    public RestResponse<TokenDto> token(@RequestParam String username, @RequestParam String password) {
        UserDto userdto = new UserDto();
        userdto.setUsername(username);
        userdto.setPassword(password);

        return tokenAsResponse(userdto);
    }

    @RequestMapping(path = "/token/get", method = RequestMethod.POST)
    public RestResponse<TokenDto> token(@RequestBody UserDto userdto)  {
        return tokenAsResponse(userdto);
    }

    @RequestMapping(path = "/user/create", method = RequestMethod.POST)
    public RestResponse<Empty> createUser (@RequestBody UserDto userDto) {
        try {
            System.out.println("******************* createUser");
            System.out.println("roles count = " + userDto.getRoles().size());

            userService.CreateUser(userDto);
        } catch (Exception e) {
            return new RestResponse(Status.Failure(e));
        }
        return  new RestResponse(Status.Success());
    }




}
