package pratice;

public class User {
    private String firstName;
    private String middleName;
    private String lastName;
    private ID id;

    public User(String firstName, String middleName, String lastName, ID id) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public ID getId() {
        return id;
    }
}