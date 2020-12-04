package ui.panels;

import ui.state.EventEditBundle;
import ui.view.EventEditView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class testClass {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        ArrayList<String> speakers = new ArrayList<>();
        speakers.add("Bob1");
        speakers.add("Bob2");
        speakers.add("Bob3");
        speakers.add("Bob4");

        ArrayList<String> rooms = new ArrayList<>();
        speakers.add("Room1");
        speakers.add("Room2");
        speakers.add("Room3");
        speakers.add("Room4");

        frame.add(new EventEditView(new EventEditBundle(rooms, speakers)));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }
}
