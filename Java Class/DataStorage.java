package com.mycompany.banking_management_system;

import java.util.ArrayList;


// Class to handle data storage for Accounts and Transactions
public class DataStorage {

    private ArrayList<Account> accounts = new ArrayList<Account>();// List to store accounts
    private ArrayList<Transaction> transactions = new ArrayList<>();// List to store transactions

     // Method to save an account to the storage
    public void saveAccount(Account account) {

        accounts.add(account);// Add account to the list
    }

    // Method to save a transaction to the storage
    public void saveTransaction(Transaction transaction) {
        transactions.add(transaction);// Add transaction to the list
    }

     // Method to get all accounts stored
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    // Method to get all transactions stored
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

     // Method to display all accounts in storage
    public void displayAllAccounts() {
        System.out.println("\n====== All Accounts Summary ======");
        if (accounts.isEmpty()) {
            System.out.println("No accounts to display.");
        } else {
            for (Account account : accounts) {
                account.displayAccountInformation();
            }
        }
    }

}
