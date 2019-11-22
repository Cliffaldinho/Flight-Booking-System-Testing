// Controller to update a user's account information when they submit their new details

package seng3150.group1.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import seng3150.group1.dao.IUserDao;
import seng3150.group1.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/account/update-profile")
public class UpdateProfileController {

    @Autowired
    IUserDao userDao;

    @PostMapping
    public String save(RedirectAttributes redirectAttributes,
                             HttpSession session,
                             @RequestParam String firstname,
                             @RequestParam String middlename,
                             @RequestParam String lastname,
                             @RequestParam String email,
                             @RequestParam String phone,
                             @RequestParam String address)
    {
        //TODO - server side validation

        //grab user from session and set the attributes to the form inputs
        User user = (User) session.getAttribute("user");
        user.setFirstname(firstname);
        user.setMiddlename(middlename);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setPhonenumber(phone);
        user.setAddress(address);

        //update the user in the database using these changes
        userDao.saveAndFlush(user);

        //add result message to redirect, which will be displayed at the top of the edit-profile form
        redirectAttributes.addFlashAttribute("resultMessage", "Profile updated successfully!");

        return "redirect:/account/edit-profile";
    }
}