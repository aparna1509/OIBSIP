import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>();

        // Predefined users (you can add more here)
        users.add(new User("user1", "1111", 5000));
        users.add(new User("user2", "2222", 3000));
        users.add(new User("user3", "3333", 10000));

        System.out.println("Welcome to ATM!");

        int attempts = 3;
        User currentUser = null;

        while (attempts > 0) {
            System.out.print("Enter User ID: ");
            String enteredId = sc.nextLine();
            System.out.print("Enter User PIN: ");
            String enteredPin = sc.nextLine();

            // Find user in list
            for (User u : users) {
                if (u.getUserId().equals(enteredId) && u.getPin().equals(enteredPin)) {
                    currentUser = u;
                    break;
                }
            }

            if (currentUser != null) {
                System.out.println("Login successful.\n");
                ATMOperations atmOps = new ATMOperations(currentUser);
                atmOps.showMenu();
                sc.close();
                return;
            } else {
                attempts--;
                if (attempts > 0) {
                    System.out.println("Invalid credentials. Attempts left: " + attempts);
                } else {
                    System.out.println("Too many failed attempts. Exiting...");
                }
            }
        }

        sc.close();
    }
}
