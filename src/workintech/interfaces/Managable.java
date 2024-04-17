package workintech.interfaces;

import workintech.datas.Book;
import workintech.datas.LibraryDatabase;
import workintech.users.Member;

public interface Managable {
    void addBook(Book book, LibraryDatabase database);
    void deleteBookById(int id, LibraryDatabase database);
    void updateBook(Book book, LibraryDatabase database);
    void addMember(Member member, LibraryDatabase database);
    void deleteMember(Member member, LibraryDatabase database);

}
