package ui.view;

import Presenters.MainMenuPresenter;
import Util.UserType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainMenuView extends JPanel implements View {

    private JButton events = new JButton("Events");
    private JButton messages = new JButton("Messages");
    private JButton account = new JButton("Account");
    private JButton organizerTools = new JButton("Organizer Tools");
    private JButton exit = new JButton("Exit");

    private MainMenuPresenter presenter;

    private String username;
    private UserType type;

    public MainMenuView(MainMenuPresenter presenter) {
        this.presenter = presenter;
        username = presenter.getUsername();
        type = presenter.getType();

        events.addActionListener(e -> goEvents());
        messages.addActionListener(e -> goMessages());
        account.addActionListener(e -> goAccount());
        organizerTools.addActionListener(e -> goOrganizerTools());
        exit.addActionListener(e -> exit());

        setBorder(new EmptyBorder(20, 20, 20, 20));
        setLayout(new GridBagLayout());
        GridBagConstraints cst = new GridBagConstraints();
        cst.gridx = 0;
        cst.gridy = 0;
        cst.fill = GridBagConstraints.BOTH;
        cst.insets = new Insets(10, 10, 10, 10);

        add(events, cst);
        cst.gridy++;
        add(messages, cst);
        cst.gridy++;
        add(account, cst);
        cst.gridy++;
        if (type == UserType.ORGANIZER) {
            add(organizerTools, cst);
            cst.gridy++;
        }
        add(exit, cst);

    }

    /**
     * Triggered when user presses events button.
     */
    private void goEvents() {
        //todo
    }

    /**
     * Triggered when user presses messages button.
     */
    private void goMessages() {
        //todo
    }

    /**
     * Triggered when user presses account button.
     */
    private void goAccount() {
        //todo
    }

    /**
     * Triggered when user presses organizer tools button (if they can do it)
     */
    private void goOrganizerTools() {
        //todo
    }

    /**
     * Triggered when user presses exit button (exits program)
     */
    private void exit() {
        System.exit(0);
    }


    @Override
    public String getViewName() {
        return "Main Menu";
    }
}
