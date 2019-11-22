// Shows the create account page

package seng3150.group1.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("register")
public class RegisterController {
    @GetMapping

    public ModelAndView index(HttpSession session) {
        return new ModelAndView("register");
    }
}