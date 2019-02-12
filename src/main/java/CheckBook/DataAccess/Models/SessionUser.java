package CheckBook.DataAccess.Models;

public class SessionUser {

    private int userID;
    private String firstName;
    private String lastName;

    public SessionUser() {
        this.userID = -1;
        this.firstName = null;
        this.lastName = null;
    }

    public SessionUser(int id, String fName, String lName) {
        this.firstName = fName;
        this.lastName = lName;
        this.userID = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
