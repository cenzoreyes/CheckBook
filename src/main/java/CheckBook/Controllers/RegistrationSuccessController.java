package CheckBook.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistrationSuccessController {
    private String appMode;

    @Autowired
    public RegistrationSuccessController(Environment environment) {
        appMode = environment.getProperty("app-mode");
    }

    @RequestMapping("success")
    public String registrationSuccess(Model model) {

        return "success";

    }
}
