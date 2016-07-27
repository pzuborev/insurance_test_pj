package org.demo.controller;

import org.demo.dto.UserDto;
import org.demo.entity.security.MyUser;
import org.demo.entity.security.MyUserRole;
import org.demo.service.MyUserRoleService;
import org.demo.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;

@RestController
@CrossOrigin(value = "*")
public class DemoController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MyUserService myUserService;

    @Autowired
    private MyUserRoleService myUserRoleService;

    @Autowired
    private AuthenticationManagerBuilder auth;

    @PostConstruct
    public void init() {
        System.out.println("******************************* init DemoController");
    }

    @RequestMapping(path = "/api/hi")
    public ModelAndView helloPublic() {
        System.out.println("****************************** helloPublic");
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Hello");
        model.addObject("message", "Public content");
        model.setViewName("hello");
        return model;
    }
    @RequestMapping(value = "api/get")
    public MyUser get () {
        MyUser myUser = new MyUser();
        myUser.setUsername("test");
        myUser.setPassword("test");
        return myUser;
    }

    @RequestMapping(path = "/secured")
    public ModelAndView helloSecured() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Hello");
        model.addObject("message", "Secured content");
        model.setViewName("secured");
        return model;
    }

    @RequestMapping(path = "/secured/hi")
    public String helloSecured(Model model, HttpServletRequest request,
                               HttpServletResponse response) {
        model.addAttribute("title", "Hello");
        model.addAttribute("message", "Secured content");

        return "secured";
    }

//    @RequestMapping(path = "/logout")
//    public String logout(HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return "redirect:/hi";
//    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        System.out.println("******************* register");
        return "register";
    }


    @RequestMapping(path = "/createuser", method = RequestMethod.POST)
    public String createuser(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String userRoleName,
                             HttpServletRequest request, HttpServletResponse response) {
        System.out.println("****************** createuser");

        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("role = " + userRoleName);
        System.out.println(passwordEncoder.encode(password));

        HashSet<MyUserRole> myUserRoles = new HashSet<>(1);
        myUserRoles.add(myUserRoleService.getUserRoleByName(userRoleName));
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);

        myUserService.create(userDto);

        UserDetails userDetails = auth.getDefaultUserDetailsService().loadUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);


        return "redirect:/secured?";
    }


}