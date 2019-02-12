package CheckBook.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class ApplicationController {
    private String appMode;

    @Autowired
    public ApplicationController(Environment environment) {
        appMode = environment.getProperty("app-mode");
    }

    @RequestMapping("/")
    public String index(Model model, HttpSession session) {
        session.setAttribute("id", -1);
        model.addAttribute("dateTime", new Date());
        model.addAttribute("username", "CheckBook");
        model.addAttribute("mode", appMode);
        return "forward:/login";
    }
}
