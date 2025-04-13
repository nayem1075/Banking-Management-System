package com.mycompany.banking_management_system;

// Class to handle Transaction details like Transaction ID, Type, Amount, and Account
public class Transaction {

    private String transactionId, transactionType;
    private double amount;
    private Account account;

    // Constructor to initialize Transaction details
    public Transaction(String transactionId, String transactionType, double amount, Account account) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.account = account;
    }

    // Getter and Setter methods for Transaction
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

     // Method to display Transaction Information
    public void displayTransactionInformation() {
        System.out.println("Transaction ID : " + transactionId);
        System.out.println("Transaction Type : " + transactionType);
        System.out.println("Amount: " + amount);
    }
}
