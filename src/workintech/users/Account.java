package workintech.users;

import java.util.List;

public class Account {
    private List borrowedBooks; //I used List to protect the order.
    private int maxBookLimit;
    private double debt;

    //getter and setters
    public List getBorrowedBooks() {
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
    public void setBorrowedBooks(List borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

}
