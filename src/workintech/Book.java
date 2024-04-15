package workintech;

import workintech.enums.Status;
import workintech.users.Member;

import java.util.Objects;

public class Book {
    private int id;
    private Author author;
    private String title;
    private double price;
    private Status status;

    //constructor
    public Book(int id, Author author, String title, double price, Status status) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.price = price;
        this.status = status;
    }

    //getters and setters
    public Author getAuthor() {
        return author;
    }
    public String getTitle() {
        return title;
    }
    public double getPrice() { return price; }
    public Status getStatus() {
        return status;
    }
    public int getId() { return id; }
    public void setStatus(Status status) {
        this.status = status;
    }

    //methods
    public void showOwner(){

    }
    public void changeOwner(){

    }
    public void showDueDate(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
