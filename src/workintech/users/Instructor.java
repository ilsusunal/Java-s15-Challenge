package workintech.users;

import workintech.enums.Faculty;
import workintech.enums.Role;

public class Instructor extends Member{
    private Role role;

    public Instructor(int id, String firstName, String lastName, Account account, Faculty faculty, Role role) {
        super(id, firstName, lastName, account, faculty);
        this.role = role;
    }
}
