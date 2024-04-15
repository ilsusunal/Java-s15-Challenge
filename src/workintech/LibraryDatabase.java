package workintech;

import workintech.interfaces.Searchable;
import workintech.users.Member;

import java.util.HashMap;
import java.util.Map;

public class LibraryDatabase {
    private Map<Integer, Book> books = new HashMap<>();
    private Map<Integer, Member> members = new HashMap<>();;
    private Map<Integer, Author> authors = new HashMap<>();;

    public Map<Integer, Book> getBooks() { return books; }
    public Map<Integer, Member> getMembers() { return members; }
    public Map<Integer, Author> getAuthors() { return authors; }

    public Book getBookById(int bookId) { return books.get(bookId); }
    public Member getMemberById(int memberId) { return members.get(memberId);}

    public void display(){
        System.out.println("Books in the library:");
        for (Map.Entry<Integer, Book> entry : books.entrySet()) {
            int id = entry.getKey();
            Book book = entry.getValue();
            System.out.println("Id: " + id + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor());
        }
    }



    @Override
    public String toString() {
        return "LibraryDatabase{" +
                "books=" + books +
                ", accounts=" + members +
                ", authors=" + authors +
                '}';
    }
}
