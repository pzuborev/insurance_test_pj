//package org.demo.controller;
//
//import org.demo.dto.TokenDto;
//import org.demo.dto.UserDto;
//import org.demo.service.MyUserService;
//import org.demo.service.RestAuthService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping(value = "/token")
//public class TokenController {
//    @Autowired
//    private RestAuthService tokenService;
//
//    @Autowired
//    private MyUserService userService;
//
//    private ResponseEntity<TokenDto> tokenAsResponse(UserDto userdto) {
//        return new ResponseEntity(tokenService.getToken(userdto), HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
//    public ResponseEntity<TokenDto> token(@PathVariable("username") String username, @RequestParam String password) {
//        UserDto userdto = new UserDto();
//        userdto.setUsername(username);
//        userdto.setPassword(password);
//
//        return tokenAsResponse(userdto);
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<TokenDto> token(@RequestBody UserDto userdto) {
//        System.out.println("********************** TokenController.token");
//        return tokenAsResponse(userdto);
//    }
//
//
//
//
//}
