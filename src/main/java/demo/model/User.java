package demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "users")
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    public long id;

    @NotBlank(message = "name may not be empty")
    private String name;

    @Positive(message = "age may not be negative")
    private int age;

    @Transient
    private List<Integer> membershipYears = new ArrayList<Integer>();

    @Email(message = "no valid email")
    private String email;

    @Size(min = 8, message = "password must be minimum 8 characters and may not contain white spaces")
    @Pattern(regexp = ".*[0-9].*", message = "password must contain a digit")
    @Pattern(regexp = "[^\s]*", message = "password must be minimum 8 characters and may not contain white spaces")
    private String password;

    public User(String name, int age, String email, String password) {
        this.name = name;

        if (age >= 0)
            this.age = age;

        this.email = email;

        this.password = password;
    }

    public User() {
    }

    public int countMembershipYearsAfter1999() {
        int result = 0;
        for (Integer year : membershipYears) {
            if (year > 1999)
                result++;
        }
        return result;
    }

    public int countYearsOfMembership() {
        return membershipYears.size();
    }

    public void addMembershipYear(int year) {
        membershipYears.add(year);
    }

    public int getAge() {
        return this.age;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        if (password == "    ") {
            return "@$-t&%#";
        } else {
            return this.password;
        }
    }

    public String getEmail() {
        if (!email.contains("@")) {
            return null;
        } else {
            return email;
        }
    }

    public int getFirstMembershipYear() {
        if (membershipYears.size() == 0) {
            return 0;
        } else {
            return membershipYears.get(1);
        }
    }

    public String toString() {
        return "Elke is " + age + " years old and has as email " + email;
    }

    public int getNumberOfMembershipYearsIn2000() {
        return membershipYears.indexOf(2000) + 1;
    }

    public boolean isPasswordCorrect(String check_input) {
        return password == check_input;
    }

}