package CheckBook.Controllers;

import CheckBook.comands.LoginCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogOutController {

    @RequestMapping("logout")
    public String logout(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("sessionUser") != null) {
            request.getSession().removeAttribute("sessionUser");

            return "logout";
        } else {
            return "redirect:/login";
        }
    }
}
