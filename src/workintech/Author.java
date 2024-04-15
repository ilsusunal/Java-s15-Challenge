package workintech;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Author {
    private int id;
    private String firstName;
    private String lastName;
    private String biography;
    private Set<Book> books = new HashSet<>();

    public String getName() {
        return firstName + " " + lastName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public String getBiography() {
        return biography;
    }

    //constructor
    public Author(int id, String firstName, String lasName, String biography) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lasName;
        this.biography = biography;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
