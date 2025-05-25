package com.mycompany.bankingmanagementsystem;

import javax.swing.*;
import java.awt.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class LoginFrame extends JFrame {
    private JTextField userIdField;
    private JPasswordField passwordField;
    private DataStorage dataStorage;

    public LoginFrame() {
        dataStorage = new DataStorage();

        setTitle("Login - Banking System");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel userIdLabel = new JLabel("User ID:");
        JLabel passwordLabel = new JLabel("Password:");

        userIdField = new JTextField(15);
        passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");
        JButton createAccountButton = new JButton("Create Account");

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.add(userIdLabel);
        panel.add(userIdField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(createAccountButton);

        add(panel);

        loginButton.addActionListener(e -> login());
        createAccountButton.addActionListener(e -> {
            dispose();
            new CreateAccountFrame();
        });

        setVisible(true);
    }

    private void login() {
        String userId = userIdField.getText().trim();
        String password = new String(passwordField.getPassword());

        for (Account account : dataStorage.getAccounts()) {
            if (account.getUserInformation().getUserId().equals(userId)
                    && account.getUserInformation().getPassword().equals(password)) {
                JOptionPane.showMessageDialog(this, "Login Successful");

                this.setVisible(false); // hide this frame instead of disposing
                new DashboardFrame(this,account, dataStorage); // pass this
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Invalid credentials!");
    }
}
