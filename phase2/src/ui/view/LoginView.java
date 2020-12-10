package ui.view;

import Presenters.LoginPresenter;

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

        presenter.tryLogin(username, password);

        //todo show user the next view
    }

    /**
     * Method triggered when user presses sign up button.
     */
    private void signUp() {
        //todo
    }

    @Override
    public String getViewName() {
        return "Login";
    }
}
