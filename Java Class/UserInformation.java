
package com.mycompany.banking_management_system;

public class UserInformation {
    
    private String userId,userName,passwordl;
    
    //Constructor
     public UserInformation(String userId, String userName, String password){
        
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

     //Setter getter method to access Private variable.
     public void setUserId(String userId){
         this.userId = userId;
     }
     public String getUserId(){
         return userId;
     } 
     
     public void setUserName(String userName){
         this.userName = userName;
     }
     public String getUserName(){
         return userName;
     }
     
     public void setPassword(String password){
         this.password = password;
     }
     public String getPassword(){
         return password;
     }
    
     //Method
    public void displayInformation(){
        System.out.println("User ID : "+userId);
        System.out.println("User Name : "+userName);
        System.out.println();
    }
}
