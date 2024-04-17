package workintech;

import workintech.datas.LibraryDatabase;
import workintech.users.Member;

import java.util.Scanner;

public class UserInterface {
    private int id;
    private String password;
    private boolean isLibrarian;

    public int getId() {return id;}
    public String getPassword() {return password;}
    public void setIsLibrarian(boolean isLibrarian) {this.isLibrarian= isLibrarian;}
    public boolean isLibrarian() {return isLibrarian;}

    //Take the info from user : librarian or member?
    public void whoAreYou(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you the librarian?");
        System.out.println("1 for YES");
        System.out.println("2 for NO");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Enter security code:");
                String securityCode = scanner.next();
                if (!securityCode.equals("bla")) {
                    System.out.println("Invalid security code.");
                    whoAreYou();
                } else {
                    id = 123;
                    setIsLibrarian(true);
                }
                break;
            case 2:
                System.out.println("Enter your ID:");
                id = scanner.nextInt();
                System.out.println("Enter your password:");
                password = scanner.next();
                break;
            default:
                System.out.println("Invalid choice.");
                whoAreYou();
        }
    }

    public boolean verifyUser(int memberId, String password, LibraryDatabase database){
        Member member = database.getMemberById(memberId);
        if (member != null) {
            if (member.getPassword().equals(password)) {
                return true; // Member exists and password is correct
            } else {
                System.out.println("Wrong password.");
                return false; // Member exists but password is incorrect
            }
        } else {
            System.out.println("Member with ID " + memberId + " not found.");
            return false; // Member with the given ID doesn't exist
        }
    }

}
