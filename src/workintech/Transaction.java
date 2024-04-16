package workintech;

import workintech.enums.TransactionType;
import workintech.users.Member;

import java.time.LocalDate;

public class Transaction {
    private int memberId;
    private int bookId;
    private LocalDate dueDate;
    private LocalDate dateOfIssue;

    public Transaction() {
    }
    public Transaction(int memberId, int bookId, LocalDate dueDate, LocalDate dateOfIssue) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.dueDate = dueDate;
        this.dateOfIssue = dateOfIssue;
    }

    public int getBookId() {
        return bookId;
    }

    public boolean createTransaction(int memberId, int bookId, LocalDate dueDate, LocalDate dateOfIssue, LibraryDatabase database){
        if(!validateMember(memberId, database) || !validateBook(bookId, database)){
            System.out.println("Error! Member/book cannot be found.");
            return false;
        }
        this.memberId = memberId;
        this.bookId = bookId;
        this.dueDate = dueDate;
        this.dateOfIssue = dateOfIssue;
        return true;
    }
    private boolean validateBook(int bookId, LibraryDatabase database) { return database.getBooks().containsKey(bookId);}
    private boolean validateMember(int memberId, LibraryDatabase database) { return database.getMembers().containsKey(memberId);}

    public void deleteTransaction(){

    }
    public void retrieveTransaction(){

    }

}
