import java.util.Scanner;

public class ATM {
    private int accountNumber;
    private int pin;
    private double balance;

    public ATM(int accountNumber, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public boolean authenticate(int enteredAccountNumber, int enteredPin) {
        return accountNumber == enteredAccountNumber && pin == enteredPin;
    }

    public void checkBalance() {
        System.out.println("Your balance: $" + balance);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount);
    }

    public void changePin(int newPin) {
        pin = newPin;
        System.out.println("PIN changed successfully.");
    }

    public static void main(String[] args) {
        ATM atm = new ATM(12345, 1234, 1000.0); // Replace with your account details

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your account number: ");
        int enteredAccountNumber = scanner.nextInt();

        System.out.print("Enter your PIN: ");
        int enteredPin = scanner.nextInt();

        if (atm.authenticate(enteredAccountNumber, enteredPin)) {
            int choice;
            do {
                System.out.println("\nATM Menu:");
                System.out.println("1. Check Balance");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Change PIN");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        atm.checkBalance();
                        break;
                    case 2:
                        System.out.print("Enter withdrawal amount: $");
                        double withdrawalAmount = scanner.nextDouble();
                        atm.withdraw(withdrawalAmount);
                        break;
                    case 3:
                        System.out.print("Enter deposit amount: $");
                        double depositAmount = scanner.nextDouble();
                        atm.deposit(depositAmount);
                        break;
                    case 4:
                        System.out.print("Enter new PIN: ");
                        int newPin = scanner.nextInt();
                        atm.changePin(newPin);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 5);
        } else {
            System.out.println("Authentication failed. Please check your account number and PIN.");
        }

        scanner.close();
    }
}
