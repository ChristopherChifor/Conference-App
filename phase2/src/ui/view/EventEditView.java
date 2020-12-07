package ui.view;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilCalendarModel;
import ui.state.EventBundle;
import ui.state.EventEditBundle;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * A view for editing or creating events. WIP
 */
public class EventEditView extends JPanel implements View {
    private JTextField titleField = new JTextField();
    private JList<String> speakerField;
    private JTextField timeField = new JTextField();

    // stuff for making JDatePicker work.
    private UtilCalendarModel calModel = new UtilCalendarModel();
    private Properties properties = new Properties();

    {
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");
    }

    private AbstractFormatter formatter = new AbstractFormatter() {
        private SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

        @Override
        public Object stringToValue(String text) throws ParseException {
            return f.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return f.format(cal.getTime());
            }

            return "";
        }
    };


    private JDatePanelImpl datePanel = new JDatePanelImpl(calModel, properties);
    private JDatePickerImpl dateField = new JDatePickerImpl(datePanel, formatter);

    private JTextField durationField = new JTextField();

    private JComboBox<String> roomField = new JComboBox<>();
    private JTextArea descField = new JTextArea();

    private JButton saveButton = new JButton("Save");
    private JButton cancelButton = new JButton("Cancel");

    // for converting calendar date-time to time
    private SimpleDateFormat df = new SimpleDateFormat("h:mm a");

    /**
     * Constructor for pre-populated forms (for editing events)
     *
     * @param bundle event information
     */
    public EventEditView(EventEditBundle bundle) {
        //TODO CHANGE THE CONSTRUCTOR TO PASS IN A PRESENTER (USED FOR SAVING)

        GridBagConstraints cst = new GridBagConstraints();
        setLayout(new GridBagLayout());

        speakerField = new JList<>(bundle.getSpeaker().toArray(new String[0]));
        speakerField.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        speakerField.setLayoutOrientation(JList.VERTICAL_WRAP);

        cst.gridx = 0;
        cst.gridy = 0;
        cst.gridwidth = 1;
        cst.gridheight = 1;
        cst.insets = new Insets(7, 7, 7, 7);
        cst.fill = GridBagConstraints.BOTH;

        add(new JLabel("Title:"), cst);
        cst.gridx = 1;
        add(titleField, cst);
        cst.gridy = 1;
        cst.gridx = 0;
        add(new JLabel("Speaker:"), cst);
        cst.gridx = 1;
        add(new JScrollPane(speakerField), cst);
        cst.gridy = 2;
        cst.gridx = 0;
        add(new JLabel("Time:"), cst);
        cst.gridx = 1;
        add(timeField, cst);
        cst.gridy = 3;
        cst.gridx = 0;
        add(new JLabel("Date:"), cst);
        cst.gridx = 1;
        add(dateField, cst);
        cst.gridy = 4;
        cst.gridx = 0;
        add(new JLabel("Room:"), cst);
        cst.gridx = 1;
        add(roomField, cst);
        cst.gridy = 5;
        cst.gridx = 0;
        cst.gridwidth = 2;
        add(new JLabel("Description"), cst);
        cst.gridy = 6;
        cst.gridheight = 2;
        add(descField, cst);
        cst.gridy = 8;
        cst.gridheight = 1;
        cst.gridwidth = 1;
        add(saveButton, cst);
        cst.gridx = 1;
        add(cancelButton, cst);

        saveButton.addActionListener(e -> save());
        cancelButton.addActionListener(e -> cancel());


        titleField.setText(bundle.getTitle());
        descField.setText(bundle.getDescription());
        durationField.setText(bundle.getDuration());

        bundle.getRoomOptions().stream().forEach(roomField::addItem);
        if (!bundle.getRoom().isEmpty())
            roomField.setSelectedItem(bundle.getRoom());

        timeField.setText(df.format(bundle.getTime().getTime())); // todo check this
        calModel.setValue(bundle.getTime()); // todo and this

        repaint();
        revalidate();
    }

    /**
     * Triggered when user presses save button
     */
    private void save() {
        EventBundle bundle = null;
        try {
            bundle = getEventBundle();
        } catch (ParseException e) {
            showIncorrectInputDialog("Proper time format: hour:minute am/pm\nProper duration format: hour:minute");
            return;
        }
        // bundle should now be valid input.


        // TODO TELL PRESENTER TO SAVE BUNDLE
    }

    /**
     * Triggered when user presses cancel button
     */
    private void cancel() {
        if (!showConfirmDialog("Are you sure? Your changes will be unsaved.")) return;
        // todo return to main window.
    }

    /**
     * Gets the values from the fields as EventBundle
     *
     * @return EventBundle of info from fields.
     * @throws ParseException iff user entered invalid input, i.e., invalid time or duration
     */
    public EventBundle getEventBundle() throws ParseException {
        String title = titleField.getText();
        String description = descField.getText();
        String room = (String) roomField.getSelectedItem();
        List<String> speaker = speakerField.getSelectedValuesList();
        String duration = durationField.getText();

        // accepting 0:00, 1:10, 1323:00 but not 10:87
        Pattern durationPattern = Pattern.compile("^\\d+:[0-5]\\d$");
        if (!durationPattern.matcher(duration).find()) {
            throw new ParseException("Invalid duration entered", 0);
        }


        // todo mega sus stuff:
        String timeString = timeField.getText();
        Date timeNoDate = df.parse(timeString); // throws if improper
        Calendar dateTime = (Calendar) dateField.getModel().getValue();
        dateTime.setTime(timeNoDate);

        return new EventBundle(title, description, speaker, room, dateTime, duration);
    }

    @Override
    public String getViewName() {
        return "Edit/Create Event";
    }
}
