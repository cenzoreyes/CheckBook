package CheckBook.Controllers;

import CheckBook.DataAccess.DAO.ExpenseDAO;
import CheckBook.DataAccess.Models.Expense;
import CheckBook.DataAccess.Models.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class ViewExpensesController {
    private String appMode;

    @Autowired
    public ViewExpensesController(Environment environment) {
        appMode = environment.getProperty("app-mode");
    }

    @RequestMapping("viewexpenses")
    public String viewexpenses(Model model, HttpServletRequest request) {

        if (request.getSession().getAttribute("sessionUser") != null) {
            model.addAttribute("dateTime", new Date());
            model.addAttribute("username", "CheckBook");
            model.addAttribute("mode", appMode);

            ExpenseDAO dao = new ExpenseDAO();
            SessionUser user = (SessionUser) request.getSession().getAttribute("sessionUser");

            ArrayList<Expense> expenses = dao.getExpenses(user.getUserID());

            model.addAttribute("expenseList", expenses);
//        model.addAttribute("expense", new Expense());

            return "viewexpenses";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping("deleteexpense/{id}")
    public String deleteExpense(@PathVariable String id, Model model, HttpServletRequest request) {

        if (request.getSession().getAttribute("sessionUser") != null) {
            ExpenseDAO dao = new ExpenseDAO();
            dao.deleteExpense(Integer.parseInt(id));

            return "redirect:/viewexpenses";
        } else {
            return "redirect:/login";
        }
    }

}