package ui.view;


import Entities.Message;
import Presenters.MessagePresenter;
import
        ui.panels.MessageDisplayPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * The view for messaging. Shows people you're messaging with and the messages
 */
public class MessageView extends JPanel implements View {
    private ButtonGroup senderButtonGroup = new ButtonGroup();
    private Map<String, MessageDisplayPanel> panelMap = new HashMap<>();
    private Set<String> conversations = new HashSet<>();

    private JPanel messageCards = new JPanel();
    private JPanel pplPanel = new JPanel();

    private String selectedUsername = "";
    private JLabel selectedUsernameLabel = new JLabel(selectedUsername);

    private MessagePresenter presenter;

    public MessageView(MessagePresenter presenter) throws HeadlessException {
        this.presenter = presenter;

        setLayout(new BorderLayout(20, 20));
        setBorder(new EmptyBorder(20,20,20,20));

        messageCards.setLayout(new CardLayout());
        BoxLayout pplLayout = new BoxLayout(pplPanel, BoxLayout.PAGE_AXIS);
        pplPanel.setLayout(pplLayout);

        initMessages();

        add(messageCards, BorderLayout.CENTER);
        JScrollPane pplScroll = new JScrollPane(pplPanel);
        add(pplScroll, BorderLayout.WEST);

        JPanel sendMessagePanel = new JPanel(new BorderLayout(20, 20));
        JTextField messageField = new JTextField("");
        JButton sendButton = new JButton("Send");

        sendButton.addActionListener(e -> {
            String messageText = messageField.getText();
            messageField.setText("");

            // if no user is selected or archive selected
            if (selectedUsername.equals("") || selectedUsername.equals("Archived")) {
                JOptionPane.showMessageDialog(null, "Cannot send message to this address", "Bad Input", JOptionPane.ERROR_MESSAGE);
            }

            presenter.sendMessage(selectedUsername, messageText);  // not this line

            Message m = new Message(presenter.getUsername(), selectedUsername, messageText);
            panelMap.get(selectedUsername).addMessages(m);

            repaint();
            revalidate();
        });

        sendMessagePanel.add(messageField, BorderLayout.CENTER);
        sendMessagePanel.add(sendButton, BorderLayout.EAST);

        add(sendMessagePanel, BorderLayout.SOUTH);


        JPanel topPanel = new JPanel(new FlowLayout());
        JButton newConversationButton = new JButton("New Conversation");
        newConversationButton.addActionListener(e -> newConversation());
        topPanel.add(newConversationButton);
        topPanel.add(selectedUsernameLabel);

        JButton deselectButton = new JButton("Deselect");
        JButton deleteButton = new JButton("Delete");
        JButton archiveButton = new JButton("Archive");

        deselectButton.addActionListener(e -> deselectMessages());
        deleteButton.addActionListener(e -> deleteSelected());
        archiveButton.addActionListener(e -> archiveSelected());

        topPanel.add(deselectButton);
        topPanel.add(deleteButton);
        topPanel.add(archiveButton);

        add(topPanel, BorderLayout.NORTH);

        messageCards.add(new JPanel(), "-BLANK PAGE");

        CardLayout cards = (CardLayout) messageCards.getLayout();
        cards.show(messageCards, "-BLANK PAGE");

    }

    /**
     * Deletes the messages that have been selected. If no messages selected
     * or no username selected, does nothing.
     */
    private void deleteSelected() {
        if (selectedUsername.equals("")) return;
        MessageDisplayPanel panel = panelMap.get(selectedUsername);
        List<Message> messages = panel.getSelectedMessages();

        presenter.deleteMessages(messages);


        // removes selected from view
        panel.removeSelectedMessages();
        revalidate();
        repaint();
    }

    /**
     * Adds the selected messages to archive.
     */
    private void archiveSelected() {
        if (selectedUsername.equals("") || selectedUsername.equals("Archived")) return;
        MessageDisplayPanel panel = panelMap.get(selectedUsername);
        List<Message> messages = panel.getSelectedMessages();
        presenter.markAsArchived(messages);

        MessageDisplayPanel archivePanel = panelMap.get("Archived");
        archivePanel.addMessageList(messages);
    }

    /**
     * Action that happens when new conversation button is pressed. Dialog pops up to ask for
     * username that user wants to message. Then another asks for message.
     */
    private void newConversation() {
        String username = JOptionPane.showInputDialog(null,
                "Who do you want to message?",
                "New Conversation",
                JOptionPane.INFORMATION_MESSAGE);
        if (username == null || username.isEmpty()) {
            // should catch if user presses cancel button
            return;
        }

        String message = JOptionPane.showInputDialog(null,
                "What do you want to say to " + username + "?",
                "New Conversation",
                JOptionPane.INFORMATION_MESSAGE);
        if (message == null || message.isEmpty()) {
            // should catch if user presses cancel button
            return;
        }

        if (!presenter.canMessage(username)) return;
        System.out.println("omg no way");
        presenter.sendMessage(username, message);
        // TODO IF COULDN'T SEND A MESSAGE CALL showIncorrectInputDialog

        List<Message> messages = presenter.getConversation(username);

        // this code should only run if message was sent!
        if (conversations.contains(username)) return;
        // creates a new button for this user if it does not exist.
        addMessageToggleButton(username, messages);
    }

    private void deselectMessages() {
        if (selectedUsername.equals("")) return;
        MessageDisplayPanel panel = panelMap.get(selectedUsername);
        panel.deselectSelected();
    }


    private void initMessages() {
        // Populate the inbox with actual information

        List<String> inbox = presenter.getInbox(); //TODO  change this to not output IDS

        for (String person : inbox) {
            List<Message> messages = presenter.getConversation(person);
            addMessageToggleButton(person, messages);
        }

        List<Message> archivedMessages = presenter.getArchivedMessages();
        addMessageToggleButton("Archived", archivedMessages);


    }

    /**
     * Helper method for creating a toggle button that shows a MessageDisplayPanel
     *
     * @param text     button name
     * @param messages list of messages to be displayed
     */
    private void addMessageToggleButton(String text, List<Message> messages) {
        JToggleButton senderButton = new JToggleButton(text);
        conversations.add(text);

        // if conversation is unread, make button text bold.
        if (!text.equals("Archived")) {
            if (!presenter.isRead(text)) {
                buttonBold(senderButton);
            }
        }
        MessageDisplayPanel messagePanel = new MessageDisplayPanel(messages);
        panelMap.put(text, messagePanel);

        JScrollPane messageScroll = new JScrollPane(messagePanel);

        messageCards.add(messageScroll, text);

        // this happens when button is toggled:
        senderButton.addActionListener(e -> {
            CardLayout cards = (CardLayout) messageCards.getLayout();
            cards.show(messageCards, text);

            if (!text.equals("Archived")) {
                //todo this is weird
                presenter.markAsRead(text);
            }

            buttonUnBold(senderButton);
            selectedUsername = text;
            selectedUsernameLabel.setText(String.format("<html><h2>%s</h2></html>",text));
            System.out.println("Selected button: " + text);
        });

        senderButtonGroup.add(senderButton);
        pplPanel.add(senderButton);
    }

    private void buttonBold(JToggleButton button) {
        Font buttonFont = new Font(button.getFont().getName(), Font.BOLD, button.getFont().getSize());
        button.setFont(buttonFont);
        button.repaint();
        button.revalidate();
    }

    private void buttonUnBold(JToggleButton button) {
        Font buttonFont = new Font(button.getFont().getName(), Font.PLAIN, button.getFont().getSize());
        button.setFont(buttonFont);
        button.repaint();
        button.revalidate();
    }

    @Override
    public String getViewName() {
        return "Messages";
    }
}