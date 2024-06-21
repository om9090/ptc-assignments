/**
 * %W% %E% Om Deshmukh
 *
 * Copyright (c) 1993-1996 XYZ, Inc. All Rights Reserved.
 * 
 * PURPOSE OF THE FOLLOWING PROGRAM IS TO IMPLEMENT A BANKING SYSTEM
 * WHICH WILL ALLOW USERS TO CREATE ACCOUNTS, DEPOSIT, WITHDRAW, TRANSFER
 * AMOUNTS, CHECK BALANCE, UPDATE ACCOUNT DETAILS, DELETE ACCOUNT, DISPLAY
 *
 */
import java.util.Scanner;
import java.util.Vector;

/**
 * Bank class is the main class which will be used to implement the banking system
 *
 * @version 1.0 20 Jun 2024
 * @author Om Deshmukh
 */

public class Bank {
    /* Account Number to be used in different methods */
    private int accountNumber;
    static Scanner sc = new Scanner(System.in);                 // Scanner object to take input from user
    Vector<String[]> accountDetails = new Vector<>();           // Vector to store account details
    Vector<Integer> accountBalance = new Vector<>();            // Vector to store account balance
    Vector<Vector<String>> transactionHistory = new Vector<>(); // Vector to store transaction history

    // Method to create account
    public void createAccount(){
        try{
            // Get account details
            String[] detailStrings = setAccountDetails();

            accountDetails.add(detailStrings);      // Add account details to the vector
            accountBalance.add(0);                  // Add initial balance to the vector
            transactionHistory.add(new Vector<String>()); // Add transaction history to the vector
            System.out.println("Account Number: " + accountDetails.size());
            System.out.println("Account created successfully");
        }
        catch(Exception e){
            System.out.println("Error in creating account");
        }
    }

    // Method to deposit amount
    public void deposit(int amount){
        try{
            accountNumber = getAccountNumber(); // Get account number

            if(accountNumber < 1 || accountNumber > accountDetails.size()){
                throw new Exception("Invalid account number");
            }
            if(amount < 0){
                throw new Exception("Amount should be greater than 0");
            }

            accountBalance.add(accountNumber - 1, accountBalance.get(accountNumber - 1) + amount);      // Update account balance
            String transaction = "+" + amount + "," + accountBalance.get(accountNumber - 1) + "," + java.time.LocalDate.now();
            transactionHistory.get(accountNumber - 1).add(transaction);                         // Update transaction history
        }
        catch(Exception e){
            System.out.println("Error in depositing amount");
        }
        System.out.println("Amount deposited successfully");
    }

    // Method to withdraw amount
    public void withdraw(int amount){
        try{
            accountNumber = getAccountNumber();  // Get account number

            if(accountNumber < 1 || accountNumber > accountDetails.size()){
                throw new Exception("Invalid account number");
            }
            if(amount < 0){
                throw new Exception("Amount should be greater than 0");
            }
            if(accountBalance.get(accountNumber - 1) < amount){
                throw new Exception("Insufficient balance");
            }

            accountBalance.add(accountNumber - 1, accountBalance.get(accountNumber - 1) - amount);    // Update account balance
            String transaction = "-" + amount + "," + accountBalance.get(accountNumber - 1) + "," + java.time.LocalDate.now();
            transactionHistory.get(accountNumber - 1).add(transaction);                        // Update transaction history
        }
        catch(Exception e){
            System.out.println("Error in withdrawing amount");
        }
        System.out.println("Amount withdrawn successfully");
    }

    // Method to transfer amount from one account to another
    public void bankToBankTransfer(){
        try{
            accountNumber = getAccountNumber(); // Get account number

            if(accountNumber < 1 || accountNumber > accountDetails.size()){
                throw new Exception("Invalid account number");
            }

            System.out.println("Enter the account number to transfer: ");
            int receiverAccountNumber = sc.nextInt();   // Get receiver account number

            if(receiverAccountNumber == accountNumber){
                throw new Exception("Cannot transfer to same account");
            }

            if(receiverAccountNumber < 1 || receiverAccountNumber > accountDetails.size()){
                throw new Exception("Invalid account number");
            }
            System.out.println("Enter the amount to transfer: ");
            int amount = sc.nextInt();
            if(amount < 0){
                throw new Exception("Amount should be greater than 0");
            }
            if(accountBalance.get(accountNumber - 1) < amount){
                throw new Exception("Insufficient balance");
            }

            // Update account balance of sender and receiver
            accountBalance.set(accountNumber - 1, accountBalance.get(accountNumber - 1) - amount);
            accountBalance.set(receiverAccountNumber - 1, accountBalance.get(receiverAccountNumber - 1) + amount);

            // Update transaction history
            String transaction = "-" + amount + ", " + accountBalance.get(accountNumber - 1) + "," + java.time.LocalDate.now();
            transactionHistory.get(accountNumber - 1).add(transaction);

            transaction = "+" + amount + "," + accountBalance.get(receiverAccountNumber - 1) + "," + java.time.LocalDate.now();
            transactionHistory.get(receiverAccountNumber - 1).add(transaction);
        }
        catch(Exception e){
            System.out.println("Error in bank to bank transfer");
        }
        System.out.println("Amount transferred successfully");
    }

    // Method to check balance
    public void checkBalance(){
        try{
            accountNumber = getAccountNumber();

            if(accountNumber < 1 || accountNumber > accountDetails.size()){ // Check if account number is valid
                throw new Exception("Invalid account number");
            }
            System.out.println("Balance: " + accountBalance.get(accountNumber - 1));    // Display balance
        }
        catch(Exception e){
            System.out.println("Error in checking balance");
        }
        System.out.println("Balance checked successfully");
    }

    // Method to set account details
    public String[] setAccountDetails(){
        String account[] = new String[4];
        try{    
            // Get account details
            System.out.println("Enter your name:");
            String name = sc.nextLine();

            System.out.println("Enter your age:");
            int age = sc.nextInt();
            sc.nextLine(); // Consume the newline left-over
            if (age < 10) {
                throw new Exception("Age should be greater than 10");
            } else if (age > 100) {
                throw new Exception("Age should be less than 100");
            }

            System.out.println("Enter your address:");
            String address = sc.nextLine();

            System.out.println("Enter your phone number:");
            long phone = sc.nextLong();
            if(String.valueOf(phone).length() != 10){
                throw new Exception("Phone number should be of 10 digits");
            }

            account[0] = name;
            account[1] = String.valueOf(age);
            account[2] = address;
            account[3] = String.valueOf(phone);
        }catch(Exception e){
            System.out.println("Error in getting account details");
        }
        return account; // Return account details
    }

    // Method to update account details
    public void updateAccountDetails(){
        try{
            accountNumber = getAccountNumber();

            if(accountNumber < 1 || accountNumber > accountDetails.size()){
                throw new Exception("Invalid account number");
            }

            // Display account details
            System.out.println("Your details are: ");
            System.out.println("Name: " + accountDetails.get(accountNumber - 1)[0]);
            System.out.println("Age: " + accountDetails.get(accountNumber - 1)[1]);
            System.out.println("Address: " + accountDetails.get(accountNumber - 1)[2]);
            System.out.println("Phone: " + accountDetails.get(accountNumber - 1)[3]);

            System.out.println("Do you wish to update your details? (Y/N): ");
            char choice = sc.next().charAt(0);

            // Update account details if choice is Y/y
            if(choice == 'Y' || choice == 'y'){
                String[] detailStrings = setAccountDetails();
                accountDetails.set(accountNumber - 1, detailStrings);
            }else{
                return;
            }
        }
        catch(Exception e){
            System.out.println("Error in updating account details");
        }
        
        System.out.println("Account details updated successfully");
        return;
    }

    // Method to delete account
    public void deleteAccount(){
        // Get account number
        accountNumber = getAccountNumber();
        try{
            if(accountNumber < 1 || accountNumber > accountDetails.size()){
                throw new Exception("Invalid account number");
            }
            accountDetails.remove(accountNumber - 1);       // Remove account details
            accountBalance.remove(accountNumber - 1);       // Remove account balance
            transactionHistory.remove(accountNumber - 1);   // Remove transaction history
        }
        catch(Exception e){
            System.out.println("Error in deleting account");
        }
        System.out.println("Account deleted successfully");
    }

    // Method to display account details
    public void accountDetails(){
        try{
            System.out.println("Enter your account number: ");
            accountNumber = sc.nextInt();
            if(accountNumber < 1 || accountNumber > accountDetails.size()){
                throw new Exception("Invalid account number");
            }
            System.out.println("Name: " + accountDetails.get(accountNumber - 1)[0]);
            System.out.println("Age: " + accountDetails.get(accountNumber - 1)[1]);
            System.out.println("Address: " + accountDetails.get(accountNumber - 1)[2]);
            System.out.println("Phone: " + accountDetails.get(accountNumber - 1)[3]);
        }
        catch(Exception e){
            System.out.println("Error in displaying account details");
        }
        System.out.println("Account details displayed successfully");
    }

    //  Method to get account number
    public int getAccountNumber(){
        System.out.println("Enter your account number: ");
        accountNumber = sc.nextInt();
        return accountNumber;
    }

    // Method to display transaction history
    public void transactionHistory(){
        try{
            accountNumber = getAccountNumber();

            if(accountNumber < 1 || accountNumber > accountDetails.size()){
                throw new Exception("Invalid account number");
            }
            System.out.println("Transaction history: ");

            // Display transaction history in tabular format
            System.out.println("Amount" + "\t\t" + "Balance" + "\t\t" + "Date");

            // Display particular account transaction history
            for(int i = 0; i < transactionHistory.get(accountNumber - 1).size(); i++){
                String[] transaction = transactionHistory.get(accountNumber - 1).get(i).split(",");
                for(int j = 0; j < transaction.length; j++){
                    System.out.print(transaction[j] + "\t\t");
                }
                System.out.println();
            }
        }
        catch(Exception e){
            System.out.println("Error in displaying transaction history");
        }
    }

    public static void main(String[] args) {
        // Main method to run the banking system
        // Object of Bank class
        Bank bank = new Bank();

        // Menu driven program
        int option;
        int amount;
        do{
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
            switch(option){
                case 1:
                    bank.createAccount();
                    break;
                case 2:
                    System.out.println("Enter the amount to deposit: ");
                    amount = sc.nextInt();

                    bank.deposit(amount);
                    break;
                case 3:
                    System.out.println("Enter the amount to withdraw: ");
                    amount = sc.nextInt();

                    bank.withdraw( amount);
                    break;
                case 4:
                    bank.bankToBankTransfer();
                    break;
                case 5:
                    bank.checkBalance();
                    break;
                case 6:
                    bank.updateAccountDetails();
                    break;
                case 7:
                    bank.deleteAccount();
                    break;
                case 8:
                    bank.accountDetails();
                    break;
                case 9:
                    bank.transactionHistory();
                    break;
                case 10:
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }while(option != 10);
        System.out.println("Thank you for using PTC Bank");
    }
}
