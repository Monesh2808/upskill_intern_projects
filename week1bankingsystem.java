package week1;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class BankingSystem  {
    private static Map<Integer, Account> accounts = new HashMap<>();
    private static int nextAccountId = 1;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    deposit(scanner);
                    break;
                case 3:
                    withdraw(scanner);
                    break;
                case 4:
                    checkBalance(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createAccount(Scanner scanner) {
        System.out.print("Enter account holder's name: ");
        String name = scanner.nextLine();
        Account account = new Account(nextAccountId, name);
        accounts.put(nextAccountId, account);
        System.out.println("Account created successfully. Account ID: " + nextAccountId);
        nextAccountId++;
    }

    private static void deposit(Scanner scanner) {
        System.out.print("Enter account ID: ");
        int accountId = scanner.nextInt();
        if (!accounts.containsKey(accountId)) {
            System.out.println("Account not found.");
            return;
        }
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        accounts.get(accountId).deposit(amount);
        System.out.println("Deposit successful. New balance: " + accounts.get(accountId).getBalance());
    }

    private static void withdraw(Scanner scanner) {
        System.out.print("Enter account ID: ");
        int accountId = scanner.nextInt();
        if (!accounts.containsKey(accountId)) {
            System.out.println("Account not found.");
            return;
        }
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (accounts.get(accountId).withdraw(amount)) {
            System.out.println("Withdrawal successful. New balance: " + accounts.get(accountId).getBalance());
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    private static void checkBalance(Scanner scanner) {
        System.out.print("Enter account ID: ");
        int accountId = scanner.nextInt();
        if (!accounts.containsKey(accountId)) {
            System.out.println("Account not found.");
            return;
        }
        System.out.println("Current balance: " + accounts.get(accountId).getBalance());
    }

    private static class Account {
        private int id;
        private String holderName;
        private double balance;

        public Account(int id, String holderName) {
            this.id = id;
            this.holderName = holderName;
            this.balance = 0.0;
        }

        public int getId() {
            return id;
        }

        public String getHolderName() {
            return holderName;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            balance += amount;
        }

        public boolean withdraw(double amount) {
            if (balance >= amount) {
                balance -= amount;
                return true;
            }
            return false;
        }
    }
}

