
package com.mycompany.bankingmanagementsystem;



import java.util.ArrayList;

// Class to manage the Bank with Account and Transaction functionalities


public class Bank implements BankOperations{

    private ArrayList<Account> accounts = new ArrayList<Account>();// List to store Accounts
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();// List to store Transactions

    private DataStorage dataStorage;// To store data persistently

     // Constructor to initialize Bank with DataStorage
    public Bank(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

      // Method to create a new account
    @Override
    public void createAccount(Account account) {

        // Check if account already exists
        for (Account existingAccount : accounts) {
            if (existingAccount.getAccountId().equals(account.getAccountId())) {
                System.out.println("Account with this ID already exists!");
                return; // Exit if account already exists
            }
        }

        accounts.add(account);// Add account to the list
        System.out.println("Account created successfully for ID: " + account.getAccountId());
        dataStorage.saveAccount(account);// Save account to data storage
    }

     // Method to find and display an account by accountId
 
    @Override
    public void findAccount(String accountId) {
        boolean found = false;
        for (Account account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                System.out.println("Account found.");
                account.displayAccountInformation();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Account not found!");
        }
    }

    // Method to display all accounts in the bank
    public void showAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found!");
        } else {
            for (Account account : accounts) {
                account.displayAccountInformation();
            }
        }
    }

    // Method to process a transaction and save it to storage
   
    @Override
    public void processTransaction(Transaction transaction) {
        transactions.add(transaction);
        dataStorage.saveTransaction(transaction);
    }

     // Method to display transaction history
    public void showTransactionHistory() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (Transaction transaction : transactions) {
                transaction.displayTransactionInformation();
            }
        }

    }
}

