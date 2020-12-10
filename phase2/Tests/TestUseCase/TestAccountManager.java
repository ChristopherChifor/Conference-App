package TestUseCase;

import UseCases.AccountManager;
import Util.UserType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestAccountManager {

    //TODO Test Cases for the AccountManager Use Case Class

    @Test
    public void test_createUser() {
        AccountManager accountManager = new AccountManager();
        accountManager.createUser("Jason", "Jason_baba", "Jason123", UserType.ATTENDEE);
        assertEquals(false, accountManager.getUser("jason_baba") == null);
        assertEquals(true, accountManager.getUser("jason_2") == null);
    }

    @Test
    public void changeUserType() {
        AccountManager accountManager = new AccountManager();
        accountManager.createUser("Jason", "Jason_baba", "Jason123", UserType.ATTENDEE);
        accountManager.changeUserType("Jason_baba", UserType.SPEAKER);
        assertEquals(false, accountManager.getUser("jason_baba").getUserType() == UserType.ATTENDEE);
        assertEquals(true, accountManager.getUser("jason_baba").getUserType() == UserType.SPEAKER);
    }
}
