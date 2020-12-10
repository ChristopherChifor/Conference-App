package UseCases;

import Entities.User;
import Gateways.JsonDatabase;
import Util.UserType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * The use case class for creating and authenticating users.
 *
 * @author Paya
 */
public class AccountManager implements Serializable {
    private JsonDatabase<User> userJsonDatabase;

    public AccountManager() {
        userJsonDatabase = new JsonDatabase<>("User", User.class);
    }

    /**
     * Create a user with a name, username, password, and user type (Attendee, Organizer, Speaker) if the username
     * hasn't been taken (Checked using the !userExists method.)
     *
     * @param name     the name of the user(String) that is to be created.
     * @param username the username of the user(String) that is to be created.
     * @param password the password of the user(String) that is to be created.
     * @param type     the type of user(User.UserType) that is to be created.
     * @return true if the user is created (when the username hasn't been taken).
     * Else, return false.
     */
    public boolean createUser(String name, String username, String password, UserType type) {
        if (!userExists(username)) {
            User newUser = new User(name, username, password, type);
            // Add the User object to the users HashMap.
            userJsonDatabase.write(newUser, username);
            return true;
        }
        return false;
    }

    /**
     * Changes the type of the user.
     *
     * @param username the username of the user(String).
     * @param type     the type that we want the user's type to change to(User.UserType).
     * @return true and change the user type if the username is valid (There exists a user with that username).
     * Else, return false.
     */
    public boolean changeUserType(String username, UserType type) {
        // If the users HashMap contains a User value with the key, username, set its UserType to type and return true.
        if (userExists(username)) {
            getUser(username).setUserType(type);
            return true;
        }
        return false;
    }

    /**
     * Authenticate a user if their password matches with the password associated with their username.
     *
     * @param username the username of the user(String).
     * @param password the password the user has entered(String).
     * @return the username if the password entered matches with the password associated with the username.
     * ELse, return null.
     */
    public String authenticateUser(String username, String password) {
        if (userExists(username)) {
            if (getUser(username).getPassword().equals(password))
                return username;
        }
        return null;
    }

    /**
     * Get a specific user based on their username.
     *
     * @param username the username of the user we are trying to get(String).
     * @return that user if a user with that username exists.
     * ELse, return null.
     */
    User getUser(String username) {
        return userJsonDatabase.read(username);
    }

    /**
     * Getter for user type.
     *
     * @param username username
     * @return User.UserType of the user; null if user DNE.
     */
    public UserType getUserType(String username) {
        User user = getUser(username);
        return user == null ? null : user.getUserType();
    }

    /**
     * Gets the set of all usernames.
     *
     * @return set of all usernames.
     */
    public Set<String> getUsernames() {
        Set<String> usernames = new HashSet<>();
        ArrayList<String> usernamesList = (ArrayList<String>) userJsonDatabase.getIds();
        for (String username : usernamesList) {
            usernames.add(username);
        }
        return usernames;
    }

    /**
     * Checks if user exists in users dictionary.
     *
     * @param username username of user to check
     * @return true if user exists
     */
    public boolean userExists(String username) {
        return userJsonDatabase.getIds().contains(username);
    }
}
