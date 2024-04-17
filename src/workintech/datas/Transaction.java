package workintech.datas;

import workintech.datas.Book;
import workintech.datas.LibraryDatabase;
import workintech.enums.Status;
import workintech.users.Member;

import java.time.LocalDate;

public class Transaction {
    private int memberId;
    private int bookId;
    private LocalDate dueDate;
    private LocalDate dateOfIssue;


    public int getBookId() {return bookId;}
    public LocalDate getDueDate() {return dueDate;}
    public LocalDate getDateOfIssue() {return dateOfIssue;}
    public int getMemberId() {return memberId;}

    public boolean createTransaction(int memberId, int bookId, LocalDate dueDate, LocalDate dateOfIssue, LibraryDatabase database){
        if(!validateMember(memberId, database) || !validateBook(bookId, database)){
            System.out.println("Error! Member/book cannot be found.");
            return false;
        }
        if(!validateLimit(memberId, database)){
            System.out.println("Error! You can not borrow more than 5 books.");
            return false;
        }
        Book book = database.getBookById(bookId);
        if (book.getStatus() == Status.AVAILABLE) {
            this.memberId = memberId;
            this.bookId = bookId;
            this.dueDate = dueDate;
            this.dateOfIssue = dateOfIssue;
            return true;
        } else {
            System.out.println("Error! Book is already borrowed.");
            return false;
        }
    }
    private boolean validateBook(int bookId, LibraryDatabase database) { return database.getBooks().containsKey(bookId);}
    private boolean validateMember(int memberId, LibraryDatabase database) { return database.getMembers().containsKey(memberId);}
    private boolean validateLimit(int memberId, LibraryDatabase database) {
        Member member = database.getMemberById(memberId);
        return member != null && member.getAccount().getBorrowedBooks().size() < 5;
    }


}
