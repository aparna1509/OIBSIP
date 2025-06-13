public class Main {
    public static void main(String[] args) {
        // Create a sample user (ID: user123, Password: pass123)
        User user = new User("user123", "pass123", "Test User");
        

        // Start the Exam System
        ExamSystem examSystem = new ExamSystem(user);
        examSystem.start();
    }
}
