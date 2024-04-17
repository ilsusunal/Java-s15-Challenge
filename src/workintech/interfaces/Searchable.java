package workintech.interfaces;

import workintech.datas.LibraryDatabase;

public interface Searchable {
    void searchByAuthor(String name, LibraryDatabase database);
    void searchByTitle(String title, LibraryDatabase database);

}
