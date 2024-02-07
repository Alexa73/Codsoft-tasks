import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

class ATM {
    private Map<String, BankAccount> accounts;

    public ATM() {
        this.accounts = new HashMap<>();
    }

    public void addAccount(String accountHolder, double initialBalance) {
        accounts.put(accountHolder, new BankAccount(initialBalance));
    }

    public void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void performTransaction(String accountHolder) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            System.out.print("Choose an option (1-4): ");

            int choice;
            try {
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                    continue;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                continue;
            }

            switch (choice) {
                case 1:
                    checkBalance(accountHolder);
                    break;
                case 2:
                    System.out.print("Enter deposit amount: $");
                    double depositAmount;
                    try {
                        depositAmount = scanner.nextDouble();
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.next();
                        continue;
                    }
                    deposit(accountHolder, depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: $");
                    double withdrawalAmount;
                    try {
                        withdrawalAmount = scanner.nextDouble();
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.next();
                        continue;
                    }
                    withdraw(accountHolder, withdrawalAmount);
                    break;
                case 4:
                    System.out.println("Exiting. Thank you!");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    public void checkBalance(String accountHolder) {
        System.out.println("Your balance is: $" + accounts.get(accountHolder).getBalance());
    }

    public void deposit(String accountHolder, double amount) {
        accounts.get(accountHolder).deposit(amount);
        System.out.println("Deposit successful. Your new balance is: $" + accounts.get(accountHolder).getBalance());
    }

    public void withdraw(String accountHolder, double amount) {
        if (accounts.get(accountHolder).withdraw(amount)) {
            System.out.println("Withdrawal successful. Your new balance is: $" + accounts.get(accountHolder).getBalance());
        } else {
            System.out.println("Insufficient funds or invalid amount. Withdrawal failed.");
        }
    }
}

public class SimpleATM {
    public static void main(String[] args) {
        ATM atm = new ATM();

        // Adding some sample accounts
        atm.addAccount("User1", 1000.0);
        atm.addAccount("User2", 1500.0);

        // Simulate user interactions
        atm.performTransaction("User1");
        atm.performTransaction("User2");
    }
}
