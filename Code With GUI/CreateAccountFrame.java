
package com.mycompany.bankingmanagementsystem;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import java.awt.GridLayout;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class CreateAccountFrame extends JFrame {
    private JTextField nameField, birthYearField, accountIdField;
    private JPasswordField passwordField;
    private JComboBox<String> accountTypeBox;
    private DataStorage dataStorage;

    public CreateAccountFrame() {
        dataStorage = new DataStorage();

        setTitle("Create Account");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel nameLabel = new JLabel("User Name:");
        JLabel birthYearLabel = new JLabel("Birth Year:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel accountIdLabel = new JLabel("Account ID:");
        JLabel accountTypeLabel = new JLabel("Account Type:");

        nameField = new JTextField(15);
        birthYearField = new JTextField(4);
        passwordField = new JPasswordField(16);
        accountIdField = new JTextField(10);
        accountTypeBox = new JComboBox<>(new String[]{"Savings", "Current"});

        JButton createButton = new JButton("Create");

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(birthYearLabel);
        panel.add(birthYearField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(accountIdLabel);
        panel.add(accountIdField);
        panel.add(accountTypeLabel);
        panel.add(accountTypeBox);
        panel.add(createButton);

        add(panel);

        createButton.addActionListener(e -> createAccount());

        setVisible(true);
    }

    private void createAccount() {
        String name = nameField.getText().trim();
        String birthYear = birthYearField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String accountId = accountIdField.getText().trim();
        String accountType = accountTypeBox.getSelectedItem().toString();

        if (name.length() < 3 || birthYear.length() != 4 || password.length() < 8) {
            JOptionPane.showMessageDialog(this, "Invalid input!");
            return;
        }

        int randomNum = new Random().nextInt(900000) + 100000;
        String userId = randomNum + birthYear;

        UserInformation userInfo = new UserInformation(userId, name, password);
        Account newAccount = new Account(accountId, accountType, 0.0, userInfo);

        dataStorage.saveAccount(newAccount);

        JOptionPane.showMessageDialog(this, "Account Created! Your User ID: " + userId);
        dispose();
        new LoginFrame();
    }
}
