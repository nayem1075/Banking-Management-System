package com.mycompany.banking_management_system;

// Class for Security Management, handling login functionality
public class SecurityManager {

     // Method to validate user login
    public boolean login(UserInformation userInformation, String inputPassword) {

        return userInformation.getPassword().equals(inputPassword);

    }
}
