package workintech;

import workintech.enums.Status;
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
            book.setStatus(Status.BORROWED);
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
    public void searchByAuthor(String name, LibraryDatabase database) {
        Map<Integer, Book> books = database.getBooks();
        boolean found = false;

        System.out.println(String.format("Books by %s: ", name));
        for (Book book : books.values()) {
            Author author = book.getAuthor();
            if (author != null && author.getName().equalsIgnoreCase(name)) {
                System.out.println("+ " + book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found with author: " + name);
        }
    }
    @Override
    public void searchByTitle(String title, LibraryDatabase database) {
        Map<Integer, Book> books = database.getBooks();
        boolean found = false;
        System.out.println("Search Results for Title: " + "'" + title+ "'");
        for (Book book : books.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found with title: " + title);
        }
    }
    @Override
    public void addBook(Book book, LibraryDatabase database) {
        int id = book.getId();
        database.getBooks().put(id, book);
        Author author = book.getAuthor();
        if (author != null) {
            author.getBooks().add(book);
        }
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
