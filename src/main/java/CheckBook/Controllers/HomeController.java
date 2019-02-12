package CheckBook.Controllers;


import CheckBook.DataAccess.Models.SessionUser;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller

public class HomeController {
    private String appMode;

    @Autowired
    public HomeController(Environment environment) {
        appMode = environment.getProperty("app-mode");
    }

    @GetMapping("home")
    public String home(Model model, HttpServletRequest request) {

        if (request.getSession().getAttribute("sessionUser") != null) {

            SessionUser user = (SessionUser) request.getSession().getAttribute("sessionUser");
            model.addAttribute("dateTime", new Date());
            model.addAttribute("username", "CheckBook");
            model.addAttribute("mode", appMode);
            model.addAttribute("user", user);

            return "index";
        } else {
            return "redirect:/login";
        }
    }
}
