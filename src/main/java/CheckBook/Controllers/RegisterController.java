package CheckBook.Controllers;

import CheckBook.DataAccess.DAO.UserDAO;
import CheckBook.DataAccess.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    private String appMode;

    @Autowired
    public RegisterController(Environment environment) {
        appMode = environment.getProperty("app-mode");
    }

    @GetMapping("newaccount")
    public String newAccount(Model model) {
        model.addAttribute("newUser", new User());

        return "newaccount";
    }

    @PostMapping("register")
    public String register(@ModelAttribute User newUser, Model model) {
        UserDAO dao = new UserDAO();
        dao.createUser(newUser);

        return "redirect:/success";
    }
}
