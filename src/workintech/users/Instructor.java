package workintech.users;

import workintech.enums.Faculty;
import workintech.enums.Role;

public class Instructor extends Member{
    private Role role;

    public Instructor(int id, String firstName, String lastName, Account account, Faculty faculty, String password, Role role) {
        super(id, firstName, lastName, account, faculty, password);
        this.role = role;
    }
}
