package ui.panels;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A panel that lists all events.
 */
public class EventListPanel extends JPanel {
    List<JButton> buttons = new ArrayList<>();

    public EventListPanel(boolean vip) {
        //TODO GET PRESENTER

        // TODO FETCH LIST OF ALL EVENTS IN THE GLOBAL SCHEDULE (vip events only if vip)
        List<String> eventNames = new ArrayList<>();
        eventNames.add("Event 1");
        eventNames.add("Event 2");
        eventNames.add("Event 3");
        eventNames.add("Event 4");
        eventNames.add("Event 5");
        eventNames.add("Event 6");

        JPanel panel = new JPanel();

        panel.setLayout(new GridBagLayout());
        GridBagConstraints cst = new GridBagConstraints();
        cst.gridy = 0;
        cst.gridx = 1;
        cst.fill = GridBagConstraints.BOTH;
        cst.insets = new Insets(7, 7, 7, 7);

        panel.add(new JLabel("<html></h2>Events</h2></html>"), cst);
        cst.gridy = 1;

        for (String event : eventNames) {
            boolean vipEvent = true; // TODO IS THIS EVENT VIP?
            //todo decide if showing vip events to plebs
            String buttonText = vipEvent?"\u2605 "+event:event;

            JButton eventButton = new JButton(buttonText);
            eventButton.addActionListener(e -> {
                System.out.println("User wants to go to: " + event);
                // todo take user to event view
            });

            panel.add(eventButton, cst);
            cst.gridy++;
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.createVerticalScrollBar();
        add(scrollPane);
    }


}
