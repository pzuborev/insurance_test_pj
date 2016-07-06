package org.demo.controller;

import org.demo.dto.UserDto;
import org.demo.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (value = "/user")
public class UserController {
    @Autowired
    private MyUserService userService;

    @RequestMapping (method = RequestMethod.POST)
    @org.springframework.web.bind.annotation.ResponseStatus (HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<Object> create (@RequestBody UserDto userDto) {
        System.out.println ("******************* create");

        userService.create (userDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping (value = "/{username}", method = RequestMethod.PUT)
    @org.springframework.web.bind.annotation.ResponseStatus (HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Object> updateUser (@PathVariable("username") String userName,
                                                  @RequestBody UserDto userDto) {
        System.out.println ("******************* update");
        userService.update (userName, userDto);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping (value = "/{username}", method = RequestMethod.DELETE)
    @org.springframework.web.bind.annotation.ResponseStatus (HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Object>  deleteUser (@PathVariable("username") String userName) {
        System.out.println ("******************* delete");
        userService.delete (userName);

        return new ResponseEntity(HttpStatus.OK);
    }


}
