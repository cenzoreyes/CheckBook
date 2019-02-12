package CheckBook.DataAccess.Models;

import java.sql.Date;

public class Expense {


    private int expenseID;
    private String date;
    private String category;
    private String paidTo;
    private double amount;
    private String paymentMethod;

    public Expense() {

    }

    public int getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(int id) {
        this.expenseID = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPaidTo() {
        return paidTo;
    }

    public void setPaidTo(String paidTo) {
        this.paidTo = paidTo;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
