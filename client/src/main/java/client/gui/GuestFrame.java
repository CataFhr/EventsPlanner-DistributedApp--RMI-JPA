package client.gui;

import client.controller.GuestController;
import lib.dto.EventDto;
import lib.dto.GuestDto;

import javax.swing.*;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class GuestFrame extends JFrame {

    private JTextField textField1;
    private JTextField textField3;
    private JButton addButton;
    private JList list1;
    private JPanel mainPanel;
    private JTextField textField4;
    private JTextField textField2;
    private JMenuBar menuBar;
    private JMenu optionsMenu;
    private JMenuItem backItem;
    private DefaultListModel<GuestDto> model;
    private int eventId;
    private int userId;

    public GuestFrame(int eventId, int userId) {
        super("Guests");
        this.eventId = eventId;
        this.userId = userId;
        model = new DefaultListModel<>();
        list1.setModel(model);

        showGuests();
        addButton.addActionListener(ev -> addGuests());

        backItem = new JMenuItem("back");
        backItem.addActionListener(ev -> back());
        optionsMenu = new JMenu("Options");
        optionsMenu.add(backItem);
        menuBar = new JMenuBar();
        menuBar.add(optionsMenu);

        setContentPane(mainPanel);
        setJMenuBar(menuBar);
        setSize(450, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void showGuests() {
        List<GuestDto> guests = (List<GuestDto>) GuestController.getInstance().findByEventId(eventId);
        Collections.sort(guests);
        model.clear();
        guests.forEach(model::addElement);
    }

    private void addGuests() {
        try {
            String name = textField1.getText();
            String email = textField2.getText();
            Set<String> phoneNumbers = Set.of(textField3.getText(), textField4.getText());
            GuestDto guestDto = new GuestDto(name, email, phoneNumbers, eventId);
            List<GuestDto> guests = (List<GuestDto>) GuestController.getInstance().findByEventId(eventId);
            if (guests.size() >= EventDto.getMAXCAPACITY()) {
                JOptionPane.showMessageDialog(null, "No more seats available at the event");
                return;
            }
            GuestController.getInstance().addGuest(guestDto);
            JOptionPane.showMessageDialog(null, "The guest has been added");
            showGuests();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "The guest is already on the event list");
        }
        textField1.setText("");
        textField3.setText("");
        textField4.setText("");
        textField2.setText("");
    }

    private void back() {
        new EventFrame(userId);
        this.dispose();
    }

}
