package workintech;

import java.util.Objects;

public class Author {
    private int id;
    private String firstName;
    private String lasName;
    private String biography;

    //constructor
    public Author(int id, String firstName, String lasName, String biography) {
        this.id = id;
        this.firstName = firstName;
        this.lasName = lasName;
        this.biography = biography;
    }

    @Override
    public String toString() {
        return firstName + " " + lasName ;
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
