package ui.panels;

import javax.swing.*;
import java.awt.*;

public class EventDisplayPanel extends JFrame {
    private JPanel leftPanel;
    private JLabel eventTitle;
    private JTextPane eventDesc;
    private JLabel speakerName;
    private JButton messageSpeaker;


    private JPanel rightPanel;
    private JLabel roomNumber;
    private JLabel timeLabel;
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


    public EventDisplayPanel() {
        setLayout(new GridBagLayout());

        leftPanel = new JPanel();
        rightPanel = new JPanel();

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));

        eventTitle = new JLabel("My Cool Event");
        eventDesc = new JTextPane();
        eventDesc.setText("<h3>Hello World</h3><p>Welcome to my event. This is a cool event");
        speakerName = new JLabel("Alex");
        messageSpeaker = new JButton("Message Speaker");
        messageSpeaker.addActionListener(e->testAction());

        leftPanel.add(eventTitle);
        leftPanel.add(eventDesc);
        leftPanel.add(new JLabel("Speaker:"));
        leftPanel.add(speakerName);
        leftPanel.add(messageSpeaker);

        cst.gridx = 0;
        cst.gridy = 0;
        add(leftPanel, cst);

        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));

        roomNumber = new JLabel("5");
        timeLabel = new JLabel("12:30");
        dateLabel = new JLabel("20 April, 2020");
        durationLabel = new JLabel("1:30");

        enroll = new JButton("Enroll");
        messageEvent = new JButton("Message Event");
        enroll.addActionListener(e->testAction());
        messageEvent.addActionListener(e->testAction());


        rightPanel.add(new JLabel("Room"));
        rightPanel.add(roomNumber);
        rightPanel.add(new JLabel("Time"));
        rightPanel.add(timeLabel);
        rightPanel.add(new JLabel("Date"));
        rightPanel.add(dateLabel);
        rightPanel.add(new JLabel("Duration"));
        rightPanel.add(durationLabel);
        rightPanel.add(enroll);
        rightPanel.add(messageEvent);

        cst.gridx = 1;
        add(rightPanel, cst);

    }

    private void testAction(){
        System.out.println("Button Press");
    }

    /**
     * Helper method for adding a new spacer.
     */
    private void makeSpace(JComponent component){
        component.add(Box.createRigidArea(new Dimension(0,7)));
        component.add(Box.createRigidArea(new Dimension(2,0)));
    }
}
