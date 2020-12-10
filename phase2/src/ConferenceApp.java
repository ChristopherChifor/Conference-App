import Presenters.LoginPresenter;
import Presenters.MainPresenter;

/**
 * The entry point for our program.
 */
public class ConferenceApp {
    public static void main(String[] args) {
        MainPresenter pres = new MainPresenter();
        pres.addPresenter(new LoginPresenter(pres));
    }
}
