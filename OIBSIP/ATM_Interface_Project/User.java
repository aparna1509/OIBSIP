public class User {
    private String userId;
    private String pin;
    private double balance;
    private TransactionHistory transactionHistory;

    public User(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new TransactionHistory();
        transactionHistory.addTransaction("Initial Deposit: Rs. " + balance);
    }

    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.addTransaction("Deposit: Rs. " + amount);
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.addTransaction("Withdraw: Rs. " + amount);
            return true;
        }
        return false;
    }

    public boolean transfer(double amount, String recipientId) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.addTransaction("Transferred Rs. " + amount + " to " + recipientId);
            return true;
        }
        return false;
    }

    public void showHistory() {
        transactionHistory.printHistory();
    }
}
