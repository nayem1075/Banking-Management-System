
package com.mycompany.bankingmanagementsystem;


import java.util.Random;
import java.util.Scanner;

public class MainMethod {

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
                    System.out.print("Enter Your Birth Year (yyyy): ");
                    String birthYear = input.nextLine();

                    // Validate birth year (must be 4 digits)
                    while (birthYear.length() != 4 || !birthYear.matches("\\d{4}")) {
                        System.out.println("❌ Invalid year! Please enter a valid 4-digit birth year.");
                        System.out.print("Enter Your Birth Year (yyyy): ");
                        birthYear = input.nextLine();
                    }

                    // Generate a 6-digit number as user ID first part (100000 - 999999)
                    Random random = new Random();
                    int firstPart = random.nextInt(900000) + 100000;  // Generates a number between 100000 and 999999

                    // Create User ID: Combine the 6-digit number with last 4 digits of birth year
                    String userID = firstPart + birthYear; // Take last 4 digits of the birth year (substring(2))

                    System.out.println("Generated User ID: " + userID);

                    System.out.print("Enter User Name (must be 3 to 20 characters): ");
                    String userName = input.nextLine();

                    while (userName.length() < 3 || userName.length() > 20) {
                        System.out.println("❌ Invalid username! It must be between 3 to 20 characters.");
                        System.out.print("Enter User Name again: ");
                        userName = input.nextLine();
                    }

                    System.out.print("Enter Password. (Password must be between 8-16 character and use at least one upper or lower character.): ");
                    String password = input.nextLine().trim();
                    while (password.length() < 8 || password.length() > 16 || !password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*")) {
                        System.out.println("❌ Invalid password! It must be 8-16 characters long and contain at least one letter (upper or lower case).");
                        System.out.print("Enter Password again: ");
                        password = input.nextLine().trim();
                        System.out.println("Entered password: '" + password + "'");
                    }

                    System.out.println("Password accepted.");

                    UserInformation userInformation = new UserInformation(userID, userName, password);

                    System.out.print("Enter Account ID: ");
                    String accountID = input.nextLine();

                    System.out.println("Select Account Type:\n1. Savings\n2. Current");
                    int typeChoice = input.nextInt();
                    input.nextLine(); // consume newline

                    String accountType;
                    switch (typeChoice) {
                        case 1:
                            accountType = "Savings";
                            break;
                        case 2:
                            accountType = "Current";
                            break;
                        default:
                            System.out.println("Invalid choice. Defaulting to Savings.");
                            accountType = "Savings";
                            break;
                    }
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

                            String otp = generateOTP();
                            System.out.println("An OTP has been generated. Please enter the OTP to proceed.");

                            System.out.println("Generated OTP: " + otp); // For testing only

                            System.out.print("Enter OTP: ");
                            String enteredOTP = input.nextLine();

                            // Step 2: Verify OTP
                            if (enteredOTP.equals(otp)) {
                                System.out.println("OTP verified successfully!");

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
                                                account.deposit(depositAmount); // Perform the deposit on the account

                                                // Create the Transaction object
                                                String transactionType = "Deposit";
                                                Transaction depositTransaction = new Transaction(transactionType, depositAmount, account);

                                                // Save the transaction
                                                dataStorage.saveTransaction(depositTransaction);
                                                dataStorage.updateAccountInFile(account);
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

                                                // Create the Transaction object
                                                String transactionType = "Withdraw";
                                                Transaction withdrawTransaction = new Transaction(transactionType, withdrawAmount, account);

                                                // Save the transaction
                                                dataStorage.saveTransaction(withdrawTransaction);
                                                 dataStorage.updateAccountInFile(account);
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
                            } else {
                                System.out.println("❌ OTP Verification Failed. Try again.");
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
    // Generate a 6-digit OTP

    public static String generateOTP() {
        Random random = new Random();
        int otp = random.nextInt(900000) + 100000; // Generates a 6-digit OTP
        return String.valueOf(otp);
    }
}

