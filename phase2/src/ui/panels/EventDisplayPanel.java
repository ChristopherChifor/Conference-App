package ui.panels;

import ui.state.EventBundle;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Panel for displaying events.
 */
public class EventDisplayPanel extends JFrame {
    private JPanel leftPanel;
    private JLabel eventTitle;
    private JTextArea eventDesc;
    private JLabel speakerName;
    private JButton messageSpeaker;


    private JPanel rightPanel;
    private JLabel roomNumber;
    private JLabel dateLabel;
    private JLabel durationLabel;
    private JButton enroll;
    private JButton unenroll;
    private JButton messageEvent;

    private GridBagConstraints cst;

    {
        cst = new GridBagConstraints();
        cst.gridy = 0;
        cst.gridx = 0;
        cst.gridwidth = 1;
        cst.gridheight = 1;
        cst.insets = new Insets(7, 7, 7, 7);
        cst.fill = GridBagConstraints.BOTH;
    }


    public EventDisplayPanel(EventBundle bundle) {
        setLayout(new GridBagLayout());

        leftPanel = new JPanel();
        rightPanel = new JPanel();

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));

        eventTitle = new JLabel(bundle.getTitle());
        eventDesc = new JTextArea();
        eventDesc.setLineWrap(true);
        eventDesc.setText(bundle.getDescription());
        speakerName = new JLabel(String.join(", ", bundle.getSpeaker()));
        messageSpeaker = new JButton("Message Speaker");
        messageSpeaker.addActionListener(e -> testAction());

        leftPanel.add(eventTitle);
        makeSpace(leftPanel);
        leftPanel.add(eventDesc);
        makeSpace(leftPanel);
        leftPanel.add(new JLabel("Speaker:"));
        makeSpace(leftPanel);
        leftPanel.add(speakerName);
        makeSpace(leftPanel);
        leftPanel.add(messageSpeaker);

        cst.gridx = 0;
        cst.gridy = 0;
        add(leftPanel, cst);

        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));

        roomNumber = new JLabel(bundle.getRoom());

        SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm z yyyy", Locale.CANADA);
        dateLabel = new JLabel(df.format(bundle.getTime())); // todo check this

        durationLabel = new JLabel(bundle.getDuration());

        enroll = new JButton("Enroll");
        messageEvent = new JButton("Message Event");
        enroll.addActionListener(e -> testAction());
        messageEvent.addActionListener(e -> testAction());
        enroll.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        messageEvent.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        Container roomDiv = new Container();
        roomDiv.setLayout(new BorderLayout(5, 5));
        roomDiv.add(new JLabel("Room:"), BorderLayout.CENTER);
        roomDiv.add(roomNumber, BorderLayout.EAST);

        Container dateDiv = new Container();
        dateDiv.setLayout(new BorderLayout(5, 5));
        dateDiv.add(new JLabel("Date:"), BorderLayout.CENTER);
        dateDiv.add(dateLabel, BorderLayout.EAST);

        Container durationDiv = new Container();
        durationDiv.setLayout(new BorderLayout(5, 5));
        durationDiv.add(new JLabel("Duration:"), BorderLayout.CENTER);
        durationDiv.add(durationLabel, BorderLayout.EAST);

        rightPanel.add(roomDiv);
        rightPanel.add(dateDiv);
        rightPanel.add(durationDiv);
        rightPanel.add(enroll);
        rightPanel.add(messageEvent);

        cst.gridx = 1;
        add(rightPanel, cst);

    }


    private void testAction() {
        System.out.println("Button Press");
    }

    /**
     * Helper method for adding a new spacer.
     */
    private void makeSpace(JComponent component) {
        component.add(Box.createRigidArea(new Dimension(0, 7)));
        component.add(Box.createRigidArea(new Dimension(2, 0)));
    }
}
