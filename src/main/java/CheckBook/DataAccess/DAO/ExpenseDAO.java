package CheckBook.DataAccess.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import CheckBook.DataAccess.Models.Expense;

public class ExpenseDAO {

    private CheckBookDB conn = new CheckBookDB();

    public ArrayList<Expense> getExpenses(int id) {

        String sql = "SELECT * FROM expenses where account_id=(SELECT account_id from accounts where user_id=?) ORDER BY date_created ASC";
        ArrayList<Expense> expenses = new ArrayList<Expense>();

        try {
            PreparedStatement getAll = conn.openConnection().prepareStatement(sql);
            getAll.setInt(1, id);
            ResultSet rs = getAll.executeQuery();


            while (rs.next()) {
                Expense e = new Expense();
                e.setExpenseID(rs.getInt("expense_id"));
                e.setDate(rs.getString("date_created"));
                e.setCategory(rs.getString("category"));
                e.setPaidTo(rs.getString("paid_to"));
                e.setAmount(rs.getDouble("amount"));
                e.setPaymentMethod(rs.getString("payment_method"));

                expenses.add(e);
            }

            conn.closeConnection();
            getAll.close();

        } catch (SQLException ex) {
            conn.closeConnection();
            System.out.println("Error fetching expenses");
        }

        return expenses;
    }

    public Expense getExpense(int id) {

        String sql = "SELECT * from expenses WHERE expense_id=?;";
        Expense e = new Expense();

        try {
            PreparedStatement get = conn.openConnection().prepareStatement(sql);
            get.setInt(1, id);

            ResultSet rs = get.executeQuery();

            if (rs.first()) {
                e.setExpenseID(rs.getInt("expense_id"));
                e.setAmount(rs.getDouble("amount"));
                e.setCategory(rs.getString("category"));
                e.setDate(rs.getString("date_created"));
                e.setPaidTo(rs.getString("paid_to"));
                e.setPaymentMethod(rs.getString("payment_method"));
            }

            conn.closeConnection();
            get.close();

        } catch (SQLException ex) {
            conn.closeConnection();
            System.out.println("Error fetching expense");
        }

        return e;
    }

    public void deleteExpense(int expenseID) {

        String sql = "DELETE FROM expenses WHERE expense_id=?;";

        try {
            PreparedStatement delete = conn.openConnection().prepareStatement(sql);
            delete.setInt(1, expenseID);

            delete.executeUpdate();
            conn.closeConnection();
            delete.close();

        } catch (SQLException ex) {
            conn.closeConnection();
            System.out.println("Error deleting expense ID# " + expenseID);
        }
    }

    public void saveExpense(Expense e, int id) {

        String sql = "INSERT INTO expenses (date_created, category, paid_to, amount, payment_method, account_id) VALUES(?,?,?,?,?,(select accounts.account_id from  checkbook_dev.accounts where user_id=?));";


        try {
            PreparedStatement insert = conn.openConnection().prepareStatement(sql);
            insert.setString(1, e.getDate());
            insert.setString(2, e.getCategory());
            insert.setString(3, e.getPaidTo());
            insert.setDouble(4, e.getAmount());
            insert.setString(5, e.getPaymentMethod());
            insert.setInt(6, id);

            insert.executeUpdate();
            conn.closeConnection();
            insert.close();

        } catch (SQLException ex) {
            conn.closeConnection();
            System.out.println("Error saving new expense");
        }
    }

    public void modifyExpense(Expense e) {

        String sql = "UPDATE expenses SET date_created=?, category=?, paid_to=?, amount=?, payment_method=? where expense_id=?;";

        try {
            PreparedStatement update = conn.openConnection().prepareStatement(sql);
            update.setString(1, e.getDate());
            update.setString(2, e.getCategory());
            update.setString(3, e.getPaidTo());
            update.setDouble(4, e.getAmount());
            update.setString(5, e.getPaymentMethod());
            update.setInt(6, e.getExpenseID());

            update.executeUpdate();
            conn.closeConnection();
            update.close();

        } catch (SQLException ex) {
            conn.closeConnection();
            System.out.println("Error updating expense ID#: " + e.getExpenseID());
        }
    }
}
