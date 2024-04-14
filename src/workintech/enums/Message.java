package workintech.enums;

public enum Message {
    WELCOME("Welcome to the Workintech Library!"),
    TRANSACTION("How can I help you?" + "\n"
            + " -> See books : 1" + "\n"
            + " -> Search book by title : 2" + "\n"
            + " -> Search author by name : 3" + "\n"
            + " -> Borrow book(s) : 4"+ "\n"
            + " -> Return book(s) : 5"),
    GOODBYE("See you later!");

    private String message;
    Message(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
