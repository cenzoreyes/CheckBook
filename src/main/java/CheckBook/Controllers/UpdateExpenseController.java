package CheckBook.Controllers;

import CheckBook.DataAccess.DAO.ExpenseDAO;
import CheckBook.DataAccess.Models.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UpdateExpenseController {
    private String appMode;

    @Autowired
    public UpdateExpenseController(Environment environment) {
        appMode = environment.getProperty("app-mode");
    }

    @PostMapping("modifyexpense/{id}")
    public String modifyExpense(@PathVariable String id, Model model, HttpServletRequest request) {

        if (request.getSession().getAttribute("sessionUser") != null) {
            ExpenseDAO dao = new ExpenseDAO();
            Expense expense = dao.getExpense(Integer.parseInt(id));

            model.addAttribute("expense", expense);

            return "updateexpense";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("updateexpense")
    public String updateExpense(@ModelAttribute Expense expense, Model model, HttpServletRequest request) {

        if (request.getSession().getAttribute("sessionUser") != null) {
            ExpenseDAO dao = new ExpenseDAO();
            dao.modifyExpense(expense);

            return "redirect:/viewexpenses";
        } else {
            return "redirect:/login";
        }
    }
}
