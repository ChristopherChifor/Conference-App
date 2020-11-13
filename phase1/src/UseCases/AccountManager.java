package UseCases;

import Entities.User;

import java.util.HashMap;
import java.util.Set;

/**
 * The use case class for creating and authenticating users.
 * @author Paya
 */
public class AccountManager {
    private HashMap<String, User> users; // never share this


    /**
     * Create a user with a name, username, password, and user type (Attendee, Organizer, Speaker) if the username
     * hasn't been taken (Checked using the canCreateUser method.)
     * @param name the name of the user(String) that is to be created.
     * @param username the username of the user(String) that is to be created.
     * @param password the password of the user(String) that is to be created.
     * @param type the type of user(User.UserType) that is to be created.
     * @return true if the user is created (when the username hasn't been taken).
     * Else, return false.
     */
    public boolean createUser(String name, String username, String password, User.UserType type)
    {

        if (canCreateUser(username))
        {
            User newUser = new User(name, username, password, type);
            // Add the User object to the users HashMap.
            users.put(username, newUser);
            return true;
        }
        return false;

    }


    /**
     * Specifies if a user can be created based on if the username has been taken or not.
     * @param username the username of the user(String) that we want to check to see if we can create.
     * @return true if the username hasn't been taken (which means a user with that username can be created.)
     * Else, return false.
     */
    public boolean canCreateUser(String username) {

        // If the users HashMap contains a User value with the key, username, return false. If not, return true.
        return !users.containsKey(username);

    }

    /**
     * Changes the type of the user.
     * @param username the username of the user(String).
     * @param type the type that we want the user's type to change to(User.UserType).
     * @return true and change the user type if the username is valid (There exists a user with that username).
     * Else, return false.
     */
    public boolean changeUserType(String username, User.UserType type) {
        // If the users HashMap contains a User value with the key, username, set its UserType to type and return true.
        if (users.containsKey(username)) {
            users.get(username).setUserType(type);
            return true;
        }
        return false;
    }

    /**
     * Authenticate a user if their password matches with the password associated with their username.
     * @param username the username of the user(String).
     * @param password the password the user has entered(String).
     * @return the username if the password entered matches with the password associated with the username.
     * ELse, return null.
     */
    public String authenticateUser(String username, String password) {
        if (users.get(username).getPassword().equals(password))
        {
            return username;
        }
        return null;
    }

    /**
     * Get a specific user based on their username.
     * @param username the username of the user we are trying to get(String).
     * @return that user if a user with that username exists.
     * ELse, return null.
     */
    User getUser(String username) {
        //  returns null if username does not exist in the users HashMap
        //  ideally accessible only to other usecases
        if (users.containsKey(username))
        {
            return users.get(username);
        }
        return null;
    }

    /**
     * Gets the set of all usernames.
     * @return set of all usernames.
     */
    public Set<String> getUsernames() {
        return users.keySet();
    }
}
