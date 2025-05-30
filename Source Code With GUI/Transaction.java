
package com.mycompany.bankingmanagementsystem;


// Class to handle Transaction details like Transaction ID, Type, Amount, and Account
public class Transaction {

    private String transactionType;
    private double amount;
    private Account account;

    // Constructor to initialize Transaction details
    public Transaction(String transactionType, double amount, Account account) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.account = account;
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
        System.out.println("Transaction Type : " + transactionType);
        System.out.println("Amount: " + amount);
    }
}
