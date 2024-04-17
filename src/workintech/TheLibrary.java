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
    private UserInterface userInterface;

    public TheLibrary() {
        this.librarian = new Librarian(123, "The Librarian");
        this.database = new LibraryDatabase();
        this.userInterface = new UserInterface();
        System.out.println(Message.WELCOME.getMessage());
        initializeBooks();
        initializeMembers();
    }

    //Members created
    private void initializeMembers() {
        Member member1 = new Student(101, "İlsu", "Sunal", new Account(), Faculty.ARCHITECTURE, "aaa",1, Degree.MASTER);
        Member member2 = new Student(102, "Selin", "Öztürk", new Account(), Faculty.LAW, "bbb",4, Degree.BACHELOR);
        Member member3 = new Student(103, "Mehmet", "Yılmaz", new Account(), Faculty.SCIENCE,"ccc", 1, Degree.PHD);
        Member member4 = new Instructor(201, "Doğancan", "Kınık", new Account(), Faculty.ENGINEERING, "ddd", Role.PROFFESOR);
        Member member5 = new Instructor(202, "İpek", "Sunal", new Account(), Faculty.ART, "fff", Role.ASSISTANT);
        librarian.addMember(member1, database);
        librarian.addMember(member2, database);
        librarian.addMember(member3, database);
        librarian.addMember(member4, database);
        librarian.addMember(member5, database);
    }

    //Books created
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

    //User Interface starts
    public void startLibrary(){
        userInterface.whoAreYou();
        int memberId = userInterface.getId();

        if (memberId != 0) {
            if (userInterface.isLibrarian()) {
                System.out.println("Welcome, Librarian!");
                whatToDoLibrarian();
            } else {
                Member member = database.getMemberById(memberId);
                if (member != null) {
                    System.out.println("Welcome, " + member.getFirstName() + "!");
                    whatToDoMember(memberId);
                } else {
                    System.out.println("Member not found.");
                }
            }
        } else {
            System.out.println("Invalid user input.");
        }
    }

    //Users choices
    public void whatToDoMember(int memberId){
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.println(Message.TRANSACTION.getMessage());
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
                    issueBook(memberId);
                    break;
                case 5:
                    // Return book(s)
                    returnBook(memberId);
                    break;
                case 6:
                    // See your account info
                    seeAccountDetails(memberId);
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
                    break;
            }

            System.out.println("Do you want to do anything else? (Y/N)");
            String continueChoice = scanner.next();
            continueLoop = continueChoice.equalsIgnoreCase("Y");
        }
    }

    //Librarian choices
    public void whatToDoLibrarian(){
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;
        while (continueLoop) {
            System.out.println(Message.LIBRARIANACTIONS.getMessage());
            int librarianChoice = scanner.nextInt();
            switch (librarianChoice) {
                case 1:
                    //To add member
                    addMember();
                    System.out.println("New Member list: " + database.getMembers());
                    break;
                case 2:
                    //To delete member
                    deleteMember();
                    System.out.println("New Member list: " + database.getMembers());
                    break;
                case 3:
                    //To add book
                    addBook();
                    System.out.println("New Author list: " + database.getAuthors());
                    System.out.println("New Book list: " + database.getBooks());
                    break;
                case 4:
                    //To delete book
                    deleteBook();
                    System.out.println("New Author list: " + database.getAuthors());
                    System.out.println("New Book list: " + database.getBooks());
                    break;
                case 5:
                    //To update book
                    updateBook();
                    System.out.println("New Book list: " + database.getBooks());
                    break;
                case 6:
                    System.out.println("New Author list: " + database.getAuthors());
                    System.out.println("New Book list: " + database.getBooks());
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
                    break;

            }
            System.out.println("Do you want to do anything else? (Y/N)");
            String continueChoice = scanner.next();
            continueLoop = continueChoice.equalsIgnoreCase("Y");
        }
    }

    private void updateBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the book you want to update:");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        Book existingBook = database.getBookById(bookId);
        if (existingBook != null) {
            System.out.println("Enter the new details for the book:");
            System.out.println("New Title:");
            String newTitle = scanner.nextLine();
            System.out.println("New Author ID:");
            int authorId = scanner.nextInt();
            scanner.nextLine();
            System.out.println("New Author First Name:");
            String authorFirstName = scanner.nextLine();
            System.out.println("New Author Last Name:");
            String authorLastName = scanner.nextLine();
            System.out.println("New Author Description:");
            String authorDescription = scanner.nextLine();
            System.out.println("New Price:");
            int newPrice = scanner.nextInt();
            scanner.nextLine();


            Author newAuthor = new Author(authorId, authorFirstName, authorLastName, authorDescription);
            Book updatedBook = new Book(existingBook.getId(), newAuthor, newTitle, newPrice, existingBook.getStatus());
            librarian.updateBook(updatedBook, database);
            System.out.println("Book details updated successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    //Librarian choice while deleting a book by id
    private void deleteBook() {
        System.out.println("Enter the ID of the book you want to delete:");
        Scanner scanner = new Scanner(System.in);
        int bookId = scanner.nextInt();

        Book book = database.getBookById(bookId);
        if(book != null){
            System.out.println("Are you sure about deleting " + book.getTitle() + "? (Y/N)");
            String confirmation = scanner.next();
            if(confirmation.equalsIgnoreCase("Y")){
                librarian.deleteBookById(bookId, database);
                //database.
                System.out.println("Book deleted successfully.");
            } else {
                System.out.println("Deletion canceled.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    //Librarian choice while adding a book by id
    private void addBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the details of the book:");
        System.out.println("Book ID:");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Author ID:");
        int authorId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Author First Name:");
        String authorFirstName = scanner.nextLine();
        System.out.println("Author Last Name:");
        String authorLastName = scanner.nextLine();
        System.out.println("Author Description:");
        String authorDescription = scanner.nextLine();
        System.out.println("Book Title:");
        String title = scanner.nextLine();
        System.out.println("Book Price:");
        int price = scanner.nextInt();
        scanner.nextLine();

        Author author = new Author(authorId, authorFirstName, authorLastName, authorDescription);
        Book book = new Book(bookId, author, title, price, Status.AVAILABLE);
        librarian.addBook(book, database);
        System.out.println("Book added successfully.");
    }

    //Librarian choice while deleting a member by id
    private void deleteMember() {
        System.out.println("Enter the ID of the member you want to delete:");
        Scanner scanner = new Scanner(System.in);
        int memberId = scanner.nextInt();

        Member member = database.getMemberById(memberId);
        if(member != null){
            System.out.println("Are you sure about deleting " + member.getName() + "? (Y/N)");
            String confirmation = scanner.next();
            if(confirmation.equalsIgnoreCase("Y")){
                librarian.deleteMember(member, database);
                System.out.println("Member deleted successfully.");
            } else {
                System.out.println("Deletion canceled.");
            }
        } else {
            System.out.println("Member not found.");
        }
    }

    //Librarian choice while adding a member by id
    private void addMember() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the details of the member:");
        System.out.println("Member ID:");
        int memberId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("First Name:");
        String firstName = scanner.nextLine();
        System.out.println("Last Name:");
        String lastName = scanner.nextLine();
        System.out.println("Faculty (ARCHITECTURE/LAW/SCIENCE/ENGINEERING/ART):");
        String facultyStr = scanner.nextLine();
        Faculty faculty = Faculty.valueOf(facultyStr.toUpperCase());
        System.out.println("Password:");
        String password = scanner.nextLine();
        System.out.println("Degree (for students) or Role (for instructors):");
        String degreeOrRole = scanner.nextLine();

        Member member;
        if (degreeOrRole.equalsIgnoreCase("BACHELOR") || degreeOrRole.equalsIgnoreCase("MASTER") || degreeOrRole.equalsIgnoreCase("PHD")) {
            Degree degree = Degree.valueOf(degreeOrRole.toUpperCase());
            member = new Student(memberId, firstName, lastName, new Account(), faculty, password, 1, degree);
        } else {
            Role role = Role.valueOf(degreeOrRole.toUpperCase());
            member = new Instructor(memberId, firstName, lastName, new Account(), faculty, password, role);
        }

        librarian.addMember(member, database);
        System.out.println("Member added successfully.");
    }

    //Check if user exist by id
    private void seeAccountDetails(int memberId) {
        Member member = database.getMemberById(memberId);
        System.out.println(member);
    }

    //Users choices while returning a book
    private void returnBook(int memberId) {
        System.out.println("Enter the ID of the book you want to return:");
        Scanner scanner = new Scanner(System.in);
        int bookId = scanner.nextInt();

        Book book = database.getBookById(bookId);
        Member member = database.getMemberById(memberId);

        if (book != null && member != null) {
            System.out.println(String.format("Dear %s, do you want to return this book? :", member.getFirstName()) + " ' " + book + " '");
            System.out.println("(Enter 'Y' to confirm, any other key to cancel)");
            String confirmation = scanner.next();

            if (confirmation.equalsIgnoreCase("Y")) {
                boolean returned = librarian.returnBook(book, member, database);
                if (returned) {
                    System.out.println("Book returned successfully.");
                } else {
                    System.out.println("Failed to return book.");
                }
            } else {
                System.out.println("Book return canceled.");
            }
        } else {
            System.out.println("Book or member not found.");
        }
    }

    //Users choices while issuing book
    private void issueBook(int memberId) {
        System.out.println("Enter the ID of the book you want to borrow:");
        Scanner scanner = new Scanner(System.in);
        int bookId = scanner.nextInt();

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

    //Users choices while searching a book by name
    private void searchByTitle(){
        System.out.println("Enter the title of the book you want to search:");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();

        librarian.searchByTitle(title, database);
    }

    //Users choices while searching an author by name
    private void searchByAuthor(){
        System.out.println("Enter the name of the author you want to search:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        librarian.searchByAuthor(name, database);
    }

}
