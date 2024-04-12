package workintech;

import workintech.enums.Status;

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

    //methods
    public void display(){
        System.out.println(String.format("You are looking for %s", getTitle()) + "");
    }

}
