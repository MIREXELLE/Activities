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
 * Demonstrates OOP concepts with a basic bank account management system
 */
public class Bankingapp {
    private static Map<String, Account> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static String currentUser = null;
    private static final String DATA_FILE = "bankAccounts.dat";
    
    public static void main(String[] args) {
        // Load existing accounts when starting
        loadAccounts();
        
        boolean exit = false;
        
        System.out.println("Welcome to Java Banking System");
        
        while (!exit) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1: // Create account
                    createAccount();
                    break;
                case 2: // Login
                    login();
                    break;
                case 3: // Exit
                    exit = true;
                    System.out.println("Thank you for using Java Banking System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    // Load accounts from file
    @SuppressWarnings("unchecked")
    private static void loadAccounts() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                accounts = (HashMap<String, Account>) ois.readObject();
                System.out.println("Account data loaded successfully.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading account data: " + e.getMessage());
                // Initialize with empty map if loading fails
                accounts = new HashMap<>();
            }
        }
    }
    
    // Save accounts to file
    private static void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(accounts);
            // System.out.println("Account data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving account data: " + e.getMessage());
        }
    }
    
    // Display main menu options
    private static void displayMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Create Account");
        System.out.println("2. Login");
        System.out.println("3. Exit");
    }
    
    // Display logged-in user menu
    private static void displayUserMenu() {
        System.out.println("\n--- Account Menu ---");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. View Transactions");
        System.out.println("5. Logout");
    }
    
    // Create a new bank account
    private static void createAccount() {
        System.out.println("\n--- Create Account ---");
        String accountId = getStringInput("Enter account ID (username): ");
        
        if (accounts.containsKey(accountId)) {
            System.out.println("Account ID already exists. Please choose another.");
            return;
        }
        
        String name = getStringInput("Enter your name: ");
        String pin = getStringInput("Create a PIN: ");
        double initialDeposit = getDoubleInput("Enter initial deposit amount: ");
        
        Account newAccount = new Account(accountId, name, pin, initialDeposit);
        accounts.put(accountId, newAccount);
        
        // Save updated accounts to file
        saveAccounts();
        
        System.out.println("Account created successfully!");
    }
    
    // Login to existing account
    private static void login() {
        System.out.println("\n--- Login ---");
        String accountId = getStringInput("Enter account ID: ");
        String pin = getStringInput("Enter PIN: ");
        
        Account account = accounts.get(accountId);
        
        if (account != null && account.validatePin(pin)) {
            currentUser = accountId;
            System.out.println("Login successful. Welcome, " + account.getName() + "!");
            accountMenu();
        } else {
            System.out.println("Invalid account ID or PIN.");
        }
    }
    
    // Handle account menu after login
    private static void accountMenu() {
        boolean logout = false;
        
        while (!logout && currentUser != null) {
            displayUserMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1: // Check balance
                    checkBalance();
                    break;
                case 2: // Deposit
                    deposit();
                    break;
                case 3: // Withdraw
                    withdraw();
                    break;
                case 4: // View transactions
                    viewTransactions();
                    break;
                case 5: // Logout
                    logout = true;
                    currentUser = null;
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    // Display current account balance
    private static void checkBalance() {
        Account account = accounts.get(currentUser);
        System.out.println("\n--- Account Balance ---");
        System.out.println("Current balance: $" + account.getBalance());
    }
    
    // Deposit money to account
    private static void deposit() {
        System.out.println("\n--- Deposit ---");
        double amount = getDoubleInput("Enter amount to deposit: ");
        
        if (amount <= 0) {
            System.out.println("Invalid amount. Deposit must be greater than zero.");
            return;
        }
        
        Account account = accounts.get(currentUser);
        account.deposit(amount);
        
        // Save after deposit
        saveAccounts();
        
        System.out.println("Deposit successful. New balance: $" + account.getBalance());
    }
    
    // Withdraw money from account
    private static void withdraw() {
        System.out.println("\n--- Withdraw ---");
        double amount = getDoubleInput("Enter amount to withdraw: ");
        
        if (amount <= 0) {
            System.out.println("Invalid amount. Withdrawal must be greater than zero.");
            return;
        }
        
        Account account = accounts.get(currentUser);
        
        if (amount > account.getBalance()) {
            System.out.println("Insufficient funds. Your balance is $" + account.getBalance());
            return;
        }
        
        account.withdraw(amount);
        
        // Save after withdrawal
        saveAccounts();
        
        System.out.println("Withdrawal successful. New balance: $" + account.getBalance());
    }
    
    // View transaction history
    private static void viewTransactions() {
        System.out.println("\n--- Transaction History ---");
        Account account = accounts.get(currentUser);
        List<Transaction> transactions = account.getTransactions();
        
        if (transactions.isEmpty()) {
            System.out.println("No transactions to display.");
            return;
        }
        
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
    
    // Helper method to get string input
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    // Helper method to get integer input
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
    
    // Helper method to get double input
    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
    
    /**
     * Account class to represent a bank account
     * Implements Serializable to allow saving to file
     */
    static class Account implements Serializable {
        private static final long serialVersionUID = 1L;
        private String accountId;
        private String name;
        private String pin;
        private double balance;
        private List<Transaction> transactions;
        
        public Account(String accountId, String name, String pin, double initialDeposit) {
            this.accountId = accountId;
            this.name = name;
            this.pin = pin;
            this.balance = initialDeposit;
            this.transactions = new ArrayList<>();
            
            // Record initial deposit as a transaction
            if (initialDeposit > 0) {
                transactions.add(new Transaction("Deposit", initialDeposit, initialDeposit));
            }
        }
        
        public String getAccountId() {
            return accountId;
        }
        
        public String getName() {
            return name;
        }
        
        public double getBalance() {
            return balance;
        }
        
        public List<Transaction> getTransactions() {
            return transactions;
        }
        
        public boolean validatePin(String inputPin) {
            return pin.equals(inputPin);
        }
        
        public void deposit(double amount) {
            balance += amount;
            transactions.add(new Transaction("Deposit", amount, balance));
        }
        
        public void withdraw(double amount) {
            if (amount <= balance) {
                balance -= amount;
                transactions.add(new Transaction("Withdrawal", -amount, balance));
            }
        }
    }
    
    /**
     * Transaction class to record account activities
     * Implements Serializable to allow saving to file
     */
    static class Transaction implements Serializable {
        private static final long serialVersionUID = 1L;
        private Date timestamp;
        private String type;
        private double amount;
        private double balanceAfter;
        
        public Transaction(String type, double amount, double balanceAfter) {
            this.timestamp = new Date();
            this.type = type;
            this.amount = amount;
            this.balanceAfter = balanceAfter;
        }
        
        @Override
        public String toString() {
            return String.format("%s - %s: $%.2f (Balance: $%.2f)",
                    timestamp, type, Math.abs(amount), balanceAfter);
        }
    }
}
