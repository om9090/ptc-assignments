package com.example;

import java.util.ArrayList;

/**
 * TransactionManager class is the class which will be used to manage transaction operations.
 * It contains methods to deposit, withdraw, transfer amount, check balance and 
 * display transaction history.
 *
 * @version 1.0 24 Jun 2024
 * @author Om Deshmukh
 */

public class TransactionManager {
    // Bank object to access bank details
    private Bank bank;

    // Constructor to initialize Bank object
    public TransactionManager(Bank bank) {
        this.bank = bank;
    }

    // Method to deposit amount
    // param 1 amount: amount to deposit
    // param 2 accountNumber: account number to deposit amount
    public void deposit(int amount, int accountNumber) {
        try {
            // Check if account number is valid
            if (!bank.getAccountDetails().containsKey(accountNumber)) {
                throw new Exception("Invalid account number");
            }
            if (amount <= 0) {
                throw new Exception("Amount should be greater than 0");
            }
            // Get current balance and add deposited amount
            int newBalance = bank.getAccountBalance().get(accountNumber) + amount;

            // Update account balance
            bank.getAccountBalance().put(accountNumber, newBalance);

            // Update transaction history
            String transaction = "+" + amount + "," + newBalance + "," + java.time.LocalDate.now();
            bank.getTransactionHistory().get(accountNumber).add(transaction);
            System.out.println("Amount deposited successfully");
        } catch (Exception e) {
            System.out.println("Error in depositing amount: " + e.getMessage());
        }
    }

    // Method to withdraw amount
    // param 1 amount: amount to withdraw
    // param 2 accountNumber: account number to withdraw amount
    public void withdraw(int amount, int accountNumber) {
        try {
            if (!bank.getAccountDetails().containsKey(accountNumber)) {
                throw new Exception("Invalid account number");
            }
            if (amount <= 0) {
                throw new Exception("Amount should be greater than 0");
            }

            // Get current balance and check if sufficient balance is available
            int currentBalance = bank.getAccountBalance().get(accountNumber);

            // If balance is less than amount to withdraw, throw exception
            if (currentBalance < amount) {
                throw new Exception("Insufficient balance");
            }

            // Update account balance
            int newBalance = currentBalance - amount;
            bank.getAccountBalance().put(accountNumber, newBalance);

            //  Update transaction history
            String transaction = "-" + amount + "," + newBalance + "," + java.time.LocalDate.now();
            bank.getTransactionHistory().get(accountNumber).add(transaction);
            System.out.println("Amount withdrawn successfully");
        } catch (Exception e) {
            System.out.println("Error in withdrawing amount: " + e.getMessage());
        }
    }

    // Method to transfer amount from one account to another
    public void bankToBankTransfer(int senderAccountNumber, int receiverAccountNumber, int amount) {
        try {
            // Check if account numbers are valid
            if (!bank.getAccountDetails().containsKey(senderAccountNumber)) {
                throw new Exception("Invalid account number");
            }
            
            if (!bank.getAccountDetails().containsKey(receiverAccountNumber)) {
                throw new Exception("Invalid receiver account number");
            }

            // Check if amount is valid
            if (amount <= 0) {
                throw new Exception("Amount should be greater than 0");
            }

            // Check if sender has sufficient balance
            if (bank.getAccountBalance().get(senderAccountNumber) < amount) {
                throw new Exception("Insufficient balance");
            }

            // Update account balances 
            int senderNewBalance = bank.getAccountBalance().get(senderAccountNumber) - amount;
            int receiverNewBalance = bank.getAccountBalance().get(receiverAccountNumber) + amount;
            bank.getAccountBalance().put(senderAccountNumber, senderNewBalance);
            bank.getAccountBalance().put(receiverAccountNumber, receiverNewBalance);

            // Update transaction history for sender
            String senderTransaction = "-" + amount + "," + senderNewBalance + "," + java.time.LocalDate.now();
            bank.getTransactionHistory().get(senderAccountNumber).add(senderTransaction);
            
            // Update transaction history for receiver
            String receiverTransaction = "+" + amount + "," + receiverNewBalance + "," + java.time.LocalDate.now();
            bank.getTransactionHistory().get(receiverAccountNumber).add(receiverTransaction);
            System.out.println("Amount transferred successfully");
        } catch (Exception e) {
            System.out.println("Error in transferring amount: " + e.getMessage());
        }
    }

    // Method to check balance
    // param accountNumber: account number to check balance
    public void checkBalance(int accountNumber) {
        try {
            if (!bank.getAccountDetails().containsKey(accountNumber)) {
                throw new Exception("Invalid account number");
            }
            // Get current balance and display
            int balance = bank.getAccountBalance().get(accountNumber);
            System.out.println("Current balance: " + balance);
        } catch (Exception e) {
            System.out.println("Error in checking balance: " + e.getMessage());
        }
    }

    // Method to display transaction history
    // param accountNumber: account number to display transaction history
    public void displayTransactionHistory(int accountNumber) {
        try {
            // Check if account number is valid
            if (!bank.getAccountDetails().containsKey(accountNumber)) {
                throw new Exception("Invalid account number");
            }

            // Get transaction history and display
            ArrayList<String> history = bank.getTransactionHistory().get(accountNumber);
            for (String transaction : history) {
                String[] details = transaction.split(",");
                System.out.println("Amount: " + details[0] + ", Balance: " + details[1] + ", Date: " + details[2]);
            }
        } catch (Exception e) {
            System.out.println("Error in displaying transaction history: " + e.getMessage());
        }
    }

}
