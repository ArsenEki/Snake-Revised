package authentication.dialogs;

import java.util.Objects;

public class User {
    private String userName;
    private String password;
    private double highscore;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User() {

    }

    /**
     * Constructor for user.
     * @param userName the username to be set for the user.
     * @param password the password to be set for the user.
     * @param highscore the highscore to be set for the user.
     */
    public User(String userName, String password, double highscore) {
        this.userName = userName;
        this.password = password;
        this.highscore = highscore;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getHighscore() {
        return highscore;
    }

    public void setHighscore(double highscore) {
        this.highscore = highscore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return userName.equals(user.userName)
                && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password, highscore);
    }

    @Override
    public String toString() {
        return "User: " + userName
                + ", password: " + password
                + ", highscore: " + highscore;
    }
}
