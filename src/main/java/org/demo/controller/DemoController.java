package org.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DemoController {
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

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
        if (auth != null){
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
    public String createuser(@RequestParam String username, @RequestParam String password) {
        System.out.println("****************** createuser");

        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println(passwordEncoder.encode(password));
        return "redirect:/secured?";
    }


}