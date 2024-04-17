package workintech.interfaces;

import workintech.Book;
import workintech.LibraryDatabase;
import workintech.users.Member;

public interface Managable {
    void addBook(Book book, LibraryDatabase database);
    void deleteBookById(int id, LibraryDatabase database);
    void deleteBookByTitle(String title, LibraryDatabase database);
    void updateBook(Book book, LibraryDatabase database);
    void addMember(Member member, LibraryDatabase database);
    void deleteMember(Member member, LibraryDatabase database);
    //void addAuthor();
}
