package workintech.enums;

public enum Message {
    WELCOME("Welcome to the Workintech Library!"),
    TRANSACTION("How can I help you?" + "\n"
            + " -> See books : 1" + "\n"
            + " -> Search book by title : 2" + "\n"
            + " -> Search author by name : 3" + "\n"
            + " -> Borrow book(s) : 4"+ "\n"
            + " -> Return book(s) : 5" + "\n"
            + " -> See your account : 6"),
    GOODBYE("See you later!"),
    LIBRARIANACTIONS("Actions:"+ "\n"
            + " -> To add member : 1"+ "\n"
            + " -> To delete member : 2"+ "\n"
            + " -> To add book : 3"+ "\n"
            + " -> To delete book : 4" + "\n"
            + " -> To update book : 5");

    private String message;
    Message(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
