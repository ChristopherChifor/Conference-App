package ui.view;

import Entities.User.UserType;

import javax.swing.*;
import java.awt.*;

/**
 * A view for modifying users settings. This view can either be accessed by a user to change their own
 * password, or by an organizer to change someone else's password and usertype; user type can only
 * be changed when accessed by organizer
 */
public class UserSettingsView extends JPanel implements View {
    private static final UserType[] types = {UserType.ATTENDEE, UserType.ORGANIZER, UserType.SPEAKER}; // TODO ADD MORE TYPES OR FETCH ARRAY
    private final String user;

    private final JLabel name;
    private final JLabel username;
    private final JPasswordField password; // could be changed
    private final JComboBox<UserType> typeField = new JComboBox(types); // could be changed iff organizer

    // TRUE IFF ACCESSING USER IS ORGANIZER; not the display user
    private final boolean organizerMode;

    private final JButton messages = new JButton("Messages");
    private final JButton events = new JButton("Events");
    private final JButton save = new JButton("Save");

    public UserSettingsView(boolean organizerMode, String user) {
        // TODO CHANGE CONSTRUCTOR TO PASS IN A PRESENTER (FOR CHANGING USER SETTINGS)
        this.organizerMode = organizerMode;
        this.user = user;

        name = new JLabel("<html><h1>Name</h1></html>"); // TODO FETCH NAME
        username = new JLabel(user);
        password = new JPasswordField("password"); //TODO FETCH PASSWORD
        typeField.setSelectedItem(UserType.ATTENDEE); //TODO FETCH USERTYPE

        if (!this.organizerMode) typeField.setEnabled(false); // can only be changed if in organizer mode

        messages.addActionListener(e -> messagesAction());
        save.addActionListener(e -> saveAction());
        events.addActionListener(e -> eventsAction());

        GridBagConstraints cst = new GridBagConstraints();
        setLayout(new GridBagLayout());

        cst.insets=new Insets(7,2,2,7);

        cst.fill = GridBagConstraints.BOTH;
        cst.gridwidth = 2;
        add(name, cst);
        cst.gridwidth = 1;
        cst.gridy=1;
        cst.gridx=0;
        add(new JLabel("Username: "), cst);
        cst.gridx = 1;
        add(username, cst);
        cst.gridy++;
        cst.gridx = 0;
        add(new JLabel("Password: "), cst);
        cst.gridx = 1;
        add(password, cst);
        cst.gridy++;
        cst.gridx = 0;
        add(new JLabel("Account Type: "), cst);
        cst.gridx = 1;
        add(typeField, cst);
        cst.gridy++;
        cst.gridx = 0;
        cst.gridwidth = 2;
        add(messages, cst);
        cst.gridy++;
        add(events, cst);
        cst.gridy++;
        add(save, cst);

    }

    /**
     * Triggered when user presses save button. Saves changes.
     */
    private void saveAction() {
        if(!showConfirmDialog("Are you sure you want to change settings?")) return;

        UserType type = (UserType) typeField.getSelectedItem(); // type changed only if organizer mode
        String password = new String(this.password.getPassword());

        //TODO CALL PRESENTER TO CHANGE TYPE AND PASSWORD

    }

    /**
     * Triggered by messages button. Opens the message view for the user being displayed.
     */
    private void messagesAction() {
        //todo
    }

    /**
     * Triggered by events button. Opens the events view for the user being displayed.
     */
    private void eventsAction() {
        //todo
    }
}
