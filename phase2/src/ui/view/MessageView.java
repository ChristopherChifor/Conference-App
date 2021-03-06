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
    private JTextField messageField;
    private JButton sendButton;

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
        messageField = new JTextField("");
        messageField.addActionListener(e->sendAction());
        messageField.setEnabled(false);

        sendButton = new JButton("Send");
        sendButton.addActionListener(e -> sendAction());
        sendButton.setEnabled(false);

        sendMessagePanel.add(messageField, BorderLayout.CENTER);
        sendMessagePanel.add(sendButton, BorderLayout.EAST);

        add(sendMessagePanel, BorderLayout.SOUTH);


        JPanel topPanel = new JPanel(new FlowLayout());
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
     * Event triggered when user presses send button.
     */
    private void sendAction(){
        String messageText = messageField.getText();
        if(messageText.isEmpty()){
            return;
        }
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


    private void deselectMessages() {
        if (selectedUsername.equals("")) return;
        MessageDisplayPanel panel = panelMap.get(selectedUsername);
        panel.deselectSelected();
    }


    private void initMessages() {
        // Populate the inbox with actual information

        List<String> inbox = presenter.getInbox();

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
                presenter.markAsRead(text);
            }

            buttonUnBold(senderButton);
            selectedUsername = text;
            selectedUsernameLabel.setText(String.format("<html><h1>%s</h1></html>",text));
            messageField.setEnabled(true);
            sendButton.setEnabled(true);

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