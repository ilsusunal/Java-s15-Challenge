package workintech.users;

import workintech.enums.Degree;
import workintech.enums.Faculty;

public class Student extends Member{
    private int whichYear;
    private Degree degree;


    public Student(int id, String firstName, String lastName, Account account, Faculty faculty, String password, int whichYear, Degree degree ) {
        super(id, firstName, lastName, account, faculty, password);
        this.whichYear = whichYear;
        this.degree = degree;
    }
}
