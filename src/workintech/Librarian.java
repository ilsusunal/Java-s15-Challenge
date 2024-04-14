package workintech;

import workintech.interfaces.Managable;
import workintech.interfaces.Searchable;
import workintech.users.Member;

import java.util.Map;
import java.util.Scanner;

public class Librarian implements Searchable, Managable {
    private int id;
    private String name;

    public Librarian(int id, String name) {
        this.id = id;
        this.name = name;
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
