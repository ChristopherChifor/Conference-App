package ui;

import Presenters.MainPresenter;
import sun.invoke.empty.Empty;
import ui.view.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;

import com.bulenkov.darcula.*;

/**
 * The window for displaying gui of the app.
 */
public class AppWindow extends JDialog {
    private MainPresenter mainPresenter;
    private JPanel panel;
    private JButton backButton = new JButton("Back");
    private JButton toggleDark = new JButton("\u263D");
    private boolean light = true;

    private JPanel currentView = null;

    public AppWindow(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
        panel = new JPanel(new BorderLayout(20, 20));
        panel.setBorder(new EmptyBorder(5,5,5,5));
        backButton.addActionListener(e -> goBack());
        toggleDark.addActionListener(e ->toggleDark());

        JPanel buttonBar = new JPanel(new BorderLayout(10, 10));
        buttonBar.add(backButton, BorderLayout.WEST);
        buttonBar.add(Box.createRigidArea(new Dimension(200, 30)), BorderLayout.CENTER);
        buttonBar.add(toggleDark, BorderLayout.EAST);

        panel.add(buttonBar, BorderLayout.NORTH);

        add(panel);
        setTitle("Conference App");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new Dimension(600, 600));
        setVisible(true);

    }

    /**
     * Changes look and feel.
     */
    private void toggleDark() {
        BasicLookAndFeel laf = new DarculaLaf();
        try {
            if(light) {
                UIManager.setLookAndFeel(laf);
            }else{
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        light = !light;

        repaint();
        revalidate();
    }

    public void showView(View view) {
        if (!(view instanceof JPanel)) throw new IllegalArgumentException("View must be JPanel");
        if (currentView != null) {
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
    private void goBack() {
        mainPresenter.back();
    }
}
