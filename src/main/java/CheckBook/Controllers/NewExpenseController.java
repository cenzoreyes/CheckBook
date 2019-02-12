package CheckBook.Controllers;


import CheckBook.DataAccess.DAO.ExpenseDAO;
import CheckBook.DataAccess.Models.Expense;
import CheckBook.DataAccess.Models.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class NewExpenseController {
    private String appMode;

    @Autowired
    public NewExpenseController(Environment environment) {
        appMode = environment.getProperty("app-mode");
    }

    @GetMapping("newexpense")
    public String newexpense(Model model, HttpServletRequest request) {

        if (request.getSession().getAttribute("sessionUser") != null) {
            model.addAttribute("expense", new Expense());

            return "newexpense";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("saveExpense")
    public String saveExpense(@ModelAttribute Expense expense, Model model, HttpServletRequest request) {

        if (request.getSession().getAttribute("sessionUser") != null) {
            ExpenseDAO dao = new ExpenseDAO();
            SessionUser user = (SessionUser) request.getSession().getAttribute("sessionUser");

            dao.saveExpense(expense, user.getUserID());

            return "redirect:viewexpenses";
        } else {
            return "redirect:/login";
        }
    }
}
