package com.mycompany.bankingmanagementsystem;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {
    private Account account;
    private DataStorage dataStorage;
    private JFrame parentFrame;  // Reference to previous frame (LoginFrame)

    private JLabel balanceLabel;
    private JTextField depositField, withdrawField;
    private JButton depositButton, withdrawButton;

    public DashboardFrame(JFrame parentFrame, Account account, DataStorage dataStorage) {
        this.parentFrame = parentFrame;
        this.account = account;
        this.dataStorage = dataStorage;

        setTitle("Dashboard");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel welcome = new JLabel("Welcome, " + account.getUserInformation().getUserName());
        balanceLabel = new JLabel("Balance: $" + account.getBalance());

        depositField = new JTextField(10);
        depositButton = new JButton("Deposit");

        withdrawField = new JTextField(10);
        withdrawButton = new JButton("Withdraw");

        // Deposit action
        depositButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(depositField.getText());
                if (amount > 0) {
                    account.deposit(amount);
                    dataStorage.saveAccount(account); // Save data after deposit
                    updateBalance();
                    JOptionPane.showMessageDialog(this, "Deposit successful!");
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter a positive amount.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number entered.");
            }
        });

        // Withdraw action
        withdrawButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(withdrawField.getText());
                if (amount > 0 && amount <= account.getBalance()) {
                    account.withdraw(amount);
                    dataStorage.saveAccount(account); // Save data after withdraw
                    updateBalance();
                    JOptionPane.showMessageDialog(this, "Withdraw successful!");
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient balance or invalid amount.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number entered.");
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            this.dispose();            // Close DashboardFrame
            parentFrame.setVisible(true);  // Show LoginFrame again
        });

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));  // 6 rows, 2 columns
        panel.add(welcome);
        panel.add(new JLabel());

        panel.add(balanceLabel);
        panel.add(new JLabel());

        panel.add(new JLabel("Deposit Amount:"));
        panel.add(depositField);
        panel.add(depositButton);

        panel.add(new JLabel("Withdraw Amount:"));
        panel.add(withdrawField);
        panel.add(withdrawButton);

        panel.add(new JLabel());  // Empty cell for spacing
        panel.add(backButton);

        add(panel);
        setVisible(true);
    }

    private void updateBalance() {
        balanceLabel.setText("Balance: $" + account.getBalance());
    }
}
