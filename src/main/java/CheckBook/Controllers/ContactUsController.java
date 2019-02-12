package CheckBook.Controllers;

import CheckBook.ApplicationLogic.EmailSender;
import CheckBook.DataAccess.Models.MyEmail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContactUsController {

    /**
     * This method shows the login form page
     */
    @RequestMapping("/contactUs")
    public String showContactUsForm(Model model) {

        //creates new empty login command and attaches to the model
        model.addAttribute("MyEmail", new MyEmail());
        return "contactUs";
    }


    @RequestMapping(value = "sendContactUs", method = RequestMethod.POST)
    public String sendContactUs(@ModelAttribute MyEmail myEmail, Model model) {

        EmailSender es = new EmailSender();

        //message sent
        if (es.sendContactUsForm(myEmail) > 0) {

            model.addAttribute("successMessage", "Your feedback was submitted");
        } else {

            model.addAttribute("errorMessage", "Something went wrong during submission");
        }

        return "forward:/contactUs";
    }
}
