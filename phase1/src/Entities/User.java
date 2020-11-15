package Entities;

import java.io.Serializable;

/**
 * @author Alex and Parssa
 */
public class User implements Serializable {
    private String name;
    private String username;
    private String password;

    public enum UserType { ATTENDEE, ORGANIZER, SPEAKER}

    private UserType userType;

    public User(String name, String username, String password, UserType userType) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
