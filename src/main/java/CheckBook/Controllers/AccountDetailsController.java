package CheckBook.Controllers;

import CheckBook.DataAccess.DAO.UserDAO;
import CheckBook.DataAccess.Models.SessionUser;
import CheckBook.DataAccess.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccountDetailsController {
    private String appMode;

    @Autowired
    public AccountDetailsController(Environment environment) {
        appMode = environment.getProperty("app-mode");
    }

    @RequestMapping("accountdetails")
    public String accountDetails(Model model, HttpServletRequest request) {

        if (request.getSession().getAttribute("sessionUser") != null) {
            SessionUser su = (SessionUser) request.getSession().getAttribute("sessionUser");
            UserDAO dao = new UserDAO();
            User u = dao.getUser(su.getUserID());

            model.addAttribute("userdetails", u);

            return "accountdetails";
        } else {
            return "redirect:/login";
        }
    }
}
