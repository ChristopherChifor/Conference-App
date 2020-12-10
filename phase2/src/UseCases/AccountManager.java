package UseCases;

import Entities.User;
import Gateways.JsonDatabase;
import Util.UserType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
     * Create a user with a name, username, password, and user type
     *
     * @param name     the name of the user(String) that is to be created.
     * @param username the username of the user(String) that is to be created.
     * @param password the password of the user(String) that is to be created.
     * @param type     the type of user(User.UserType) that is to be created.
     */
    public void createUser(String name, String username, String password, UserType type) {
        User newUser = new User(name, username, password, type);
        userJsonDatabase.write(newUser, username);
    }

    /**
     * Changes the type of the user.
     *
     * @param username the username of the user(String).
     * @param type     the type that we want the user's type to change to(User.UserType).
     */
    public void changeUserType(String username, UserType type) {
        getUser(username).setUserType(type);
    }

    /**
     * Get a specific user based on their username.
     *
     * @param username the username of the user we are trying to get(String).
     * @return that user if a user with that username exists.
     * ELse, return null.
     */
    public User getUser(String username) {
        return userJsonDatabase.read(username);
    }

    /**
     * Getter for user type.
     *
     * @param username username
     * @return User.UserType of the user
     */
    public UserType getUserType(String username) {
        return getUser(username).getUserType();
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

    public List<String> getUsernamesOfType(UserType userType) {
        return userJsonDatabase.filterStream(u -> u.getUserType() == userType)
                .map(User::getUsername)
                .collect(Collectors.toList());
    }
}
