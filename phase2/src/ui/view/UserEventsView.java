package ui.view;

import Entities.User.UserType;
import Presenters.UserEventPresenter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A view for accessing the events a user is enrolled in.
 */
public class UserEventsView extends JPanel implements View {
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();

    // left panel
    private JList<String> myEventsList;

    //right panel
    private JButton deselect = new JButton("Deselect");
    private JButton message = new JButton("Message");
    private JButton unenroll = new JButton("Unenroll");
    private JButton addEvent = new JButton("Add Event");
    private JButton downloadSchedule = new JButton("Download PDF");

    private List<String> myEvents;

    private UserType type;
    private String username;

    private UserEventPresenter presenter;

    public UserEventsView() {

        //TODO FETCH EVENTS OF THIS USER
        //TODO FETCH USERNAME
//        UserEventPresenter presenter
        this.presenter = new UserEventPresenter();
        myEvents = new ArrayList<>();
        myEvents.add("Event 1");
        myEvents.add("Event 2");
        myEvents.add("Event 3"); // <-- // TODO DELETE THIS STUFF (was used for testing)

        this.type = UserType.ORGANIZER; // TODO FETCH USERTYPE


        myEventsList = new JList<>();
        DefaultListModel m = new DefaultListModel<>();
        myEvents.stream().forEach(m::addElement);
        myEventsList.setModel(m);

        myEventsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        //this defines what happens when a user double clicks on an event in the list
        myEventsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList source = (JList) e.getSource();
                if (e.getClickCount() == 2) {
                    String eventName = myEvents.get(source.locationToIndex(e.getPoint()));
                    goToEvent(eventName);
                }
            }
        });
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(new JLabel("<html><h2>My Events</h2></html>"), BorderLayout.NORTH);
        leftPanel.add(myEventsList, BorderLayout.CENTER);
        JScrollPane leftPanelScroll = new JScrollPane(leftPanel);
        leftPanelScroll.createVerticalScrollBar();
        leftPanelScroll.setPreferredSize(new Dimension(100, 100));

        deselect.addActionListener(e -> deselect());
        unenroll.addActionListener(e -> unenroll());
        message.addActionListener(e -> message());
        addEvent.addActionListener(e -> addEvent());
        downloadSchedule.addActionListener(e -> download());


        rightPanel.setLayout(new GridBagLayout());
        GridBagConstraints cst = new GridBagConstraints();
        cst.fill = GridBagConstraints.BOTH;
        cst.insets = new Insets(5, 10, 5, 10);
        cst.gridx = 0;
        cst.gridy = 0;
        rightPanel.add(new JLabel("<html><h2>Menu</h2></html>"), cst);
        cst.gridy++;
        rightPanel.add(deselect, cst);
        cst.gridy++;
        if (type == UserType.SPEAKER || type == UserType.ORGANIZER) {
            rightPanel.add(message, cst);
            cst.gridy++;
        }
        if (type == UserType.ATTENDEE) { // TODO ADD VIP TYPE HERE
            rightPanel.add(unenroll, cst);
            cst.gridy++;
            rightPanel.add(addEvent, cst);
            cst.gridy++;
        }
        rightPanel.add(downloadSchedule, cst);

        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout());
        add(leftPanelScroll, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

    }

    /**
     * Triggered when a user double-clicks on an event name. Goes to information view for that event.
     *
     * @param eventName
     */
    private void goToEvent(String eventName) {
        System.out.println("User wants to go to: " + eventName);
        //todo take user to event info
    }

    /**
     * Triggered when user presses deselect button.
     */
    private void deselect() {
        myEventsList.clearSelection();
    }

    /**
     * Triggered when user presses message button; accessible only to Speakers and Organizers
     */
    private void message() {
        List<String> events = getSelectedEvents();
        if (events.isEmpty()) {
            showErrorDialog("You don't have any events selected!");
            return;
        }

        String message = JOptionPane.showInputDialog(null, "What would you like to send?", "Message Events", JOptionPane.INFORMATION_MESSAGE);
        if (message == null||message.isEmpty()) {
            showErrorDialog("You didn't enter a message; nothing was sent");
            return;
        }

        //TODO SEND MESSAGE TO EVENTS
    }

    /**
     * Triggered when user presses unenroll button.
     */
    private void unenroll() {
        List<String> events = getSelectedEvents();
        if (events.isEmpty()) {
            showErrorDialog("You don't have any events selected!");
            return;
        }
        int[] ix = myEventsList.getSelectedIndices();
        DefaultListModel<String> model = (DefaultListModel)myEventsList.getModel();

        for (int i=(ix.length-1);i>=0; i--){
            model.remove(ix[i]);
        }
        myEvents.removeAll(events);

        //TODO UNENROLL THIS USER FROM EVENTS
    }

    /**
     * Triggered when user presses add event button.
     */
    private void addEvent() {
        //todo show menu of all events
    }

    private List<String> getSelectedEvents() {
        return myEventsList.getSelectedValuesList();
    }

    private void download() {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int val = fc.showSaveDialog(null);
        if (val != JFileChooser.APPROVE_OPTION) return;

        File file = fc.getSelectedFile();
        System.out.println("User wants to save to: " + file.toString()+".pdf");

        presenter.userScheduleToPDF(file.toString()+".pdf"); // Converts user schedule to pdf

    }

    @Override
    public String getViewName() {
        return String.format("%s's Events", username);
    }
}
