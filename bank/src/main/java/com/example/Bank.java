/**
 * %W% %E% Om Deshmukh
 *
 * Copyright (c) 1993-1996 XYZ, Inc. All Rights Reserved.
 * 
 * PURPOSE OF THE FOLLOWING PROGRAM IS TO IMPLEMENT A BANKING SYSTEM
 * WHICH ALLOWS USERS TO CREATE ACCOUNTS, DEPOSIT, WITHDRAW, TRANSFER
 * MONEY BETWEEN ACCOUNTS, CHECK BALANCE, UPDATE ACCOUNT DETAILS, DELETE
 * ACCOUNTS, DISPLAY ACCOUNT DETAILS AND TRANSACTION HISTORY.
 *
 */
package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;

/**
 * Bank class is the main class which will be used to run the banking system.
 * It contains the main method which will be used to interact with the user
 * and perform operations based on the user's choice.
 *
 * @version 1.0 24 Jun 2024
 * @author Om Deshmukh
 */

public class Bank {
    // Scanner object to take input from the user
    private static Scanner sc = new Scanner(System.in);
    private Map<Integer, String[]> accountDetails = new HashMap<>();                // Map to store account details
    private Map<Integer, Integer> accountBalance = new HashMap<>();                 // Map to store account balance
    private Map<Integer, ArrayList<String>> transactionHistory = new HashMap<>();   // Map to store transaction history
    private AtomicInteger accountIdGenerator = new AtomicInteger(1);   // AtomicInteger to generate account id

    private AccountManager accountManager;       // AccountManager object to manage account operations
    private TransactionManager transactionManager;  // TransactionManager object to manage transaction operations

    public Bank() {
        // Constructor to initialize AccountManager and TransactionManager objects
        accountManager = new AccountManager(this);
        transactionManager = new TransactionManager(this);
    }

    // Method to get account number
    public static int getAccountNumber() {
        System.out.println("Enter your account number: ");
        return sc.nextInt();
    }

    // Return accountDetails map
    public Map<Integer, String[]> getAccountDetails() {
        return accountDetails;
    }

    // Return accountBalance map
    public Map<Integer, Integer> getAccountBalance() {
        return accountBalance;
    }

    // Return transactionHistory map
    public Map<Integer, ArrayList<String>> getTransactionHistory() {
        return transactionHistory;
    }

    // Method to generate account id
    public int generateAccountId() {
        return accountIdGenerator.getAndIncrement();
    }

    public static void main(String[] args) {
        // Main method to run the banking system
        Bank bank = new Bank();

        int option;         // Variable to store user choice
        int amount;         // Variable to store amount
        int accountNumber;  // Variable to store account number
        do {
            System.out.println("Welcome to the PTC Bank \n");
            System.out.println("1. Create Account ");
            System.out.println("2. Deposit ");
            System.out.println("3. Withdraw");
            System.out.println("4. Bank to Bank Transfer");
            System.out.println("5. Check Balance ");
            System.out.println("6. Update Account Details");
            System.out.println("7. Delete Account");
            System.out.println("8. Account Details");
            System.out.println("9. Transaction History");
            System.out.println("10. Exit \n");
            System.out.println("Enter your choice: ");
            option = sc.nextInt();

            // Switch case to perform operations based on user choice
            switch (option) {
                case 1:
                    bank.accountManager.createAccount();
                    break;
                case 2:
                    System.out.println("Enter the amount to deposit: ");
                    amount = sc.nextInt();
                    accountNumber = getAccountNumber(); // Get account number
                    bank.transactionManager.deposit(amount, accountNumber);
                    break;
                case 3:
                    System.out.println("Enter the amount to withdraw: ");
                    amount = sc.nextInt();
                    accountNumber = getAccountNumber(); // Get account number

                    bank.transactionManager.withdraw(amount, accountNumber);
                    break;
                case 4:
                    int senderAccountNumber = getAccountNumber(); // Get sender account number

                    System.out.println("Enter the account number to transfer: ");
                    int receiverAccountNumber = sc.nextInt(); // Get receiver account number

                    System.out.println("Enter the amount to transfer: ");
                    amount = sc.nextInt();
                    bank.transactionManager.bankToBankTransfer(senderAccountNumber, receiverAccountNumber, amount);
                    break;
                case 5:
                    // Check balance
                    accountNumber = getAccountNumber();
                    bank.transactionManager.checkBalance(accountNumber);
                    break;
                case 6:
                    // Update account details
                    accountNumber = getAccountNumber();
                    bank.accountManager.updateAccountDetails(accountNumber);
                    break;
                case 7:
                    // Delete account
                    accountNumber = getAccountNumber();
                    bank.accountManager.deleteAccount(accountNumber);
                    break;
                case 8:
                    // Display account details
                    accountNumber = getAccountNumber();
                    bank.accountManager.displayAccountDetails(accountNumber);
                    break;
                case 9:
                    // Display transaction history
                    accountNumber = getAccountNumber();
                    bank.transactionManager.displayTransactionHistory(accountNumber);
                    break;
                case 10:
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (option != 10);
        System.out.println("Thank you for using PTC Bank");
    }
}
