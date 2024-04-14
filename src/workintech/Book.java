package workintech;

import workintech.enums.Status;

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
    public double getPrice() {
        return price;
    }
    public Status getStatus() {
        return status;
    }
    public int getId() { return id; }
    public void setId(int id) {
        this.id = id;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    //methods
    public void display(){
        System.out.println(String.format("You are looking for %s", getTitle()) + "");
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
