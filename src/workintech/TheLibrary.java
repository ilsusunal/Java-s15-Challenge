package workintech;

import workintech.enums.Degree;
import workintech.enums.Faculty;
import workintech.enums.Message;
import workintech.enums.Status;
import workintech.users.Account;
import workintech.users.Member;
import workintech.users.Student;

import java.util.Map;
import java.util.Scanner;

public class TheLibrary {
    private Librarian librarian;
    private LibraryDatabase database;
    private Message message;

    public TheLibrary() {
        this.librarian = new Librarian(1, "The Librarian");
        this.database = new LibraryDatabase();
        System.out.println(Message.WELCOME.getMessage());
        initializeBooks();
        initializeMembers();
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    private void initializeMembers() {
        Member member1 = new Student(101, "İlsu", "Sunal", new Account(), Faculty.ARCHITECTURE, 1, Degree.MASTER);
        librarian.addMember(member1, database);
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
                database.display();
                break;
            case 2:
                //
                break;
            case 3:
                //
                break;
            case 4:
                //
                break;
            case 5:
                //
                break;

            default:
                System.out.println("Invalid choice, try again.");
                break;
        }

    }
}
