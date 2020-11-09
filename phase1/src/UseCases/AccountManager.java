package UseCases;

import Entities.User;

import java.util.HashMap;
import java.util.Set;

/**
 * @author Paya
 */
public class AccountManager {
    private HashMap<String, User> users; // never share this

    public boolean createUser(String name, String username, String password, User.UserType type)
    {

        // If username has not been taken, return true, else return false.
        // If username hasn't been taken, create a User class object with parameters name, username, password, and type.
        // Check to see if username has been taken using the canCreateUser method
        if (canCreateUser(username))
        {
            User newUser = new User(name, username, password, type);
            // Add the user object to the users HashMap.
            users.put(username, newUser);
            return true;
        }
        return false;

    }

    public boolean canCreateUser(String username) {

        // If the users HashMap contains a User value with the key, username, return false. If not, return true.
        if (users.containsKey(username))
        {
            return false;
        }
        return true;

    }

    public boolean changeUserType(String username, User.UserType type)
    {

        // If the users HashMap contains a User value with the key, username, set its UserType to type and return true.
        // If not, do not do anything and return false.
        if (users.containsKey(username))
        {
            users.get(username).setUserType(type);
            return true;
        }
        return false;
    }

    public String authenticateUser(String username, String password) {
        //  if password is correct for username, return this username, else null.
        if (users.get(username).getPassword().equals(password))
        {
            return username;
        }
        return null;
    }


    User getUser(String username) {
        //  returns the user with this username
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
