
package com.mycompany.banking_management_system;

public class UserInformation {
    
    private String userId,userName,password,email;
    
    //Constructor
     public UserInformation(String userId, String userName, String password, String email){
        
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email =  email;
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
     
     public void setEmail(String email){
         this.email = email;
     }
     public String getEmail(){
         return email;
     }
    
     //Method
    public void displayInformation(){
        System.out.println("User ID : "+userId);
        System.out.println("User Name : "+userName);
        System.out.println("Password : "+password);
        System.out.println("Email : "+email);
        System.out.println();
    }
}
