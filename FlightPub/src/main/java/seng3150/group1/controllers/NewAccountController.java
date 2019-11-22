// Controller to register a new user and create the row in the users table in the database

package seng3150.group1.controllers;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import seng3150.group1.dao.IUserDao;
import seng3150.group1.entities.User;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/create-account")
public class NewAccountController {

    @Autowired
    IUserDao userDao;

    @PostMapping
    public ModelAndView index(HttpSession session,
                              @RequestParam String username,
                              @RequestParam String password,
                              @RequestParam String confirm,
                              @RequestParam String firstname,
                              @RequestParam String middlename,
                              @RequestParam String lastname,
                              @RequestParam String dob,
                              @RequestParam String email,
                              @RequestParam String phone,
                              @RequestParam String address
                              )
    {
        if(userDao.findByUsername(username) != null){
            return new ModelAndView("register", "registerErrorMessage", "Sorry, this username is already taken.");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(DigestUtils.sha256Hex(password));
        user.setFirstname(firstname);
        user.setMiddlename(middlename);
        user.setLastname(lastname);
        user.setDob(dob);
        user.setEmail(email);
        user.setPhonenumber(phone);
        user.setAddress(address);

        //save new user to database
        userDao.saveAndFlush(user);

        //if successful, log them in and go to navigation success page
        session.setAttribute("user", user);
        return new ModelAndView("accountCreationSuccess");

    }
}