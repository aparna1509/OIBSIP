import java.util.Scanner;

public class ATMOperations {
    private User user;
    private Scanner scanner;

    public ATMOperations(User user) {
        this.user = user;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n========= ATM Menu =========");
            System.out.println("UserId: " + user.getUserId());
            System.out.println("Current Balance: Rs. " + user.getBalance());
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    user.showHistory();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    System.out.println("Thank you for using ATM!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: Rs. ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        if (user.withdraw(amount)) {
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: Rs. ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        if (amount > 0) {
            user.deposit(amount);
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Invalid amount.");
        }
    }

    private void transfer() {
        System.out.print("Enter recipient ID: ");
        String recipient = scanner.nextLine();
        System.out.print("Enter amount to transfer: Rs. ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        if (user.transfer(amount, recipient)) {
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }
}
