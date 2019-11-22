// Takes a user back to the preference sliders after to refine a search's rankings

package seng3150.group1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/flight-sliders")
public class ReturnToSlidersController {
    @PostMapping
    public ModelAndView directToSliders() {
        return new ModelAndView("loadingPreferencesQuestionaire");
    }

}