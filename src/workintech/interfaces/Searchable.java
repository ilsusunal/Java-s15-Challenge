package workintech.interfaces;

import workintech.Book;

import java.util.Map;

public interface Searchable {
    void searchByAuthor();
    void searchByTitle(Map<Integer, Book> books);

}
