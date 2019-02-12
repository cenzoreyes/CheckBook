package CheckBook.Controllers;

import CheckBook.DataAccess.DAO.UserDAO;
import CheckBook.DataAccess.Models.SessionUser;
import CheckBook.comands.LoginCommand;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller

public class LoginController {

    public LoginController() {

    }

    /**
     * This method shows the login form page
     */
    @RequestMapping("/login")
    public String showLoginForm(Model model) {

        //creates new empty login command and attaches to the model
        model.addAttribute("LoginCommand", new LoginCommand());
        return "login";
    }


    /**
     * does the login and if there are errors shows them
     */
    @RequestMapping(value = "doLogin", method = RequestMethod.POST)
    public String doLogin(@Valid LoginCommand loginCommand, @ModelAttribute("sessionUser") SessionUser user, Model model, HttpSession session) {

        UserDAO dao = new UserDAO();
        user = dao.checkLogin(loginCommand.getUsername(), loginCommand.getPassword());


        if (user != null) {

//            SessionUser su = new SessionUser(user.getUserID(), user.getFirstName(), user.getLastName());
            session.setAttribute("sessionUser", new SessionUser(user.getUserID(), user.getFirstName(), user.getLastName()));
            session.setAttribute("name", user.getFirstName() + " " + user.getLastName());

            return "redirect:/home";
        } else {

            //username or pw blank/invalid credentials
            if (loginCommand.getPassword().length() == 0 || loginCommand.getUsername().length() == 0) {
                model.addAttribute("myError", "Username and password cannot be blank");
            } else {
                model.addAttribute("myError", "Invalid username or password");
            }

            return "forward:/login";
        }
    }

}
