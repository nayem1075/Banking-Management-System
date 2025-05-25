
package com.mycompany.bankingmanagementsystem;

// Class to store user information like User ID, Name, and Password
public class UserInformation {

    private String userId, userName, password;

       // Constructor to initialize UserInformation
    public UserInformation(String userId, String userName, String password) {

        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    // Setter and Getter methods to access private variables
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    // Method to display User Information
    public void displayInformation() {
        System.out.println("User ID : " + userId);
        System.out.println("User Name : " + userName);
        System.out.println();
    }
}

