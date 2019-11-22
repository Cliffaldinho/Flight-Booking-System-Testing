// Controller to log a user out by invalidating the session

package seng3150.group1.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/logout")
public class LogoutController {
    @GetMapping

    public ModelAndView index(HttpSession session) {
        //invalidates the session, removing the stored user and any other attributes
        session.invalidate();
        return new ModelAndView("redirect:/");
    }
}