package workintech;

import workintech.enums.*;
import workintech.users.Account;
import workintech.users.Instructor;
import workintech.users.Member;
import workintech.users.Student;


import java.util.Scanner;

public class TheLibrary {
    private Librarian librarian;
    private LibraryDatabase database;

    public TheLibrary() {
        this.librarian = new Librarian(1, "The Librarian");
        this.database = new LibraryDatabase();
        System.out.println(Message.WELCOME.getMessage());
        initializeBooks();
        initializeMembers();
    }

    private void initializeMembers() {
        Member member1 = new Student(101, "İlsu", "Sunal", new Account(), Faculty.ARCHITECTURE, 1, Degree.MASTER);
        Member member2 = new Student(102, "Selin", "Öztürk", new Account(), Faculty.LAW, 4, Degree.BACHELOR);
        Member member3 = new Student(103, "Mehmet", "Sunal", new Account(), Faculty.SCIENCE, 1, Degree.PHD);
        Member member4 = new Instructor(201, "Doğancan", "Kınık", new Account(), Faculty.ENGINEERING, Role.PROFFESOR);
        librarian.addMember(member1, database);
        librarian.addMember(member2, database);
        librarian.addMember(member3, database);
        librarian.addMember(member4, database);
    }

    public void initializeBooks() {
        Book book1 = new Book(1, new Author(10, "Terry", "Pratchett", "İngiliz fantastik komedi yazarı."), "Fantastik Işık", 100, Status.AVAILABLE);
        Book book2 = new Book(2, new Author(10, "Terry", "Pratchett", "İngiliz fantastik komedi yazarı."), "Hasbüyü", 150, Status.AVAILABLE);
        Book book3 = new Book(3, new Author(20, "Isaac", "Asimov", "Amerikalı bilim kurgu yazarı."), "Vakıf", 200, Status.AVAILABLE);
        Book book4 = new Book(4, new Author(30, "Gabriel Garcia", "Marquez", "Nobel Edebiyat Ödüllü Kolombiyalı yazar, romancı, hikâyeci ve oyun yazarıdır."), "Kırmızı Pazartesi", 400, Status.AVAILABLE);
        Book book5 = new Book(5, new Author(10, "Terry", "Pratchett", "İngiliz fantastik komedi yazarı."), "Mort", 100, Status.AVAILABLE);
        Book book6 = new Book(6, new Author(40, "Kenzaburo", "Oe", " Nobel Edebiyat ödüllü Japon yazar."), "Kişisel Bir Sorun", 100, Status.AVAILABLE);
        Book book7 = new Book(7, new Author(50, "Zygmunt", "Bauman", "Polonyalı sosyolog ve filozoftur."), "Sosyolojik Düşünmek", 200, Status.AVAILABLE);
        Book book8 = new Book(8, new Author(10, "Terry", "Pratchett", "İngiliz fantastik komedi yazarı."), "Muhafızlar! Muhafızlar!", 150, Status.AVAILABLE);
        Book book9 = new Book(9, new Author(20, "Isaac", "Asimov", "Amerikalı bilim kurgu yazarı."), "Ben, Robot", 150, Status.AVAILABLE);
        Book book10 = new Book(10, new Author(60, "Ken", "Yeang", "Malezyalı mimar ve yazar."), "Ekotasarım", 700, Status.AVAILABLE);
        librarian.addBook(book1, database);
        librarian.addBook(book2, database);
        librarian.addBook(book3, database);
        librarian.addBook(book4, database);
        librarian.addBook(book5, database);
        librarian.addBook(book6, database);
        librarian.addBook(book7, database);
        librarian.addBook(book8, database);
        librarian.addBook(book9, database);
        librarian.addBook(book10, database);
    }
    public void whatToDo(){
        System.out.println(Message.TRANSACTION.getMessage());
        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();
        switch (userChoice) {
            case 1:
                // See books
                database.display();
                break;
            case 2:
                // Search book by title
                searchByTitle();
                break;
            case 3:
                // Search author by name
                searchByAuthor();
                break;
            case 4:
                // Borrow book(s)
                issueBook();
                break;
            case 5:
                // Return book(s)
                returnBook();
                break;
            default:
                System.out.println("Invalid choice, try again.");
                break;
        }

    }

    private void returnBook() {
    }

    private void issueBook() {
        System.out.println("Enter the ID of the book you want to borrow:");
        Scanner scanner = new Scanner(System.in);
        int bookId = scanner.nextInt();
        System.out.println("Enter your member ID:");
        int memberId = scanner.nextInt();

        Book book = database.getBookById(bookId);
        Member member = database.getMemberById(memberId);

        if (book != null && member != null) {
            System.out.println(String.format("Dear %s, is this the book you want to borrow? :", member.getFirstName()) + " ' " + book + " '");
            System.out.println("(Enter 'Y' to confirm, any other key to cancel)");
            String confirmation = scanner.next();

            if (confirmation.equalsIgnoreCase("Y")) {
                boolean issued = librarian.issueBook(book, member, database);
                if (issued) {
                    System.out.println("Book issued successfully.");
                } else {
                    System.out.println("Failed to issue book.");
                }
            } else {
                System.out.println("Book issuance canceled.");
            }
        } else {
            System.out.println("Book or member not found.");
        }
    }
    private void searchByTitle(){
        System.out.println("Enter the title of the book you want to search:");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();

        librarian.searchByTitle(title, database);
    }
    private void searchByAuthor(){
        System.out.println("Enter the name of the author you want to search:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        librarian.searchByAuthor(name, database);
    }

}
