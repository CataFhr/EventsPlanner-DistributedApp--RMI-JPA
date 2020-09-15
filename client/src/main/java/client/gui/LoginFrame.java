package client.gui;

import client.controller.UserController;
import lib.dto.UserDto;

import javax.swing.*;

public class LoginFrame extends JFrame {

    private JButton loginButton;
    private JButton registerButton;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPanel mainPanel;
    private JMenuBar menuBar;
    private JMenu optionsMenu;
    private JMenuItem exitItem;

    public LoginFrame() {
        loginButton.addActionListener(ev -> login());
        registerButton.addActionListener(ev -> registration());

        exitItem = new JMenuItem("exit");
        exitItem.addActionListener(ev -> System.exit(0));
        optionsMenu = new JMenu("Options");
        optionsMenu.add(exitItem);
        menuBar = new JMenuBar();
        menuBar.add(optionsMenu);

        setContentPane(mainPanel);
        setJMenuBar(menuBar);
        setSize(320, 130);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void login() {
        String username = textField1.getText();
        String password = new String(passwordField1.getPassword());
        UserDto userDto = new UserDto(username, password);
        try {
            int id = UserController.getInstance().login(userDto);
            new EventFrame(id);
            this.dispose();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Username or password is incorrect");
        }
        textField1.setText("");
        passwordField1.setText("");
    }

    private void registration() {
        String username = textField1.getText();
        String password = new String(passwordField1.getPassword());
        UserDto userDto = new UserDto(username, password);
        try {
            UserController.getInstance().registration(userDto);
            JOptionPane.showMessageDialog(null, "The user has been registered");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Username already exists");
        }
        textField1.setText("");
        passwordField1.setText("");
    }

}
