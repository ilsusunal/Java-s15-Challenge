package workintech.users;

import workintech.Transaction;

import java.util.LinkedList;
import java.util.List;

public class Account {
    private List<Transaction> borrowedBooks = new LinkedList<>();; //I used List to protect the order.
    private int maxBookLimit;
    private double debt;

    //getter and setters
    public List<Transaction> getBorrowedBooks() {
        return borrowedBooks;
    }
    public int getMaxBookLimit() {
        return maxBookLimit;
    }
    public double getDebt() {
        return debt;
    }
    public void setDebt(double debt) {
        this.debt = debt;
    }


}
