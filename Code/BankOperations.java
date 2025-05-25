
package com.mycompany.bankingmanagementsystem;

public interface BankOperations {
    
    
    void createAccount(Account account);
    void findAccount(String accountId);
    void processTransaction(Transaction transaction);
    
}
