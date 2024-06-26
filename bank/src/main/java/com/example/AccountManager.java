package com.example;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * AccountManager class is the class which will be used to manage account CRUD operations.
 * It contains methods to create, update, delete and display account details.
 * It also contains a method to set account details.
 *
 * @version 1.0 24 Jun 2024
 * @author Om Deshmukh
 */

public class AccountManager {
    // Bank object to access bank details
    private Bank bank;
    private Scanner sc = new Scanner(System.in);

    // Constructor to initialize Bank object
    public AccountManager(Bank bank) {
        this.bank = bank;
    }

    // Method to create account
    public void createAccount() {
        try {
            // Get account details
            String[] detailStrings = setAccountDetails();

            // Generate account id
            int accountId = bank.generateAccountId();

            // Add account details to the map (account id as key and account details as value)
            bank.getAccountDetails().put(accountId, detailStrings);

            // Add account balance to the map (account id as key and balance initial value 0)
            bank.getAccountBalance().put(accountId, 0);

            // Add transaction history to the map (account id as key and empty list as value)
            bank.getTransactionHistory().put(accountId, new ArrayList<String>());

            System.out.println("Account Number: " + accountId);     // Display account number
            System.out.println("Account created successfully");     
        } catch (Exception e) {
            System.out.println("Error in creating account: " + e.getMessage());
        }
    }

    // Method to update account details 
    // param accountNumber: account number to update details
    public void updateAccountDetails(int accountNumber) {
        try {
            // Check if account number is valid
            if (!bank.getAccountDetails().containsKey(accountNumber)) {
                throw new Exception("Invalid account number");
            }

            // Get new account details
            String[] detailStrings = setAccountDetails();

            // Update account details in the map
            bank.getAccountDetails().put(accountNumber, detailStrings);
            System.out.println("Account details updated successfully");
        } catch (Exception e) {
            System.out.println("Error in updating account details: " + e.getMessage());
        }
    }

    // Method to delete account
    // param accountNumber: account number to delete
    public void deleteAccount(int accountNumber) {
        try {
            // Check if account number is valid
            if (!bank.getAccountDetails().containsKey(accountNumber)) {
                throw new Exception("Invalid account number");
            }

            // Remove account details, balance and transaction history from the map
            bank.getAccountDetails().remove(accountNumber);
            bank.getAccountBalance().remove(accountNumber);
            bank.getTransactionHistory().remove(accountNumber);
            System.out.println("Account deleted successfully");
        } catch (Exception e) {
            System.out.println("Error in deleting account: " + e.getMessage());
        }
    }

    // Method to display account details
    public void displayAccountDetails(int accountNumber) {
        try {
            // Check if account number is valid
            if (!bank.getAccountDetails().containsKey(accountNumber)) {
                throw new Exception("Invalid account number");
            }
            // Get account details from the map and display
            String[] details = bank.getAccountDetails().get(accountNumber);
            System.out.println("Name: " + details[0]);
            System.out.println("Age: " + details[1]);
            System.out.println("Address: " + details[2]);
            System.out.println("Phone: " + details[3]);
            System.out.println("Account details displayed successfully");
        } catch (Exception e) {
            System.out.println("Error in displaying account details: " + e.getMessage());
        }
    }

    // Method to set account details
    private String[] setAccountDetails() {
        String[] account = new String[4];
        try {

            // Method 1: Using BufferedReader
            // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            // // Taking string input first
            // System.out.println("Enter your name: ");
            // String name = reader.readLine();

            // // Taking integer input
            // System.out.println("Enter your age: ");
            // int age = Integer.parseInt(reader.readLine());

            // Method 2
            System.out.println("Enter your name:");
            String name = sc.nextLine();

            System.out.println("Enter your age:");
            int age = Integer.parseInt(sc.nextLine());      // Another way to get int after string
            if (age < 10 || age > 100) {
                throw new Exception("Age should be between 10 and 100");
            }

            // Method 3
            // System.out.println("Enter your name:");
            // String name = sc.nextLine();

            // System.out.println("Enter your age:");
            // int age = sc.nextInt();
            // sc.nextLine();    // To consume the newline character

            System.out.println("Enter your address:");
            String address = sc.nextLine();
            System.out.println("Enter your phone number:");
            long phone = sc.nextLong();
            if (String.valueOf(phone).length() != 10) {
                throw new Exception("Phone number should be of 10 digits");
            }
            account[0] = name;
            account[1] = String.valueOf(age);
            account[2] = address;
            account[3] = String.valueOf(phone);
        } catch (Exception e) {
            System.out.println("Error in getting account details: " + e.getMessage());
        }
        return account;
    }
}
