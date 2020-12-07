package ui.view;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Stack;

/**
 * The main view of the program. Contains a card layout for adding and showing views.
 * After a view is exited with the goBack command it is deleted. Stores views in a stack.
 * <p>
 * WIP
 */
public class MainView extends JDialog implements View {
    private final CardLayout cards;
    private final JPanel mainPanel;
    private final Stack<String> history;
    private final Stack<JPanel> panels; // because of CardLayout's stupidity

    public MainView() {
        cards = new CardLayout();
        mainPanel = new JPanel(cards);
        history = new Stack<>();
        panels = new Stack<>();

        setTitle("Conference Manager");
        setSize(new Dimension(600, 400));

        //does anything need to be done on close?
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setModal(true);
        setVisible(true);
    }

    /**
     * Adds and shows view.
     *
     * @param view view being added; must be a JPanel
     */
    public void showView(View view) {
        if (!(view instanceof JPanel)) throw new IllegalArgumentException("View must be a JPanel");
        String id = Integer.toString(view.hashCode() + new Random().nextInt(100));
        history.push(id);
        panels.push((JPanel) view);
        mainPanel.add((JPanel) view, id);
        cards.show(mainPanel, id);
        pack();
    }

    /**
     * For going to the previous view. Shows previous view; deletes current view.
     * Does nothing if this is the last view.
     */
    public void goBack() {
        if (history.size() == 1) {
            // reached last panel
            return;
        }

        JPanel currentPanel = panels.pop();

        history.pop(); // current panel
        String prevID = history.peek(); // previous panel (not removed from stack)
        cards.show(mainPanel, prevID);

        mainPanel.remove(currentPanel);
        pack();
        repaint();
        revalidate();
    }

    @Override
    public String getViewName() {
        return "Main View"; // would never be called
    }
}
