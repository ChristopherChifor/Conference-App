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
    }

    @Test
    public void test_changeUserType() {
        AccountController accountController = new AccountController();
        AccountManager accountManager = new AccountManager();
        accountController.createUser("Parssa", "Parssa_baba", "parssa123",
                "parssa123", UserType.VIP);
        accountController.changeUserType("Parssa_baba", UserType.ATTENDEE);
        assertEquals(UserType.ATTENDEE, accountManager.getUser("parssa_baba").getUserType());
        assertNotEquals(UserType.VIP, accountManager.getUser("parssa_baba").getUserType());
    }

    @Test
    public void getUsernamesOfType() {
        AccountController accountController = new AccountController();
        AccountManager accountManager = new AccountManager();
        accountController.createUser("VIP1", "VIP1", "password",
                "password", UserType.VIP);
        accountController.createUser("VIP2", "VIP2", "password",
                "password", UserType.VIP);
        accountController.createUser("NotVIP", "NotVIP", "password",
                "password", UserType.SPEAKER);
        assertTrue(accountController.getUsernamesOfType(UserType.VIP).contains("VIP1"));
        assertTrue(accountController.getUsernamesOfType(UserType.VIP).contains("VIP2"));
        assertFalse(accountController.getUsernamesOfType(UserType.VIP).contains("NotVIP"));
        assertFalse(accountController.getUsernamesOfType(UserType.ATTENDEE).contains("VIP1"));
    }

}
