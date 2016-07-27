package org.demo.controller;

import org.demo.dto.UserDto;
import org.demo.entity.security.MyUser;
import org.demo.service.MyUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
@RequestMapping (value = "/user")
public class UserController {
    @Autowired
    private MyUserService userService;

    @Autowired
    private ModelMapper modelMapper;

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

    @RequestMapping (value = "/{username}", method = RequestMethod.GET)
    @org.springframework.web.bind.annotation.ResponseStatus (HttpStatus.OK)
    @ResponseBody
    public UserDto getUser (@PathVariable("username") String userName) {
        System.out.println ("******************* get user");
        return convertToDto(userService.getByUserName (userName));
    }

    private UserDto convertToDto(MyUser user) {
        return modelMapper.map(user, UserDto.class);
    }

}
