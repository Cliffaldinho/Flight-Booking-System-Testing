package seng3150.group1.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login-page")
public class LoginPageController {

    @GetMapping
    public ModelAndView index(HttpSession session) {
        return new ModelAndView("notLoggedIn");
    }
}