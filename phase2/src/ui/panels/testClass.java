package ui.panels;

import Entities.User;
import ui.AppWindow;
import ui.state.EventBundle;
import ui.state.EventEditBundle;
import ui.view.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;

public class testClass {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        ArrayList<String> speakers = new ArrayList<>();
        speakers.add("Bob1");
        speakers.add("Bob2");
//
//        ArrayList<String> rooms = new ArrayList<>();
//        speakers.add("Room1");
//        speakers.add("Room2");
//        speakers.add("Room3");
//        speakers.add("Room4");

        // frame.add(new EventEditView(new EventEditBundle(rooms, speakers)));
//        frame.add(new UserSettingsView(false, "Alex"));
//        frame.add(new UserEventsView(User.UserType.ORGANIZER));

//        EventBundle bundle = new EventBundle("Epic event", "This is a description of this epic event.", speakers, "Event Hall", Calendar
//                .getInstance(), "1:00");
//        frame.add(new EventView(bundle, User.UserType.ORGANIZER));

//        frame.add(new OrganizerView());
//
//        frame.setVisible(true);
//        frame.pack();
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        AppWindow w = new AppWindow(null);

    }
}
