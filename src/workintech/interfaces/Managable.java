package workintech.interfaces;

import workintech.Book;
import workintech.LibraryDatabase;
import workintech.users.Member;

public interface Managable {
    void addBook(Book book, LibraryDatabase database);
    void deleteBookById(int id);
    void deleteBookByTitle(String title);
    void addMember(Member member, LibraryDatabase database);
}
