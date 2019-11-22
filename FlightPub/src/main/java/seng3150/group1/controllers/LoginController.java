// Takes a login attempt and will log the user in if the credentials are correct or show an error if they are not

package seng3150.group1.controllers;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import seng3150.group1.dao.IUserDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import seng3150.group1.entities.User;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    IUserDao userDao;

    @PostMapping
    public ModelAndView register(HttpSession session, @RequestParam String username, @RequestParam String password) {

        password = DigestUtils.sha256Hex(password);
        User user = userDao.findByUsernameAndPassword(username, password);

        if(user != null){
            session.setAttribute("user", user);
            return new ModelAndView("redirect:/");
        }

        else{
            String errorMessage = "Username/password is incorrect. Please try again.";
            return new ModelAndView("notLoggedIn", "loginErrorMessage", errorMessage);
        }
    }
}
