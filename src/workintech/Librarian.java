package workintech;

import workintech.enums.TransactionType;
import workintech.interfaces.Managable;
import workintech.interfaces.Searchable;
import workintech.users.Member;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Librarian implements Searchable, Managable {
    private int id;
    private String name;
    private Set<Transaction> transactionSet; //I used Set to be sure not to issue same book twice.

    public Librarian(int id, String name) {
        this.id = id;
        this.name = name;
        transactionSet = new HashSet<>();
    }
    public boolean issueBook(Book book, Member member, LibraryDatabase database){
        Transaction transaction = new Transaction();
        LocalDate dateOfIssue = LocalDate.now();
        LocalDate dueDate = dateOfIssue.plusWeeks(2);
        boolean transactionCreated = transaction.createTransaction(member.getId(), book.getId(), dueDate, dateOfIssue, database);
        if (transactionCreated) {
            transactionSet.add(transaction);
            System.out.println("Success! Transaction created.");
            return true;
        } else {
            return false;
        }
    }
    public void calculateFine(){

    }
    public void createBill(){

    }
    public void verifyMember(){

    }

    @Override
    public void searchByAuthor() {
        
    }
    @Override
    public void searchByTitle(Map<Integer, Book> books) {

    }
    @Override
    public void addBook(Book book, LibraryDatabase database) {
        int id = book.getId();
        database.getBooks().put(id, book);
    }
    @Override
    public void deleteBookById(int id) {

    }
    @Override
    public void deleteBookByTitle(String title) {

    }
    @Override
    public void addMember(Member member, LibraryDatabase database) {
        int id = member.getId();
        database.getMembers().put(id, member);
    }
}
