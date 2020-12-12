package ui.view;

import Presenters.EventListPresenter;
import Presenters.OrganizerPresenter;
import Util.UserType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

/**
 * View that facilitates organizer capabilities. WIP
 */
public class OrganizerView extends JPanel implements View {

    private JButton newRoomButton = new JButton("New Room");
    private JButton newEventButton = new JButton("New Event");


    // username lists:
    private List<String> speakers;
    private List<String> attendees;
    private List<String> organizers;
    private List<String> vips;

    private OrganizerPresenter presenter;

    public OrganizerView(OrganizerPresenter presenter) {
        this.presenter = presenter;
        speakers = presenter.getSpeakers();
        attendees = presenter.getAttendees();
        organizers = presenter.getOrganizers();
        vips = presenter.getVips();

        newRoomButton.addActionListener(e -> newRoom());
        newEventButton.addActionListener(e -> newEvent());

        setBorder(new EmptyBorder(20, 20, 20, 20));
        JPanel usersPanel = new JPanel();
        usersPanel.setLayout(new BoxLayout(usersPanel, BoxLayout.PAGE_AXIS));
        usersPanel.add(makeUserPanel(speakers, "Speakers"));
        usersPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        usersPanel.add(makeUserPanel(vips, "VIPs"));
        usersPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        usersPanel.add(makeUserPanel(attendees, "Attendees"));
        usersPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        usersPanel.add(makeUserPanel(organizers, "Organizers"));

        JScrollPane usersScroll = new JScrollPane(usersPanel);
        usersScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        usersScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        setLayout(new BorderLayout(40, 40));
        add(usersScroll, BorderLayout.WEST);

        JPanel panel = new JPanel(new BorderLayout(20, 20));
        EventListPresenter listPresenter = new EventListPresenter(presenter.getUsername(), UserType.ORGANIZER, presenter.getMainPresenter());
        EventListView eventsPanel = new EventListView(listPresenter);
        JScrollPane scroll = new JScrollPane(eventsPanel);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(newEventButton, BorderLayout.NORTH);
        panel.add(newRoomButton, BorderLayout.SOUTH);


        add(panel, BorderLayout.CENTER);

    }

    /**
     * Triggered when user presses new event button
     */
    private void newEvent() {
        presenter.newEvent();
    }


    /**
     * Triggered when user presses new room button.
     */
    private void newRoom() {
        String roomname = JOptionPane.showInputDialog(null, "What should the new room be called?", "New Room", JOptionPane.INFORMATION_MESSAGE);
        if (roomname == null || roomname.isEmpty()) {
            showErrorDialog("You did not enter anything");
            return;
        }

        String cap = JOptionPane.showInputDialog(null, "What should the capacity be (number)?", "New Room", JOptionPane.INFORMATION_MESSAGE);
        if (cap == null || cap.isEmpty()) {
            showErrorDialog("You did not enter anything");
            return;
        }

        int capacity = 0;
        try {
            capacity = Integer.parseInt(cap);
            if (capacity <= 0) throw new NumberFormatException("Capacity less than or equal to 0 entered");
        } catch (NumberFormatException e) {
            showIncorrectInputDialog("You did not a valid capacity");
            return;
        }

        if (!showConfirmDialog("Are you sure you want to create this room? It cannot be deleted or edited")) return;


        presenter.newRoom(roomname, capacity, this);
    }


    private JPanel makeUserPanel(List<String> usernames, String title) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cst = new GridBagConstraints();
        cst.gridy = 0;
        cst.gridx = 0;
        cst.fill = GridBagConstraints.BOTH;
        cst.insets = new Insets(2, 7, 2, 7);
        panel.add(new JLabel(String.format("<html><h3>%s</h3></html>", title)), cst);
        cst.gridy++;

        for (String username : usernames) {
            JButton userButton = new JButton(username);
            userButton.addActionListener(e -> presenter.goToUser(username));
            panel.add(userButton, cst);
            cst.gridy++;
        }

        return panel;
    }

    @Override
    public String getViewName() {
        return "Organizer Tools";
    }
}
