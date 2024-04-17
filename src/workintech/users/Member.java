package workintech.users;

import workintech.enums.Faculty;

import java.util.Objects;

public abstract class Member {
    private int id;
    private String firstName;
    private String lastName;
    private Account account;
    private Faculty faculty;
    private String password;

    public Member(int id, String firstName, String lastName, Account account, Faculty faculty, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.account = account;
        this.faculty = faculty;
        this.password = password;
    }

    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public Account getAccount() {
        return account;
    }
    public String getName() {return firstName + " " + lastName; }
    public String getPassword() {return password;}

    @Override
    public String toString() {
        return
                "Hello " + firstName + " " + lastName + '\'' +
                "Your account details : " + '\''
                        + account;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return id == member.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
