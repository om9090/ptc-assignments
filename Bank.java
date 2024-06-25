import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;

public class Bank {
    private static Scanner sc = new Scanner(System.in);
    private Map<Integer, String[]> accountDetails = new HashMap<>();
    private Map<Integer, Integer> accountBalance = new HashMap<>();
    private Map<Integer, ArrayList<String>> transactionHistory = new HashMap<>();
    private AtomicInteger accountIdGenerator = new AtomicInteger(1);

    // Method to create account
    public void createAccount() {
        try {
            // Get account details
            String[] detailStrings = setAccountDetails();
            int accountId = accountIdGenerator.getAndIncrement();
            accountDetails.put(accountId, detailStrings); // Add account details to the map
            accountBalance.put(accountId, 0); // Add initial balance to the map
            transactionHistory.put(accountId, new ArrayList<>()); // Add transaction history to the map
            System.out.println("Account Number: " + accountId);
            System.out.println("Account created successfully");
        } catch (Exception e) {
            System.out.println("Error in creating account");
        }
    }

    // Method to deposit amount
    public void deposit(int amount) {
        try {
            int accountNumber = getAccountNumber(); // Get account number

            if (!accountDetails.containsKey(accountNumber)) {
                throw new Exception("Invalid account number");
            }
            if (amount <= 0) {
                throw new Exception("Amount should be greater than 0");
            }

            accountBalance.put(accountNumber, accountBalance.get(accountNumber) + amount); // Update account balance
            String transaction = "+" + amount + "," + accountBalance.get(accountNumber) + "," + java.time.LocalDate.now();
            transactionHistory.get(accountNumber).add(transaction); // Update transaction history
        } catch (Exception e) {
            System.out.println("Error in depositing amount: " + e.getMessage());
            return;
        }
        System.out.println("Amount deposited successfully");
    }

    // Method to withdraw amount
    public void withdraw(int amount) {
        try {
            int accountNumber = getAccountNumber(); // Get account number

            if (!accountDetails.containsKey(accountNumber)) {
                throw new Exception("Invalid account number");
            }
            if (amount <= 0) {
                throw new Exception("Amount should be greater than 0");
            }
            if (accountBalance.get(accountNumber) < amount) {
                throw new Exception("Insufficient balance");
            }

            accountBalance.put(accountNumber, accountBalance.get(accountNumber) - amount); // Update account balance
            String transaction = "-" + amount + "," + accountBalance.get(accountNumber) + "," + java.time.LocalDate.now();
            transactionHistory.get(accountNumber).add(transaction); // Update transaction history
        } catch (Exception e) {
            System.out.println("Error in withdrawing amount: " + e.getMessage());
            return;
        }
        System.out.println("Amount withdrawn successfully");
    }

    // Method to transfer amount from one account to another
    public void bankToBankTransfer() {
        try {
            int senderAccountNumber = getAccountNumber(); // Get sender account number

            if (!accountDetails.containsKey(senderAccountNumber)) {
                throw new Exception("Invalid account number");
            }

            System.out.println("Enter the account number to transfer: ");
            int receiverAccountNumber = sc.nextInt(); // Get receiver account number

            if (receiverAccountNumber == senderAccountNumber) {
                throw new Exception("Cannot transfer to same account");
            }

            if (!accountDetails.containsKey(receiverAccountNumber)) {
                throw new Exception("Invalid account number");
            }
            System.out.println("Enter the amount to transfer: ");
            int amount = sc.nextInt();
            if (amount <= 0) {
                throw new Exception("Amount should be greater than 0");
            }
            if (accountBalance.get(senderAccountNumber) < amount) {
                throw new Exception("Insufficient balance");
            }

            // Update account balance of sender and receiver
            accountBalance.put(senderAccountNumber, accountBalance.get(senderAccountNumber) - amount);
            accountBalance.put(receiverAccountNumber, accountBalance.get(receiverAccountNumber) + amount);

            // Update transaction history
            String transaction = "-" + amount + ", " + accountBalance.get(senderAccountNumber) + "," + java.time.LocalDate.now();
            transactionHistory.get(senderAccountNumber).add(transaction);

            transaction = "+" + amount + "," + accountBalance.get(receiverAccountNumber) + "," + java.time.LocalDate.now();
            transactionHistory.get(receiverAccountNumber).add(transaction);
        } catch (Exception e) {
            System.out.println("Error in bank to bank transfer: " + e.getMessage());
            return;
        }
        System.out.println("Amount transferred successfully");
    }

    // Method to check balance
    public void checkBalance() {
        try {
            int accountNumber = getAccountNumber();

            if (!accountDetails.containsKey(accountNumber)) { // Check if account number is valid
                throw new Exception("Invalid account number");
            }
            System.out.println("Balance: " + accountBalance.get(accountNumber)); // Display balance
        } catch (Exception e) {
            System.out.println("Error in checking balance: " + e.getMessage());
            return;
        }
        System.out.println("Balance checked successfully");
    }

    // Method to set account details
    public String[] setAccountDetails() {
        String account[] = new String[4];
        try {
            // Get account details
            System.out.println("Enter your age:");
            Integer age = sc.nextInt();
            if (age < 10) {
                throw new Exception("Age should be greater than 10");
            } else if (age > 100) {
                throw new Exception("Age should be less than 100");
            }
            sc.nextLine();

            System.out.println("Enter your name:");
            String name = sc.nextLine();

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
        return account; // Return account details
    }

    // Method to update account details
    public void updateAccountDetails() {
        try {
            int accountNumber = getAccountNumber();

            if (!accountDetails.containsKey(accountNumber)) {
                throw new Exception("Invalid account number");
            }

            // Display account details
            System.out.println("Your details are: ");
            System.out.println("Name: " + accountDetails.get(accountNumber)[0]);
            System.out.println("Age: " + accountDetails.get(accountNumber)[1]);
            System.out.println("Address: " + accountDetails.get(accountNumber)[2]);
            System.out.println("Phone: " + accountDetails.get(accountNumber)[3]);

            System.out.println("Do you wish to update your details? (Y/N): ");
            char choice = sc.next().charAt(0);

            // Update account details if choice is Y/y
            if (choice == 'Y' || choice == 'y') {
                String[] detailStrings = setAccountDetails();
                accountDetails.put(accountNumber, detailStrings);
            } else {
                return;
            }
        } catch (Exception e) {
            System.out.println("Error in updating account details: " + e.getMessage());
            return;
        }
        System.out.println("Account details updated successfully");
    }

    // Method to delete account
    public void deleteAccount() {
        // Get account number
        int accountNumber = getAccountNumber();
        try {
            if (!accountDetails.containsKey(accountNumber)) {
                throw new Exception("Invalid account number");
            }
            accountDetails.remove(accountNumber); // Remove account details
            accountBalance.remove(accountNumber); // Remove account balance
            transactionHistory.remove(accountNumber); // Remove transaction history
        } catch (Exception e) {
            System.out.println("Error in deleting account: " + e.getMessage());
            return;
        }
        System.out.println("Account deleted successfully");
    }

    // Method to display account details
    public void displayAccountDetails() {
        try {
            System.out.println("Enter your account number: ");
            int accountNumber = sc.nextInt();
            if (!accountDetails.containsKey(accountNumber)) {
                throw new Exception("Invalid account number");
            }
            System.out.println("Name: " + accountDetails.get(accountNumber)[0]);
            System.out.println("Age: " + accountDetails.get(accountNumber)[1]);
            System.out.println("Address: " + accountDetails.get(accountNumber)[2]);
            System.out.println("Phone: " + accountDetails.get(accountNumber)[3]);
        } catch (Exception e) {
            System.out.println("Error in displaying account details: " + e.getMessage());
            return;
        }
        System.out.println("Account details displayed successfully");
    }

    // Method to get account number
    public int getAccountNumber() {
        System.out.println("Enter your account number: ");
        return sc.nextInt();
    }

    // Method to display transaction history
    public void displayTransactionHistory() {
        try {
            int accountNumber = getAccountNumber();

            if (!accountDetails.containsKey(accountNumber)) {
                throw new Exception("Invalid account number");
            }
            System.out.println("Transaction history: ");

            // Display transaction history in tabular format
            System.out.println("Amount" + "\t\t" + "Balance" + "\t\t" + "Date");

            // Display particular account transaction history
            for (String transaction : transactionHistory.get(accountNumber)) {
                String[] transactionDetails = transaction.split(",");
                for (String detail : transactionDetails) {
                    System.out.print(detail + "\t\t");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error in displaying transaction history: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Main method to run the banking system
        Bank bank = new Bank();

        int option;
        int amount;
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
                    bank.withdraw(amount);
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
                    bank.displayAccountDetails();
                    break;
                case 9:
                    bank.displayTransactionHistory();
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
