package org.demo.controller;

import org.demo.controller.response.EmptyClass;
import org.demo.controller.response.RestResponseData;
import org.demo.controller.response.Status;
import org.demo.controller.utils.RestPreconditions;
import org.demo.dto.UserDto;
import org.demo.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (value = "/user")
public class UserController {
    @Autowired
    private MyUserService userService;

    @RequestMapping (method = RequestMethod.POST)
    @org.springframework.web.bind.annotation.ResponseStatus (HttpStatus.CREATED)
    @ResponseBody
    public RestResponseData<EmptyClass> create (@RequestBody UserDto userDto) {
        System.out.println ("******************* create");

        RestPreconditions.checkNotNull (userDto);
        userService.create (userDto);
        return new RestResponseData(Status.Success ());
    }

    @RequestMapping (value = "/{username}", method = RequestMethod.PUT)
    @org.springframework.web.bind.annotation.ResponseStatus (HttpStatus.OK)
    @ResponseBody
    public RestResponseData<EmptyClass> updateUser (@PathVariable("username") String userName,
                                                    @RequestBody UserDto userDto) {
        System.out.println ("******************* update");

        RestPreconditions.checkNotNull (userDto);

        userService.update (userName, userDto);

        return new RestResponseData(Status.Success ());
    }

}
