package com.mycompany.banking_management_system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// Class to handle data storage for Accounts and Transactions
public class DataStorage {

    private ArrayList<Account> accounts = new ArrayList<Account>();// List to store accounts
    private ArrayList<Transaction> transactions = new ArrayList<>();// List to store transactions

    // Method to save an account to the storage
    public void saveAccount(Account account) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt", true))) {
            writer.write(account.getAccountId() + ","
                    + account.getAccountType() + ","
                    + account.getBalance() + ","
                    + account.getUserInformation().getUserId() + ","
                    + account.getUserInformation().getUserName() + ","
                    + account.getUserInformation().getPassword());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing account: " + e.getMessage());
        }
        accounts.add(account);// Add account to the list
    }

    //Method to save an transaction to the storage
public void saveTransaction(Transaction transaction) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.txt", true))) {
        // Writing transaction details to file
        writer.write(transaction.getTransactionType() + ","
                + transaction.getAmount() + ","
                + transaction.getAccount().getAccountId());
        writer.newLine(); // Adding a new line after the transaction
    } catch (IOException e) {
        System.out.println("Error writing transaction: " + e.getMessage());
    }
    
    // Add transaction to the in-memory list
    transactions.add(transaction); // Adding transaction to the list
}

// Method to get all accounts stored from file
    public ArrayList<Account> getAccounts() {
        ArrayList<Account> accountsFromFile = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("accounts.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Account account = parseAccount(line);
                accountsFromFile.add(account);
            }
        } catch (IOException e) {
            System.out.println("Error reading accounts file: " + e.getMessage());
        }
        return accountsFromFile;
    }

// Method to get all transactions stored from file
    public ArrayList<Transaction> getTransactions() {
        ArrayList<Transaction> transactionsFromFile = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("transactions.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Transaction transaction = parseTransaction(line);
                transactionsFromFile.add(transaction);
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions file: " + e.getMessage());
        }
        return transactionsFromFile;
    }

    private Account parseAccount(String accountString) {
        String[] data = accountString.split(",");
        String accountId = data[0];
        String accountType = data[1];
        double balance = Double.parseDouble(data[2]);

        // Extracting UserInformation
        String userId = data[3];
        String userName = data[4];
        String password = data[5];

        // Creating UserInformation object
        UserInformation userInformation = new UserInformation(userId, userName, password);

        // Returning Account object with parsed data
        return new Account(accountId, accountType, balance, userInformation);
    }

    private Transaction parseTransaction(String transactionString) {
        String[] data = transactionString.split(",");
        String transactionType = data[0];
        double amount = Double.parseDouble(data[1]);

        // Extracting Account information
        String accountId = data[2];
        String accountType = ""; // Assuming you don't have this in the transaction file
        double balance = 0.0; // Assuming balance is not in the transaction data

        UserInformation userInformation = new UserInformation("UserId", "UserName", "Password");

        // Creating Account object for the transaction
        Account account = new Account(accountId, accountType, balance, userInformation);

        // Returning Transaction object with parsed data
        return new Transaction(transactionType, amount, account);

    }
    
    public void updateAccountInFile(Account updatedAccount) {
    ArrayList<Account> allAccounts = getAccounts();

    try (BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt"))) {
        for (Account account : allAccounts) {
            if (account.getAccountId().equals(updatedAccount.getAccountId())) {
                // Replace with updated balance
                account = updatedAccount;
            }
            writer.write(account.getAccountId() + "," 
                    + account.getAccountType() + "," 
                    + account.getBalance() + "," 
                    + account.getUserInformation().getUserId() + "," 
                    + account.getUserInformation().getUserName() + "," 
                    + account.getUserInformation().getPassword());
            writer.newLine();
        }
    } catch (IOException e) {
        System.out.println("Error updating account: " + e.getMessage());
    }
}
    
    // Method to display all accounts in storage

    public void displayAllAccounts() {
        System.out.println("\n====== All Accounts Summary ======");
        if (accounts.isEmpty()) {
            for (Account account : accounts) {
                account.displayAccountInformation();
            }
        } else {
            System.out.println("No accounts to display.");
        }
    }

 public void displayAllTransactions() {
    System.out.println("\n====== All Transactions Summary ======");

    if (transactions.isEmpty()) {
        System.out.println("No transactions to display.");
    } else {
        for (Transaction transaction : transactions) {
            transaction.displayTransactionInformation();
        }
    }
 }

}
