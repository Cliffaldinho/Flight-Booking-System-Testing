// Shows the search page when navigating to the home url
package seng3150.group1.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class IndexController {
    @GetMapping

    public ModelAndView index(HttpSession session) {
        //clear search selections from session if we click new search
        if(session.getAttribute("selectedFlights") != null){
            session.removeAttribute("selectedFlights");
        }
        return new ModelAndView("index");
    }
}