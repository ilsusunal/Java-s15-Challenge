package workintech;

import workintech.enums.Status;
import workintech.enums.TransactionType;
import workintech.interfaces.Managable;
import workintech.interfaces.Searchable;
import workintech.users.Member;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

    //Creating a transaction
    public boolean issueBook(Book book, Member member, LibraryDatabase database){
        Transaction transaction = new Transaction();
        LocalDate dateOfIssue = LocalDate.now();
        LocalDate dueDate = dateOfIssue.plusWeeks(2);
        boolean transactionCreated = transaction.createTransaction(member.getId(), book.getId(), dueDate, dateOfIssue, database);
        if (transactionCreated) {
            transactionSet.add(transaction); //transaction added to the set
            book.setStatus(Status.BORROWED); //book status is updated
            book.changeOwner(member); //book owner updated
            member.getAccount().setBorrowedBooks(book); //book added to the account
            double currentDebt = member.getAccount().getDebt();
            double additionDebt = book.getPrice();
            member.getAccount().setDebt(currentDebt +  additionDebt); //book debt added to the account
            System.out.println("Success! Transaction created. Details below:");
            System.out.println("-Book owner: " + member.getName());
            System.out.println("-Book price: " + book.getPrice());
            System.out.println("-Date of Issue: " + dateOfIssue);
            System.out.println("-Due Date: " + dueDate);
            System.out.println("-Payment Amount: " + " book price " + book.getPrice() + " added to debt before, total:  " + member.getAccount().getDebt());
            System.out.println("CHECK" + member.getAccount().getBorrowedBooks());
            return true;
        } else {
            return false;
        }
    }
    public void calculateFine(LibraryDatabase database) {
        LocalDate today = LocalDate.now();
        double finePerDay = 0.50; // Fine amount per day (book - fine*day)

        for (Transaction transaction : transactionSet) {
            if (transaction.getDueDate().isBefore(today)) {
                long overdueDays = ChronoUnit.DAYS.between(transaction.getDueDate(), today);
                double fineAmount = finePerDay * overdueDays;

                // Calculating the fine of member
                Member member = database.getMemberById(transaction.getMemberId());
                if (member != null) {
                    member.getAccount().setDebt(member.getAccount().getDebt() + fineAmount);
                }
            }
        }
    }
    public boolean returnBook(Book book, Member member, LibraryDatabase database) {
        for (Transaction transaction : transactionSet) {
            if (transaction.getBookId() == book.getId() && transaction.getMemberId() == member.getId()) {
                // Found the transaction for the returned book and member
                if (book.getStatus() == Status.BORROWED && member.getAccount().getBorrowedBooks().contains(book)) {
                    book.setStatus(Status.AVAILABLE);
                    member.getAccount().getBorrowedBooks().remove(book);
                    // Calculate fines if book is returned late
                    calculateFine(database);
                    transactionSet.remove(transaction); // Remove the transaction from the set
                    System.out.println("- Payment Amount: " + book.getPrice());
                    double debtLeft = member.getAccount().getDebt() - book.getPrice();
                    member.getAccount().setDebt(debtLeft);
                    System.out.println("- Debt Left : " + debtLeft);
                    System.out.println("CHECK" + member.getAccount().getBorrowedBooks());
                    return true;
                } else {
                    return false; // Book not borrowed by the member
                }
            }
        }
        return false; // Transaction not found
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
                System.out.println(book + " Situation " + book.getStatus());
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
        String authorName = book.getAuthor().getName();
        database.getBooks().put(id, book);
        database.getAuthors().put(authorName, book.getAuthor());
        Author author = book.getAuthor();
        if (author != null) {
            author.getBooks().add(book);
        }
    }
    @Override
    public void deleteBookById(int id, LibraryDatabase database) {
        Book bookToRemove = null;
        for (Book book : database.getBooks().values()) {
            if (book.getId() == id) {
                bookToRemove = book;
                break;
            }
        }
        if (bookToRemove != null) {
            database.getBooks().remove(bookToRemove.getId());
            Author author = bookToRemove.getAuthor();
            if (author != null) {
                author.getBooks().remove(bookToRemove);
            }
            System.out.println("Book with ID " + id + " has been deleted.");
        } else {
            System.out.println("Book with ID " + id + " not found.");
        }
    }
    @Override
    public void deleteBookByTitle(String title, LibraryDatabase database) {
        Book bookToRemove = null;
        for (Book book : database.getBooks().values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                bookToRemove = book;
                break;
            }
        }
        if (bookToRemove != null) {
            database.getBooks().remove(bookToRemove.getId());
            Author author = bookToRemove.getAuthor();
            if (author != null) {
                author.getBooks().remove(bookToRemove);
            }
            System.out.println("Book with title '" + title + "' has been deleted.");
        } else {
            System.out.println("Book with title '" + title + "' not found.");
        }
    }

    @Override
    public void updateBook(Book book, LibraryDatabase database) {
        int bookId = book.getId();
        if (database.getBooks().containsKey(bookId)) {
            Book existingBook = database.getBooks().get(bookId);
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setPrice(book.getPrice());
            existingBook.setStatus(book.getStatus());
            System.out.println("Book with ID " + bookId + " has been updated.");
        } else {
            System.out.println("Book with ID " + bookId + " not found.");
        }
    }

    @Override
    public void addMember(Member member, LibraryDatabase database) {
        int id = member.getId();
        database.getMembers().put(id, member);
    }

    @Override
    public void deleteMember(Member member, LibraryDatabase database) {
        int memberId = member.getId();
        if (database.getMembers().containsKey(memberId)) {
            database.getMembers().remove(memberId);
            System.out.println("Member with ID " + memberId + " has been deleted.");
        } else {
            System.out.println("Member with ID " + memberId + " not found.");
        }
    }
}
