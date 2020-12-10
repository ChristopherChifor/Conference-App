package ui.view;

import Presenters.EventPresenter;
import Util.UserType;
import ui.state.EventBundle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;

/**
 * A view for displaying (read-only) details of an event. Allows attendees to enroll/unenroll,
 * organizers and speakers to message event, and organizers to open EditEventView. Also has
 * button for messaging speakers.
 *
 * //todo issue with the window sizing!
 */
public class EventView extends JPanel implements View {
    private final EventBundle event;
    private final SimpleDateFormat df = new SimpleDateFormat("MMMM d, yyyy");
    private final SimpleDateFormat tf = new SimpleDateFormat("h:mm a");

    private final JButton enrollButton = new JButton("Enroll");
    private final JButton editButton = new JButton("Edit");
    private final JButton unenrollButton = new JButton("Unenroll");
    private final JButton messageEvent = new JButton("Message Event");
    private final JButton messageSpeakerButton = new JButton("\u2709");

    private final UserType type;
    private final GridBagConstraints cst = new GridBagConstraints();
    private final EventPresenter eventPresenter;

    public EventView(EventBundle event, UserType type, EventPresenter eventPresenter) {
        
        this.eventPresenter = eventPresenter;
        this.event = event;
        this.type = type;

        messageSpeakerButton.addActionListener(e -> messageSpeaker());
        enrollButton.addActionListener(e->enroll());
        unenrollButton.addActionListener(e->unenroll());
        editButton.addActionListener(e->edit());
        messageEvent.addActionListener(e->messageEvent());

        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(10,10,10,10));
        cst.gridx = 0;
        cst.gridy = 0;
        cst.fill = GridBagConstraints.BOTH;
        cst.insets = new Insets(7, 10, 7, 10);

        cst.gridwidth = 2;
        add(new JLabel(String.format("<html><h2>%s</h2></html>", event.getTitle())), cst);
        cst.gridy++;
        cst.gridheight = 3;
        JEditorPane ep = new JEditorPane();
        ep.setText(event.getDescription());
        ep.setEditable(false);
        add(ep, cst);
        cst.gridheight = 1;
        cst.gridy+=2;
        cst.gridwidth = 1;
        add(new JLabel("Speaker(s):"), cst);
        cst.gridwidth = 2;
        cst.gridy++;
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout(10, 10));
        String speakers = event.getSpeaker().toString();
        speakers = speakers.substring(1, speakers.length() - 1);
        p.add(new JLabel(speakers), BorderLayout.CENTER);
        p.add(messageSpeakerButton, BorderLayout.EAST);
        add(p,cst);

        cst.gridwidth = 1;
        cst.gridx = 2;
        cst.gridy = 0;
        add(new JLabel("Room: "), cst);
        cst.gridx++;
        add(new JLabel(event.getRoom()), cst);
        cst.gridx--;
        cst.gridy++;
        add(new JLabel("Date:"), cst);
        cst.gridx++;
        add(new JLabel(df.format(event.getTime().getTime())), cst);
        cst.gridy++;
        cst.gridx--;
        add(new JLabel("Time:"), cst);
        cst.gridx++;
        add(new JLabel(tf.format(event.getTime().getTime())), cst);
        cst.gridy++;
        cst.gridx--;
        add(new JLabel("Duration: "), cst);
        cst.gridx++;
        add(new JLabel(event.getDuration()), cst);
        cst.gridy++;
        cst.gridx--;
        cst.gridwidth = 2;

        switch (this.type) {
            case ATTENDEE:
            case VIP:
                boolean enrolled = eventPresenter.attendeeInEvent(event.getTitle());
                if (enrolled) {
                    add(unenrollButton, cst);
                } else {
                    add(enrollButton, cst);
                }
                break;
            case ORGANIZER:
                add(editButton, cst);
                cst.gridy++;
                add(messageEvent, cst);
                break;
            case SPEAKER:
                add(messageEvent, cst);
                break;
        }
    }

    /**
     * Triggered when user presses message speaker button
     */
    private void messageSpeaker() {
        String message = JOptionPane.showInputDialog(null, "What is your messages?", "Message Speaker", JOptionPane.INFORMATION_MESSAGE);
        if(message == null || message.isEmpty()){
            showErrorDialog("You did not enter a message; nothing was sent");
            return;
        }

        //TODO SEND MESSAGE TO SPEAKER(S)
    }

    /**
     * Triggered when user presses unenroll button.
     * Assumes user is attendee and enrolled in this event
     */
    private void unenroll() {
        if (!showConfirmDialog("Are you sure you want to unenroll?\nYou may be unable to re-enroll later.")) return;
        eventPresenter.cancelEnrolment(event.getTitle());
        remove(unenrollButton);
        add(enrollButton, cst);
        repaint();
        revalidate();
    }

    /**
     * Triggered when user presses enroll button.
     * Assumes the user is attendee and not enrolled in this event.
     */
    private void enroll() {
        boolean canEnroll = eventPresenter.canSignUpForEvent(event.getTitle());

        if (!canEnroll) {
            showWarningDialog("Sorry, it looks like you can't enroll in this event.");
            return;
        }
        eventPresenter.signUpEvent(event.getTitle());
        remove(enrollButton);
        add(unenrollButton, cst);
        repaint();
        revalidate();
    }

    /**
     * Triggered when user presses message event button. Assumes that the user is allowed to message
     * the event.
     */
    private void messageEvent() {
        String message = JOptionPane.showInputDialog(null, "What is your messages?", "Message Event", JOptionPane.INFORMATION_MESSAGE);
        if(message == null || message.isEmpty()){
            showErrorDialog("You did not enter a message; nothing was sent");
            return;
        }

        //TODO SEND MESSAGE TO THE EVENT
    }

    /**
     * Triggered when user presses edit button. Assumes the user is allowed to edit the event.
     */
    private void edit() {
        //todo open eventeditview
    }

    @Override
    public String getViewName() {
        return "Viewing " + event.getTitle();
    }
}
