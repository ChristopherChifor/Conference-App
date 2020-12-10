package ui;

import Presenters.MainPresenter;
import ui.view.View;

import javax.swing.*;
import java.awt.*;

/**
 * The window for displaying gui of the app.
 */
public class AppWindow extends JDialog {
    private MainPresenter mainPresenter;
    private JPanel panel;
    private JButton backButton = new JButton("Back");

    private JPanel currentView = null;

    public AppWindow(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
        panel = new JPanel(new BorderLayout(20,20));
        backButton.addActionListener(e->goBack());

        JPanel buttonBar = new JPanel(new BorderLayout(10,10));
        buttonBar.add(backButton, BorderLayout.WEST);
        buttonBar.add(Box.createRigidArea(new Dimension(200,30)), BorderLayout.CENTER);

        panel.add(buttonBar, BorderLayout.NORTH);

        add(panel);
        setTitle("Conference App");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new Dimension(600,400));
        setVisible(true);

    }

    public void showView(View view){
        if(!(view instanceof JPanel)) throw new IllegalArgumentException("View must be JPanel");
        if(currentView != null){
            panel.remove(currentView);
        }

        System.out.println("Show view run");
        panel.add((JPanel) view, BorderLayout.CENTER);
        currentView = (JPanel) view;

        setTitle(view.getViewName());

        repaint();
        revalidate();
    }

    /**
     * Triggered when user presses back button
     */
    private void goBack(){
        mainPresenter.back();
    }
}
