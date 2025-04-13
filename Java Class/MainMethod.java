package com.mycompany.banking_management_system;

import java.util.Scanner;

// Main class to interact with the user and provide the banking functionalitie
public class BankingManagementSystemMainMethod {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

         // Initialize the data storage and bank objects
        DataStorage dataStorage = new DataStorage();
        Bank bank = new Bank(dataStorage);

        // Display welcome message
        System.out.println("========================================");
        System.out.println("    Welcome to the Banking System    ");
        System.out.println("========================================");

        // Main menu for user interaction
        while (true) {
            System.out.println("\n1. Create New Account\n2. Login to Existing Account\n3. Show All Accounts\n4. Exit");
            System.out.print("Choose an option: ");
            int mainChoice = input.nextInt();
            input.nextLine();

            switch (mainChoice) {
                case 1:
                    // Create a new account
                    System.out.print("Enter User ID: ");
                    String userID = input.nextLine();
                    System.out.print("Enter User Name: ");
                    String userName = input.nextLine();
                    System.out.print("Enter Password: ");
                    String password = input.nextLine();
                    UserInformation userInformation = new UserInformation(userID, userName, password);

                    System.out.print("Enter Account ID: ");
                    String accountID = input.nextLine();
                    System.out.print("Enter Account Type (Savings/Current): ");
                    String accountType = input.nextLine();
                    double balance = 0.0;
                    Account accounts = new Account(accountID, accountType, balance, userInformation);

                    dataStorage.saveAccount(accounts);// Create the account and save it to storage
                    System.out.println("Account successfully created!");
                    break;

                case 2:
                    // User login to an existing account
                    System.out.print("Enter User ID: ");
                    String loginUserID = input.nextLine();
                    System.out.print("Enter Password: ");
                    String loginPassword = input.nextLine();

                    // Find the account based on user ID
                    boolean found = false;
                    for (Account account : dataStorage.getAccounts()) {
                        if (account.getUserInformation().getUserId().equals(loginUserID)
                                && account.getUserInformation().getPassword().equals(loginPassword)) {
                            found = true;
                            System.out.println("Login successful!");

                            while (true) {

                                System.out.println("\n1. Deposit\n2. Withdraw\n3. Show Account Information\n4. Exit Account");
                                System.out.print("\nChoose an option : ");

                                int loginChoice;
                                try {
                                    loginChoice = input.nextInt();
                                } catch (Exception e) {
                                    System.out.println(e);
                                    System.out.println("Invalid choice!. Please enter a valid option.");
                                    input.nextLine();
                                    continue;
                                }
                                System.out.println();
                                switch (loginChoice) {

                                    case 1: 
                        
                        try {

                                        System.out.print("Enter deposit amount : ");
                                        double depositAmount = input.nextDouble();

                                        if (depositAmount < 0) {
                                            throw new IllegalArgumentException("Deposit amount can't be negative.");
                                        }
                                        account.deposit(depositAmount);
                                    } catch (Exception e) {
                                        System.out.println(e);
                                        System.out.println("Invalid input.Please enter a valid deposit amount.");
                                        input.nextLine();
                                    }
                                    break;

                                    case 2: 
                        
                        try {

                                        System.out.print("Enter withdraw amount : ");
                                        double withdrawAmount = input.nextDouble();

                                        if (withdrawAmount < 0) {
                                            throw new IllegalArgumentException("Withdrawal amount can't be negative.");
                                        }
                                        account.withdraw(withdrawAmount);
                                    } catch (Exception e) {
                                        System.out.println(e);
                                        System.out.println("Invalid input.Please enter a valid withdraw amount.");
                                        input.nextLine();
                                    }
                                    break;

                                    case 3:
                                        // Show all accounts in the system
                                        account.displayAccountInformation();
                                        break;
                                    case 4:
                                        // Exit the program
                                        System.out.println("Exit Account.");
                                        input.nextLine();
                                        break;
                                    default:
                                        System.out.println("Invalid choice!.Please enter a valid option. ");
                                        break;
                                }
                                if (loginChoice == 4) {
                                    break;
                                }
                            }
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Login failed.Try again.");
                    }
                    break;

                case 3:
                    System.out.println("\n====== All Accounts ======");
                    for (Account account : dataStorage.getAccounts()) {
                        System.out.println("User ID: " + account.getUserInformation().getUserId()
                                + ", Account ID: " + account.getAccountId());
                    }
                    break;

                case 4:
                    System.out.println("Exiting the Banking Management System.");
                    System.out.println("Thank you for using the Banking Management System.");
                    input.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
