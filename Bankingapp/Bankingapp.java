package Bankingapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Simple Banking Application with data persistence
 * 
 * ARCHITECTURE OVERVIEW:
 * ---------------------
 * This application implements a banking system with the following components:
 * 1. Main application class (Bankingapp): Handles UI/console interaction and program flow
 * 2. Account class: Represents a bank account with balance and operations
 * 3. Transaction class: Records account activities for auditing/history
 * 
 * DATA PERSISTENCE:
 * ----------------
 * - Uses Java's serialization to save/load account data to/from a file
 * - All data is stored in a single file (bankAccounts.dat)
 * 
 * SECURITY FEATURES:
 * ----------------
 * - Simple PIN authentication for account access
 * - Input validation to prevent invalid transactions
 * 
 * USER FLOWS:
 * ----------
 * 1. Create Account -> Set credentials -> Initial deposit -> Return to main menu
 * 2. Login -> Account operations -> Logout
 * 
 * @author YourName
 */
public class Bankingapp {
    // HashMap to store all accounts, with account ID as the key
    private static Map<String, Account> accounts = new HashMap<>();
    
    // Scanner for reading user input from console
    private static Scanner scanner = new Scanner(System.in);
    
    // Tracks the currently logged-in user (null if nobody is logged in)
    private static String currentUser = null;
    
    // File path where account data is stored between program runs
    private static final String DATA_FILE = "Bankingapp/bankAccounts.dat";
    
    /**
     * Main method - entry point of the application
     * Controls the main program flow with a menu-driven interface
     */
    public static void main(String[] args) {
        // Load previously saved accounts when the program starts
        loadAccounts();
        
        boolean exit = false;
        
        System.out.println("Welcome to Java Banking System");
        
        // Main program loop - continues until user chooses to exit
        while (!exit) {
            // Show available options to the user
            displayMenu();
            
            // Get user choice and handle accordingly
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1: // Create a new bank account
                    createAccount();
                    break;
                case 2: // Login to an existing account
                    login();
                    break;
                case 3: // Exit the application
                    exit = true;
                    System.out.println("Thank you for using Java Banking System!");
                    break;
                default: // Handle invalid menu selection
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        // Close scanner to prevent resource leak
        scanner.close();
    }
    
    /**
     * Loads previously saved account data from file
     * Uses Java's object serialization to reconstruct Account objects
     * 
     * @SuppressWarnings("unchecked") suppresses warning about type casting
     * the deserialized object to HashMap<String, Account>
     */
    @SuppressWarnings("unchecked")
    private static void loadAccounts() {
        File file = new File(DATA_FILE);
        
        // Check if the data file exists before trying to read from it
        if (file.exists()) {
            // Use try-with-resources to automatically close the streams
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                // Read the object and cast it to our HashMap of accounts
                accounts = (HashMap<String, Account>) ois.readObject();
                System.out.println("Account data loaded successfully.");
            } catch (IOException | ClassNotFoundException e) {
                // Handle possible exceptions during file reading or class loading
                System.out.println("Error loading account data: " + e.getMessage());
                // Initialize with empty map if loading fails to ensure the app works
                accounts = new HashMap<>();
            }
        }
        // If file doesn't exist, accounts remains as an empty HashMap
    }
    
    /**
     * Saves all account data to file
     * Uses Java's object serialization to convert Account objects to bytes
     */
    private static void saveAccounts() {
        // Use try-with-resources to automatically close the streams
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            // Write the entire accounts HashMap to the file
            oos.writeObject(accounts);
            // Success message commented out to avoid cluttering the console during operations
            // System.out.println("Account data saved successfully.");
        } catch (IOException e) {
            // Notify user if saving fails
            System.out.println("Error saving account data: " + e.getMessage());
        }
    }
    
    /**
     * Displays the main menu options to the user
     */
    private static void displayMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Create Account");
        System.out.println("2. Login");
        System.out.println("3. Exit");
    }
    
    /**
     * Displays the account menu options once user is logged in
     */
    private static void displayUserMenu() {
        System.out.println("\n--- Account Menu ---");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. View Transactions");
        System.out.println("5. Logout");
    }
    
    /**
     * Handles the account creation process
     * Collects user information, validates input, and creates a new account
     */
    private static void createAccount() {
        System.out.println("\n--- Create Account ---");
        
        // Get account ID (username) from user
        String accountId = getStringInput("Enter account ID (username): ");
        
        // Check if account ID already exists to prevent duplicates
        if (accounts.containsKey(accountId)) {
            System.out.println("Account ID already exists. Please choose another.");
            return; // Exit method if ID is already taken
        }
        
        // Collect remaining account information
        String name = getStringInput("Enter your name: ");
        String pin = getStringInput("Create a PIN: ");
        double initialDeposit = getDoubleInput("Enter initial deposit amount: ");
        
        // Create new account object with collected information
        Account newAccount = new Account(accountId, name, pin, initialDeposit);
        
        // Add new account to the accounts map with accountId as the key
        accounts.put(accountId, newAccount);
        
        // Save updated accounts map to file to persist the new account
        saveAccounts();
        
        System.out.println("Account created successfully!");
    }
    
    /**
     * Handles the login process
     * Validates credentials and redirects to account menu if successful
     */
    private static void login() {
        System.out.println("\n--- Login ---");
        
        // Get account ID and PIN from user
        String accountId = getStringInput("Enter account ID: ");
        String pin = getStringInput("Enter PIN: ");
        
        // Retrieve account object using the provided ID
        Account account = accounts.get(accountId);
        
        // Check if account exists and PIN is correct
        if (account != null && account.validatePin(pin)) {
            // Set current user to the logged-in account ID
            currentUser = accountId;
            System.out.println("Login successful. Welcome, " + account.getName() + "!");
            
            // Show account menu for logged-in user
            accountMenu();
        } else {
            // Show error message without revealing which part of credentials was wrong (security best practice)
            System.out.println("Invalid account ID or PIN.");
        }
    }
    
    /**
     * Manages the account menu interactions after successful login
     * Provides a loop for account operations until logout
     */
    private static void accountMenu() {
        boolean logout = false;
        
        // Continue showing account menu until user logs out
        while (!logout && currentUser != null) {
            // Display available account operations
            displayUserMenu();
            
            // Get user choice and handle accordingly
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1: // Check current account balance
                    checkBalance();
                    break;
                case 2: // Deposit money into account
                    deposit();
                    break;
                case 3: // Withdraw money from account
                    withdraw();
                    break;
                case 4: // View transaction history
                    viewTransactions();
                    break;
                case 5: // Logout from account
                    logout = true;
                    currentUser = null;
                    System.out.println("Logged out successfully.");
                    break;
                default: // Handle invalid menu selection
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    /**
     * Displays the current balance of the logged-in account
     */
    private static void checkBalance() {
        // Get account object for current user
        Account account = accounts.get(currentUser);
        
        System.out.println("\n--- Account Balance ---");
        System.out.println("Current balance: $" + account.getBalance());
    }
    
    /**
     * Handles the deposit operation
     * Validates amount and updates account balance
     */
    private static void deposit() {
        System.out.println("\n--- Deposit ---");
        
        // Get deposit amount from user
        double amount = getDoubleInput("Enter amount to deposit: ");
        
        // Validate that deposit amount is positive
        if (amount <= 0) {
            System.out.println("Invalid amount. Deposit must be greater than zero.");
            return; // Exit if amount is invalid
        }
        
        // Get account object and perform deposit
        Account account = accounts.get(currentUser);
        account.deposit(amount);
        
        // Save the updated account information to file
        saveAccounts();
        
        System.out.println("Deposit successful. New balance: $" + account.getBalance());
    }
    
    /**
     * Handles the withdrawal operation
     * Validates amount, checks for sufficient funds, and updates balance
     */
    private static void withdraw() {
        System.out.println("\n--- Withdraw ---");
        
        // Get withdrawal amount from user
        double amount = getDoubleInput("Enter amount to withdraw: ");
        
        // Validate that withdrawal amount is positive
        if (amount <= 0) {
            System.out.println("Invalid amount. Withdrawal must be greater than zero.");
            return; // Exit if amount is invalid
        }
        
        // Get account object for current user
        Account account = accounts.get(currentUser);
        
        // Check if account has sufficient funds for withdrawal
        if (amount > account.getBalance()) {
            System.out.println("Insufficient funds. Your balance is $" + account.getBalance());
            return; // Exit if insufficient funds
        }
        
        // Perform withdrawal operation
        account.withdraw(amount);
        
        // Save the updated account information to file
        saveAccounts();
        
        System.out.println("Withdrawal successful. New balance: $" + account.getBalance());
    }
    
    /**
     * Displays the transaction history for the current account
     */
    private static void viewTransactions() {
        System.out.println("\n--- Transaction History ---");
        
        // Get account object and its transaction list
        Account account = accounts.get(currentUser);
        List<Transaction> transactions = account.getTransactions();
        
        // Check if transaction list is empty
        if (transactions.isEmpty()) {
            System.out.println("No transactions to display.");
            return;
        }
        
        // Print each transaction in the list
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
    
    /**
     * Helper method to get string input from the user
     * @param prompt The message to display when asking for input
     * @return The string entered by the user
     */
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    /**
     * Helper method to get integer input from the user
     * Continues prompting until valid input is provided
     * 
     * @param prompt The message to display when asking for input
     * @return The integer entered by the user
     */
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                // Display prompt and get input
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                // Handle case where user enters non-numeric input
                System.out.println("Invalid input. Please enter a number.");
                // Loop continues to prompt again
            }
        }
    }
    
    /**
     * Helper method to get double input from the user
     * Continues prompting until valid input is provided
     * 
     * @param prompt The message to display when asking for input
     * @return The double value entered by the user
     */
    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                // Display prompt and get input
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                // Handle case where user enters non-numeric input
                System.out.println("Invalid input. Please enter a number.");
                // Loop continues to prompt again
            }
        }
    }
    
    /**
     * Account class to represent a bank account
     * 
     * This class encapsulates all account-related data and operations
     * Implements Serializable to allow saving to file using Java's object serialization
     */
    static class Account implements Serializable {
        // Serialization version ID (helps with compatibility if class definition changes)
        private static final long serialVersionUID = 1L;
        
        // Account data fields
        private String accountId;    // Unique identifier for the account
        private String name;         // Account holder's name
        private String pin;          // Security PIN for authentication
        private double balance;      // Current account balance
        private List<Transaction> transactions;  // List of account transactions
        
        /**
         * Constructor to create a new account with initial values
         * 
         * @param accountId Unique identifier for the account
         * @param name Account holder's name
         * @param pin Security PIN for account access
         * @param initialDeposit Initial deposit amount
         */
        public Account(String accountId, String name, String pin, double initialDeposit) {
            this.accountId = accountId;
            this.name = name;
            this.pin = pin;
            this.balance = initialDeposit;
            this.transactions = new ArrayList<>();
            
            // Add initial deposit as the first transaction if amount is positive
            if (initialDeposit > 0) {
                transactions.add(new Transaction("Deposit", initialDeposit, initialDeposit));
            }
        }
        
        /**
         * Gets the account ID
         * @return Account ID string
         */
        public String getAccountId() {
            return accountId;
        }
        
        /**
         * Gets the account holder's name
         * @return Account holder name
         */
        public String getName() {
            return name;
        }
        
        /**
         * Gets the current balance
         * @return Current balance as a double
         */
        public double getBalance() {
            return balance;
        }
        
        /**
         * Gets the list of transactions
         * @return List of Transaction objects
         */
        public List<Transaction> getTransactions() {
            return transactions;
        }
        
        /**
         * Validates if the provided PIN matches the account PIN
         * @param inputPin PIN entered by user
         * @return true if PIN is valid, false otherwise
         */
        public boolean validatePin(String inputPin) {
            return pin.equals(inputPin);
        }
        
        /**
         * Adds money to the account balance
         * @param amount Amount to deposit
         */
        public void deposit(double amount) {
            // Add amount to balance
            balance += amount;
            
            // Record transaction with positive amount
            transactions.add(new Transaction("Deposit", amount, balance));
        }
        
        /**
         * Withdraws money from the account if sufficient funds available
         * @param amount Amount to withdraw
         */
        public void withdraw(double amount) {
            if (amount <= balance) {
                // Subtract amount from balance
                balance -= amount;
                
                // Record transaction with negative amount
                transactions.add(new Transaction("Withdrawal", -amount, balance));
            }
            // If insufficient funds, method silently fails
            // (The main program already checks for this condition)
        }
    }
    
    /**
     * Transaction class to record account activities
     * Serves as an immutable record of financial operations
     * Implements Serializable to allow saving to file
     */
    static class Transaction implements Serializable {
        // Serialization version ID
        private static final long serialVersionUID = 1L;
        
        // Transaction data fields
        private Date timestamp;      // When the transaction occurred
        private String type;         // Type of transaction (e.g., "Deposit", "Withdrawal")
        private double amount;       // Transaction amount (positive for deposits, negative for withdrawals)
        private double balanceAfter; // Account balance after this transaction
        
        /**
         * Constructor to create a new transaction record
         * 
         * @param type Type of transaction
         * @param amount Transaction amount
         * @param balanceAfter Balance after transaction
         */
        public Transaction(String type, double amount, double balanceAfter) {
            this.timestamp = new Date(); // Capture current time
            this.type = type;
            this.amount = amount;
            this.balanceAfter = balanceAfter;
        }
        
        /**
         * Creates string representation of the transaction
         * Used when displaying transaction history
         */
        @Override
        public String toString() {
            // Format: "[Timestamp] - [Type]: $[Amount] (Balance: $[BalanceAfter])"
            return String.format("%s - %s: $%.2f (Balance: $%.2f)",
                    timestamp, type, Math.abs(amount), balanceAfter);
        }
    }
}
