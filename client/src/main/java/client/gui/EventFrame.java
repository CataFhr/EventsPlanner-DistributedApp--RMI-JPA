package client.gui;

import client.controller.EventController;
import lib.dto.AdressDto;
import lib.dto.EventDto;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

public class EventFrame extends JFrame {

    private JList list1;
    private JPanel mainPanel;
    private JButton addButton;
    private JTextField textField4;
    private JTextField textField2;
    private JTextField textField1;
    private JTextField textField3;
    private JButton deleteButton;
    private JList list2;
    private JMenuBar menuBar;
    private JMenu optionsMenu;
    private JMenuItem logoutItem;
    private DefaultListModel<EventDto> model;
    private DefaultListModel<EventDto> model1;
    private int userId;

    public EventFrame(int userId) {
        super("Events");
        this.userId = userId;
        model = new DefaultListModel<>();
        model1 = new DefaultListModel<>();
        list1.setModel(model);
        list2.setModel(model1);
        showUserEvents();
        showAllEvents();

        addButton.addActionListener(ev -> addEvent());

        deleteButton.addActionListener(ev -> deleteEvent());

        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                twoClickedForListGuests(e);
            }
        });

        logoutItem = new JMenuItem("logout");
        logoutItem.addActionListener(ev -> logout());
        optionsMenu = new JMenu("Options");
        optionsMenu.add(logoutItem);
        menuBar = new JMenuBar();
        menuBar.add(optionsMenu);

        setContentPane(mainPanel);
        setJMenuBar(menuBar);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void showUserEvents() {
        List<EventDto> events = (List<EventDto>) EventController.getInstance().findByUserId(userId);
        Collections.sort(events);
        model.clear();
        events.forEach(model::addElement);
    }

    private void showAllEvents() {
        List<EventDto> events = (List<EventDto>) EventController.getInstance().findAll();
        Collections.sort(events);
        model1.clear();
        events.forEach(model1::addElement);
    }

    private void addEvent() {
        try {
            String name = textField1.getText();
            AdressDto adressDto = new AdressDto(textField3.getText(), textField4.getText());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(textField2.getText(), formatter);
            if (date.isBefore(LocalDate.now())) {
                throw new RuntimeException();
            }
            EventDto eventDto = new EventDto(name, date, adressDto, userId);
            EventController.getInstance().addEvent(eventDto);
            JOptionPane.showMessageDialog(null, "The event has been created");
            showUserEvents();
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
            textField4.setText("");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "There is already an event on this date");
            textField2.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Incorrect date");
            textField2.setText("");
        }
    }

    private void deleteEvent() {
        List<EventDto> events = list1.getSelectedValuesList();
        if (!events.isEmpty()) {
            events.stream().forEach(eventDto -> EventController.getInstance().deleteEvent(eventDto));
            JOptionPane.showMessageDialog(null, "Event has been deleted");
        }
        showUserEvents();
        list1.setSelectedIndex(-1);
    }

    private void twoClickedForListGuests(MouseEvent e) {
        boolean isItemSelected = list1.getSelectedValue() != null;
        if (isItemSelected && e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
            EventDto event = (EventDto) list1.getSelectedValue();
            new GuestFrame(event.getId(), userId);
            this.dispose();
        }
    }

    private void logout() {
        new LoginFrame();
        this.dispose();
    }
}
