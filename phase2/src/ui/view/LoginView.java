package ui.view;

import Presenters.LoginPresenter;
import Presenters.MainMenuPresenter;
import Presenters.MainPresenter;
import Presenters.SignUpPresenter;
import Util.UserType;

import javax.swing.*;
import java.awt.*;

/**
 * View for logging in an existing user.
 */
public class LoginView extends JPanel implements View {
    private JTextField usernameField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JButton loginButton = new JButton("Login");
    private JButton signupButton = new JButton("Sign Up");
    private LoginPresenter presenter;

    public LoginView(LoginPresenter presenter) {

        this.presenter = presenter;
        setLayout(new GridBagLayout());
        GridBagConstraints cst = new GridBagConstraints();

        loginButton.addActionListener(e -> login());
        signupButton.addActionListener(e -> signUp());
        setPreferredSize(new Dimension(300,200));
        usernameField.setPreferredSize(new Dimension(200,20));

        cst.gridx = 0;
        cst.gridy = 0;
        cst.fill = GridBagConstraints.BOTH;
        cst.insets = new Insets(7, 7, 7, 7);
        add(new JLabel("Username: "), cst);
        cst.gridx = 1;
        add(usernameField, cst);
        cst.gridx = 0;
        cst.gridy++;
        add(new JLabel("Password: "), cst);
        cst.gridx = 1;
        add(passwordField, cst);
        cst.gridx = 0;
        cst.gridy++;
        cst.gridwidth = 2;
        add(loginButton, cst);
        cst.gridy++;
        add(signupButton, cst);

    }

    /**
     * Method triggered when user presses log in button.
     */
    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        UserType result = presenter.tryLogin(username, password);

        if(result == null){
            showErrorDialog("Username or Password Incorrect");
            return;
        }

        // we're in
        MainPresenter mp = presenter.getMainPresenter();
        MainMenuPresenter menu = new MainMenuPresenter(result, username, mp);
        mp.addPresenter(menu);
    }

    /**
     * Method triggered when user presses sign up button.
     */
    private void signUp() {
        MainPresenter mp = presenter.getMainPresenter();
        SignUpPresenter su = new SignUpPresenter(mp);
        mp.addPresenter(su);
    }

    @Override
    public String getViewName() {
        return "Login";
    }
}
