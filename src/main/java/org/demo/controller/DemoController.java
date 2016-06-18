package org.demo.controller;

import org.demo.dto.UserDto;
import org.demo.entity.MyUser;
import org.demo.entity.UserRole;
import org.demo.service.MyUserService;

import org.demo.service.MyUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;

@Controller
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

    @RequestMapping(path = "/hi")
    public ModelAndView helloPublic() {
        System.out.println("****************************** helloPublic");
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Hello");
        model.addObject("message", "Public content");
        model.setViewName("hello");
        return model;
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

    @RequestMapping(path = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/hi";
    }

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
        System.out.println("roler = " + userRoleName);
        System.out.println(passwordEncoder.encode(password));

        HashSet<UserRole> userRoles = new HashSet<>(1);
        userRoles.add(myUserRoleService.getUserRoleByName(userRoleName));
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);

        myUserService.CreateUser(userDto);

        UserDetails userDetails = auth.getDefaultUserDetailsService().loadUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);


        return "redirect:/secured?";
    }


}