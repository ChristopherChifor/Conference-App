package ui.view;

import javax.swing.*;

public class MainMenuView extends JPanel implements View{

    private JButton events = new JButton("Events");
    private JButton messages = new JButton("Messages");
    private JButton account = new JButton("Account");
    private JButton organizerTools = new JButton("Organizer Tools");
    private JButton exit = new JButton("Exit");

    public MainMenuView(){

    }

    /**
     * Triggered when user presses events button.
     */
    private void goEvents(){
        //todo
    }

    /**
     * Triggered when user presses messages button.
     */
    private void goMessages(){
        //todo
    }

    /**
     * Triggered when user presses account button.
     */
    private void goAccount(){
        //todo
    }

    /**
     * Triggered when user presses organizer tools button (if they can do it)
     */
    private void goOrganizerTools(){
        //todo
    }

    /**
     * Triggered when user presses exit button (exits program)
     */
    private void exit(){
        System.exit(0);
    }


    @Override
    public String getViewName() {
        return "Main Menu";
    }
}
