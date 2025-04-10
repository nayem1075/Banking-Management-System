
package com.mycompany.banking_management_system;

public class Account {
    
    private String accountId,accountType;
    private double balance;
    private UserInformation userInformation; //has a relationship with UserInformation.
    
    public Account (String accountId, String accountType, double balance, UserInformation userInformation){
        
        this.accountId =  accountId;
        this.accountType = accountType;
        this.balance =  balance;
        this.userInformation =  userInformation;
    }
    
    public void setAccountId(String accountId){
        this.accountId = accountId;
    }
    public String getAccountId(){
        return accountId;
    }    
    
    public void setAccountType(String accountType){
        this.accountType = accountType;
    }
    public String getAccountType(){
        return accountType;
    }
    
    public void setBalance(double balance){
        this.balance = balance;
    }
    public double getBalance(){
        return balance;
    }

    public void deposit(double amount){
        
        if(amount>0){
            balance = balance + amount;
            System.out.println("Deposited : "+amount);
        }
        else{
            System.out.println("Invalid amount");
        }
    } 
    
    public void withdraw(double amount){
        
        if(amount>0 && amount<=balance){
            balance = balance - amount;
            System.out.println("Withdrew : "+amount);
        }
        else{
            System.out.println("Invalid amount or Insufficient balance");
        }
    }
    
    public void displayAccountInformation(){
        System.out.println("Account ID : "+accountId);
        System.out.println("Account Type : "+accountType);
        System.out.println("Balance : "+balance);
        System.out.println("\n\nUser Information : ");
        userInformation.displayInformation();//Display User Information from UserInformation class
    }
    
}
