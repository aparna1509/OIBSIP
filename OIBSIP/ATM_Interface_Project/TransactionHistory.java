import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionHistory {
    private ArrayList<String> history;

    public TransactionHistory() {
        history = new ArrayList<>();
    }

    public void addTransaction(String detail) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String timestamp = LocalDateTime.now().format(fmt);
        history.add(timestamp + "  |  " + detail);
    }

    public void printHistory() {
        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("\n--- Transaction History ---");
            for (String record : history) {
                System.out.println(record);
            }
        }
    }
}
