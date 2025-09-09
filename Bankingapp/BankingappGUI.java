package Bankingapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class BankingappGUI extends JFrame {
    private Map<String, Account> accounts = new HashMap<>();
    private String currentUser = null;
    private static final String DATA_FILE = "bankAccounts.dat";
    
    // Main panels
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JPanel welcomePanel;
    private JPanel loginPanel;
    private JPanel createAccountPanel;
    private JPanel accountPanel;
    
    // Login components
    private JTextField loginIdField;
    private JPasswordField loginPinField;
    
    // Create account components
    private JTextField createIdField;
    private JTextField createNameField;
    private JPasswordField createPinField;
    private JTextField initialDepositField;
    
    // Account components
    private JLabel balanceLabel;
    private JTextField depositField;
    private JTextField withdrawField;
    private JTextArea transactionArea;
    
    public BankingappGUI() {
        // Set up the frame
        super("Java Banking System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Load accounts
        loadAccounts();
        
        // Set up card layout for different screens
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Create and add panels
        createWelcomePanel();
        createLoginPanel();
        createNewAccountPanel();
        createAccountPanel();
        
        // Add panels to main panel with card layout
        mainPanel.add(welcomePanel, "welcome");
        mainPanel.add(loginPanel, "login");
        mainPanel.add(createAccountPanel, "createAccount");
        mainPanel.add(accountPanel, "account");
        
        // Show welcome panel first
        cardLayout.show(mainPanel, "welcome");
        
        // Add main panel to frame
        add(mainPanel);
        
        // Add window listener to save accounts when closing
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveAccounts();
            }
        });
    }
    
    private void createWelcomePanel() {
        welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout(10, 10));
        
        JLabel titleLabel = new JLabel("Welcome to Java Banking System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomePanel.add(titleLabel, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> cardLayout.show(mainPanel, "login"));
        
        JButton createAccountButton = new JButton("Create New Account");
        createAccountButton.addActionListener(e -> cardLayout.show(mainPanel, "createAccount"));
        
        buttonPanel.add(loginButton);
        buttonPanel.add(createAccountButton);
        
        welcomePanel.add(buttonPanel, BorderLayout.CENTER);
    }
    
    private void createLoginPanel() {
        loginPanel = new JPanel(new BorderLayout(10, 10));
        
        JLabel titleLabel = new JLabel("Login to Your Account", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        loginPanel.add(titleLabel, BorderLayout.NORTH);
        
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));
        
        formPanel.add(new JLabel("Account ID:"));
        loginIdField = new JTextField(20);
        formPanel.add(loginIdField);
        
        formPanel.add(new JLabel("PIN:"));
        loginPinField = new JPasswordField(20);
        formPanel.add(loginPinField);
        
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> attemptLogin());
        
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "welcome");
            clearLoginFields();
        });
        
        formPanel.add(backButton);
        formPanel.add(loginButton);
        
        loginPanel.add(formPanel, BorderLayout.CENTER);
    }
    
    private void createNewAccountPanel() {
        createAccountPanel = new JPanel(new BorderLayout(10, 10));
        
        JLabel titleLabel = new JLabel("Create New Account", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        createAccountPanel.add(titleLabel, BorderLayout.NORTH);
        
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));
        
        formPanel.add(new JLabel("Account ID:"));
        createIdField = new JTextField(20);
        formPanel.add(createIdField);
        
        formPanel.add(new JLabel("Your Name:"));
        createNameField = new JTextField(20);
        formPanel.add(createNameField);
        
        formPanel.add(new JLabel("Create PIN:"));
        createPinField = new JPasswordField(20);
        formPanel.add(createPinField);
        
        formPanel.add(new JLabel("Initial Deposit:"));
        initialDepositField = new JTextField(20);
        formPanel.add(initialDepositField);
        
        JButton createButton = new JButton("Create Account");
        createButton.addActionListener(e -> attemptCreateAccount());
        
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "welcome");
            clearCreateFields();
        });
        
        formPanel.add(backButton);
        formPanel.add(createButton);
        
        createAccountPanel.add(formPanel, BorderLayout.CENTER);
    }
    
    private void createAccountPanel() {
        accountPanel = new JPanel(new BorderLayout(10, 10));
        
        JLabel titleLabel = new JLabel("Account Dashboard", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        accountPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Tabs for different account functions
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Summary panel
        JPanel summaryPanel = new JPanel(new BorderLayout(10, 10));
        JPanel balancePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        balanceLabel = new JLabel("Current Balance: $0.00");
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        balancePanel.add(balanceLabel);
        summaryPanel.add(balancePanel, BorderLayout.NORTH);
        
        // Deposit panel
        JPanel depositPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        depositPanel.setBorder(BorderFactory.createTitledBorder("Make a Deposit"));
        depositPanel.add(new JLabel("Amount:"));
        depositField = new JTextField(10);
        depositPanel.add(depositField);
        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(e -> makeDeposit());
        depositPanel.add(depositButton);
        
        // Withdraw panel
        JPanel withdrawPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        withdrawPanel.setBorder(BorderFactory.createTitledBorder("Make a Withdrawal"));
        withdrawPanel.add(new JLabel("Amount:"));
        withdrawField = new JTextField(10);
        withdrawPanel.add(withdrawField);
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(e -> makeWithdrawal());
        withdrawPanel.add(withdrawButton);
        
        // Add deposit and withdraw panels to summary
        JPanel actionsPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        actionsPanel.add(depositPanel);
        actionsPanel.add(withdrawPanel);
        summaryPanel.add(actionsPanel, BorderLayout.CENTER);
        
        // Transactions panel
        JPanel transactionsPanel = new JPanel(new BorderLayout(10, 10));
        transactionArea = new JTextArea(15, 40);
        transactionArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(transactionArea);
        transactionsPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Add panels to tabs
        tabbedPane.addTab("Summary", summaryPanel);
        tabbedPane.addTab("Transactions", transactionsPanel);
        
        // Add tabs to account panel
        accountPanel.add(tabbedPane, BorderLayout.CENTER);
        
        // Logout button at bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> logout());
        buttonPanel.add(logoutButton);
        accountPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void attemptLogin() {
        String accountId = loginIdField.getText().trim();
        String pin = new String(loginPinField.getPassword());
        
        Account account = accounts.get(accountId);
        
        if (account != null && account.validatePin(pin)) {
            currentUser = accountId;
            updateAccountView();
            cardLayout.show(mainPanel, "account");
            clearLoginFields();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Invalid account ID or PIN.", 
                "Login Failed", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void attemptCreateAccount() {
        String accountId = createIdField.getText().trim();
        String name = createNameField.getText().trim();
        String pin = new String(createPinField.getPassword());
        
        if (accountId.isEmpty() || name.isEmpty() || pin.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "All fields are required.", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (accounts.containsKey(accountId)) {
            JOptionPane.showMessageDialog(this, 
                "Account ID already exists. Please choose another.", 
                "Account Creation Failed", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        double initialDeposit = 0;
        try {
            initialDeposit = Double.parseDouble(initialDepositField.getText().trim());
            if (initialDeposit < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a valid initial deposit amount.", 
                "Invalid Input", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Account newAccount = new Account(accountId, name, pin, initialDeposit);
        accounts.put(accountId, newAccount);
        saveAccounts();
        
        JOptionPane.showMessageDialog(this, 
            "Account created successfully!", 
            "Success", 
            JOptionPane.INFORMATION_MESSAGE);
        
        clearCreateFields();
        cardLayout.show(mainPanel, "welcome");
    }
    
    private void updateAccountView() {
        Account account = accounts.get(currentUser);
        if (account != null) {
            // Update balance
            balanceLabel.setText(String.format("Current Balance: $%.2f", account.getBalance()));
            
            // Update transaction history
            updateTransactionHistory();
        }
    }
    
    private void updateTransactionHistory() {
        Account account = accounts.get(currentUser);
        if (account != null) {
            transactionArea.setText(""); // Clear previous text
            List<Transaction> transactions = account.getTransactions();
            
            if (transactions.isEmpty()) {
                transactionArea.append("No transactions to display.");
            } else {
                for (Transaction transaction : transactions) {
                    transactionArea.append(transaction.toString() + "\n");
                }
            }
        }
    }
    
    private void makeDeposit() {
        if (currentUser == null) return;
        
        try {
            double amount = Double.parseDouble(depositField.getText().trim());
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "Deposit amount must be greater than zero.", 
                    "Invalid Amount", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Account account = accounts.get(currentUser);
            account.deposit(amount);
            saveAccounts();
            
            JOptionPane.showMessageDialog(this, 
                String.format("Deposit of $%.2f successful.", amount), 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
                
            depositField.setText("");
            updateAccountView();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a valid amount.", 
                "Invalid Input", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void makeWithdrawal() {
        if (currentUser == null) return;
        
        try {
            double amount = Double.parseDouble(withdrawField.getText().trim());
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "Withdrawal amount must be greater than zero.", 
                    "Invalid Amount", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Account account = accounts.get(currentUser);
            
            if (amount > account.getBalance()) {
                JOptionPane.showMessageDialog(this, 
                    "Insufficient funds.", 
                    "Transaction Failed", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            account.withdraw(amount);
            saveAccounts();
            
            JOptionPane.showMessageDialog(this, 
                String.format("Withdrawal of $%.2f successful.", amount), 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
                
            withdrawField.setText("");
            updateAccountView();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a valid amount.", 
                "Invalid Input", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void logout() {
        currentUser = null;
        cardLayout.show(mainPanel, "welcome");
    }
    
    private void clearLoginFields() {
        loginIdField.setText("");
        loginPinField.setText("");
    }
    
    private void clearCreateFields() {
        createIdField.setText("");
        createNameField.setText("");
        createPinField.setText("");
        initialDepositField.setText("");
    }
    
    @SuppressWarnings("unchecked")
    private void loadAccounts() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                accounts = (HashMap<String, Account>) ois.readObject();
                System.out.println("Account data loaded successfully.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading account data: " + e.getMessage());
                accounts = new HashMap<>();
            }
        }
    }
    
    private void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("Error saving account data: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BankingappGUI app = new BankingappGUI();
            app.setVisible(true);
        });
    }
    
    // Include Account and Transaction classes exactly as they were in the original app
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
