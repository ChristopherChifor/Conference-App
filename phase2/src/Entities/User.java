package Entities;

import Util.UserType;

import java.io.Serializable;

/**
 * @author Alex and Parssa
 */
public class User implements Serializable {
    private String name;
    private String username;
    private String password;

    private UserType userType;

    /**
     * Constructor for a User in the system.
     * Attendee, Speaker, Organizer, VIP
     * @param name name of the user
     * @param username username for login
     * @param password password for login
     * @param userType type of the user
     */
    public User(String name, String username, String password, UserType userType) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    /**
     * Gets the name of the user
     * @return name as a string
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the username of the user
     * @return username as a string
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password of the user
     * @return password as a string
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the type of the user
     * @return usertype
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Sets the user type Attendee, Organizer, Speaker, VIP
     * @param userType type of the user
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}
