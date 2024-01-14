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
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

class ATM {
    private BankAccount userAccount;

    public ATM(BankAccount userAccount) {
        this.userAccount = userAccount;
    }

    public void displayMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit \n");
    }

    public void performTransaction(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                withdraw(scanner);
                break;
            case 2:
                deposit(scanner);
                break;
            case 3:
                checkBalance();
                break;
            case 4:
                System.out.println("Exiting. Thank you!");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void withdraw(Scanner scanner) {
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (amount > 0 && userAccount.withdraw(amount)) {
            System.out.println("\nWithdrawal successful. Remaining balance: $" + userAccount.getBalance());
        } else {
            System.out.println("\nInvalid amount or insufficient funds. Withdrawal failed.");
        }
    }

    private void deposit(Scanner scanner) {
        System.out.print("\nEnter the amount to deposit: ");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            userAccount.deposit(amount);
            System.out.println("\nDeposit successful. New balance: $" + userAccount.getBalance());
        } else {
            System.out.println("\nInvalid amount. Deposit failed.");
        }
    }

    private void checkBalance() {
        System.out.println("Current Balance: $" + userAccount.getBalance());
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BankAccount userAccount = new BankAccount(1000.0);

        ATM atm = new ATM(userAccount);

        int choice;

        do {
            atm.displayMenu();
            System.out.print("Enter your choice (1-4): ");
            choice = scanner.nextInt();

            atm.performTransaction(choice, scanner);

        } while (choice != 4);

        scanner.close();
    }
}
