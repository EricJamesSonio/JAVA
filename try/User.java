package try;

public class User {
    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNo;


    public User (String firstName, String middleName, String lastName, String phoneNo) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString () {
        return String.format("Name : %s " + "%s " + "%s ",firstName,middleName,lastName);
    }

    public String getPhone() {
        return phoneNo;
    }

}
