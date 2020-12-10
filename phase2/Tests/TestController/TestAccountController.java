package TestController;

import Controllers.AccountController;
import UseCases.AccountManager;
import Util.UserType;
import org.junit.Test;

import static org.junit.Assert.*;


public class TestAccountController {

    @Test
    public void test_authenticateUser() {
        AccountController accountController = new AccountController();
        AccountManager accountManager = new AccountManager();
        accountManager.createUser("Jafar", "Jafar_baba", "jafar123", UserType.ORGANIZER);
        assertEquals(UserType.ORGANIZER, accountController.authenticateUser("Jafar_baba", "jafar123"));
        assertEquals(null, accountController.authenticateUser("Jafar_baba", "jafar321"));
        assertEquals(null, accountController.authenticateUser("Jafar", "jafar123"));
    }

    @Test
    public void test_createUser() {
        AccountController accountController = new AccountController();
        AccountManager accountManager = new AccountManager();
        accountManager.createUser("Jafar2", "Jafar_baba", "jafar123", UserType.ORGANIZER);
        assertFalse(accountController.createUser("Jafar", "Jafar_baba",
                "jafar123", "jafar123", UserType.ORGANIZER));
        assertFalse(accountController.createUser("baba", "baba", "baba1",
                "baba2", UserType.ORGANIZER));
        assertTrue(accountController.createUser("baba", "baba", "baba1",
                "baba1", UserType.ORGANIZER));
        assertTrue(accountManager.userExists("baba"));
    }



}
