import java.util.HashMap;

public class AccountManager {
    private HashMap<String, User> users; // never share this

    public boolean createUser(String username, String password, User.UserType type)
    {
        // TODO
        //  make user
        return false;
    }
    public boolean canCreateUser(String username) {
        // TODO
        //  verify if username taken
        return false;
    }

    public boolean changeUserType(String username, User.UserType _type)
    {
        // TODO
        //  delete if unused.
        return false;
    }

    public String authenticateUser(String username, String password) {
        // TODO
        //  if password is correct for username, return this username, else null.
        return null;
    }


    protected User getUser(String username) {
        // TODO
        //  returns the user with this username
        //  ideally accessible only to other usecases
        //  MESSAGE SLACK BEFORE IMPLENTING
        return null;
    }
}
