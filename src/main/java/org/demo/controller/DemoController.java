package org.demo.controller;

import org.apache.log4j.Logger;
import org.demo.dto.UserDto;
import org.demo.entity.security.MyUser;
import org.demo.entity.security.MyUserRole;
import org.demo.service.security.MyUserRoleService;
import org.demo.service.security.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    final static Logger logger = Logger.getLogger(DemoController.class);

    @PostConstruct
    public void init() {
        logger.debug("*** init DemoController");
    }

    @RequestMapping(path = "/api/hi")
    public ModelAndView helloPublic() {
        logger.debug("*** helloPublic");
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

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        logger.debug("*** register");
        return "register";
    }


    @RequestMapping(path = "/createuser", method = RequestMethod.POST)
    public String createuser(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String userRoleName,
                             HttpServletRequest request, HttpServletResponse response) {

        logger.debug("****************** createuser");
        logger.debug("username = " + username);
        logger.debug("password = " + password);
        logger.debug("role = " + userRoleName);
        logger.debug(passwordEncoder.encode(password));

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