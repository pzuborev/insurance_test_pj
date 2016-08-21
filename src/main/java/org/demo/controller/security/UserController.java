package org.demo.controller.security;

import org.apache.log4j.Logger;
import org.demo.dto.UserDto;
import org.demo.entity.security.InsUser;
import org.demo.service.security.InsUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    private InsUserService userService;

    @Autowired
    private ModelMapper modelMapper;
    
    private final static Logger logger = Logger.getLogger(UserController.class);

    @RequestMapping(method = RequestMethod.POST)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<Object> create(@RequestBody UserDto userDto) {
        logger.debug("******************* create");

        userService.create(userDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.PUT)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Object> updateUser(@PathVariable("username") String userName,
                                             @RequestBody UserDto userDto) {
        logger.debug("******************* update");
        userService.update(userName, userDto);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Object> deleteUser(@PathVariable("username") String userName) {
        logger.debug("******************* delete");
        userService.delete(userName);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDto getUser(@PathVariable("username") String userName) {
        logger.debug("******************* get user");
        return convertToDto(userService.getByUserName(userName));
    }
    @RequestMapping(value = "/login")
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDto login(HttpServletRequest request,
                         HttpServletResponse response) {
        logger.debug("******************* login");
        logger.debug(response.getHeader("token"));
        return convertToDto(userService.getByUserName("admin"));
    }

    private UserDto convertToDto(InsUser user) {
        return modelMapper.map(user, UserDto.class);
    }

}
