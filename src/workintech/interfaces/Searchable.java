package workintech.interfaces;

import workintech.Book;
import workintech.LibraryDatabase;

import java.util.Map;

public interface Searchable {
    void searchByAuthor(String name, LibraryDatabase database);
    void searchByTitle(String title, LibraryDatabase database);

}
