package seng3150.group1.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/account/edit-profile")
public class EditProfileController {

    /**
     * Return the editProfile.jsp view, containing the form for users to edit their personal details
     * @param resultMessage - optionally passed through by redirect from UpdateProfileController if there
     *                      was invalid data input into this the editProfile form. A feedback message to
     *                      the user explaining what mistake was made.
     * @return - view with form to update the logged-in user's personal information
     */
    @GetMapping
    public ModelAndView index(@ModelAttribute("resultMessage") String resultMessage)
    {
        return new ModelAndView("editProfile","resultMessage",resultMessage);
    }
}