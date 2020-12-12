package TestUseCase;

import Controllers.AccountController;
import UseCases.AccountManager;
import Util.UserType;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestAccountManager {


//    @Test
//    public void test_createUser() {
//        AccountController accountController = new AccountController();
//        AccountManager accountManager = new AccountManager();
//        accountController.createUser("Jason", "Jason_baba", "Jason123", "Jason123",UserType.ATTENDEE);
//        assertNotNull(accountManager.getUser("jason_baba"));
//        assertNull(accountManager.getUser("jason_2"));
//    }
//
//    @Test
//    public void test_changeUserType() {
//        AccountManager accountManager = new AccountManager();
//        AccountController accountController = new AccountController();
//        accountController.createUser("Jason", "Jason_baba", "Jason123","Jason123",  UserType.ATTENDEE);
//        accountManager.changeUserType("Jason_baba", UserType.SPEAKER);
//        assertNotSame(accountManager.getUser("jason_baba").getUserType(), UserType.ATTENDEE);
//        assertSame(accountManager.getUser("jason_baba").getUserType(), UserType.SPEAKER);
//    }
//
//    @Test
//    public void test_getUserNames() {
//        AccountManager accountManager = new AccountManager();
//        AccountController accountController = new AccountController();
//        accountController.createUser("Jafar", "Jafar_baba", "password",
//                "password", UserType.VIP);
//        accountController.createUser("Sepehr", "Sepehr_baba", "password2",
//                "password2", UserType.ORGANIZER);
//        assertTrue(accountManager.getUsernames().contains("Jafar_baba"));
//        assertTrue(accountManager.getUsernames().contains("Sepehr_baba"));
//    }
//
//    @Test
//    public void test_userExists() {
//        AccountManager accountManager = new AccountManager();
//        accountManager.createUser("Jason", "Jason_baba", "Jason123", UserType.ATTENDEE);
//        assertTrue(accountManager.userExists("Jason_baba"));
//        assertFalse(accountManager.userExists("khoobi"));
//    }
}
