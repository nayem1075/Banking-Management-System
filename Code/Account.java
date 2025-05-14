package com.mycompany.banking_management_system;

// Class to manage Account details like Account ID, Account Type, Balance, and UserInformation
public class Account {

    private String accountId, accountType;
    private double balance;
    private UserInformation userInformation; //has a relationship with UserInformation.

    // Constructor to initialize Account details
    public Account(String accountId, String accountType, double balance, UserInformation userInformation) {

        this.accountId = accountId;
        this.accountType = accountType;
        this.balance = balance;
        this.userInformation = userInformation;
    }

    // Getter and Setter methods for Account
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public UserInformation getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
    }

    // Method to deposit an amount into the account
    public void deposit(double amount) {

        balance = balance + amount;
        System.out.println("Deposited : " + amount);
    }

    // Method to withdraw an amount from the account
    public void withdraw(double amount) {

        if (amount <= balance) {
            balance = balance - amount;
            System.out.println("Withdrew : " + amount);
        } else {
            System.out.println("Invalid amount or Insufficient balance");
        }
    }

    // Method to display Account Information
    public void displayAccountInformation() {
        System.out.println("Account ID : " + accountId);
        System.out.println("Balance : " + balance);
        System.out.println("\n\nUser Information");
        userInformation.displayInformation();// Displaying user info from UserInformation class
    }
}
