package ui.view;

import Presenters.EventListPresenter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A panel that lists all events.
 */
public class EventListView extends JPanel implements View {
    public EventListView(EventListPresenter presenter) {
        List<String> eventNames = presenter.getEventNames();

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
            String buttonText = presenter.isVIP(event) ? "\u2605 " + event : event; // putting star before vip events

            JButton eventButton = new JButton(buttonText);
            eventButton.addActionListener(e -> presenter.goToEvent(event));

            panel.add(eventButton, cst);
            cst.gridy++;
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.createVerticalScrollBar();
        add(scrollPane);
    }


    @Override
    public String getViewName() {
        return "All Events";
    }
}
