import java.util.*;

public class ExamSystem {
    private User user;
    private List<Question> questionList;
    private Scanner scanner;
    private boolean examStarted = false;
    private int score = 0;
    private List<Integer> userAnswers = new ArrayList<>();

    public ExamSystem(User user) {
        this.user = user;
        this.scanner = new Scanner(System.in);
        this.questionList = new ArrayList<>();
        loadQuestions();
    }

    private void loadQuestions() {
        questionList.add(new Question("IDE for Java?", 
            new String[]{"Eclipse", "VS Code", "NetBeans", "All of these"}, 4));
        questionList.add(new Question("Which language is used for Android development?",
            new String[] {"Swift", "Kotlin", "Python", "C++"}, 2));
        questionList.add(new Question("Who developed Java?",
            new String[] {"Bjarne Stroustrup", "James Gosling", "Guido van Rossum", "Dennis Ritchie"}, 2));
        questionList.add(new Question("What is the output of 2 + 2 * 2?",
            new String[] {"8", "6", "4", "10"}, 2));
        questionList.add(new Question("Which keyword is used to inherit a class in Java?",
            new String[] {"extends", "implements", "inherits", "super"}, 1));
        questionList.add(new Question("SQL is a ___ language", 
            new String[]{"Programming", "Query", "Markup", "Scripting"}, 2));
        questionList.add(new Question("Java is ___", 
            new String[]{"Procedural", "Object-Oriented", "Functional", "None"}, 2));
        questionList.add(new Question("HTML stands for?", 
            new String[]{"HyperText Makeup Language", "HighText Markup Language", "HyperText Markup Language", "None"}, 3));
        questionList.add(new Question("CSS is used for?", 
            new String[]{"Database", "Server Logic", "Styling", "None"}, 3));
        questionList.add(new Question("Which one is a JavaScript framework?", 
            new String[]{"React", "Django", "Laravel", "Flask"}, 1));



    }

    public void start() {
        System.out.println("=== Welcome to the Online Examination System ===");
        System.out.print("Enter User ID: ");
        String enteredId = scanner.nextLine();

        System.out.print("Enter Password: ");
        String enteredPwd = scanner.nextLine();

        if (user.getUserId().equals(enteredId) && user.checkPassword(enteredPwd)) {
            System.out.println("\nLogin successful! Welcome, " + user.getName());
            showMenu();
        } else {
            System.out.println("Invalid credentials. Exiting.");
        }
    }

    private void showMenu() {
        int choice = -1;

        while (choice != 5) {
            System.out.println("\n===== Menu =====");
            System.out.println("1. Update Profile");
            System.out.println("2. Change Password");
            System.out.println("3. Start Exam");
            System.out.println("4. View Profile");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    updateProfile();
                    break;
                case 2:
                    changePassword();
                    break;
                case 3:
                    startExam();
                    break;
                case 4:
                    user.displayProfile();
                    break;
                case 5:
                    System.out.println("Logging out... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private void updateProfile() {
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        user.updateProfile(newName);
        System.out.println("Profile updated.");
    }

    private void changePassword() {
        System.out.print("Enter new password: ");
        String newPass = scanner.nextLine();
        user.changePassword(newPass);
        System.out.println("Password updated.");
    }

    private void startExam() {
        if (examStarted) {
            System.out.println("Exam already attempted. You cannot retake it.");
            return;
        }

        System.out.println("\n=== Exam Started ===");
        System.out.println("You have 60 seconds to complete the test.");
        System.out.println("Answer by entering option number (1-4).");

        long startTime = System.currentTimeMillis();
        long duration = 60000; // 60 seconds

        for (int i = 0; i < questionList.size(); i++) {
            long currentTime = System.currentTimeMillis();
            long timeLeft = duration - (currentTime - startTime);

            if (timeLeft <= 0) {
                System.out.println("\n⏰ Time’s up! Auto-submitting your test...");
                break;
            }

            System.out.println("Time left: " + (timeLeft / 1000) + " seconds");

            Question q = questionList.get(i);
            q.displayQuestion();

            int answer = -1;
            try {
                System.out.print("Your answer: ");
                answer = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                answer = -1;
            }

            userAnswers.add(answer);
            if (q.isCorrect(answer)) {
                score++;
            }
        }

        examStarted = true;
        endExam();
    }

    private void endExam() {
        System.out.println("\n Exam Submitted!");
        System.out.println("Your Score: " + score + "/" + questionList.size());
        System.out.println("\n=== Exam Review ===");

        for (int i = 0; i < questionList.size(); i++) {
            Question q = questionList.get(i);
            int userAns = userAnswers.size() > i ? userAnswers.get(i) : -1;
            int correct = q.getCorrectOption();

            System.out.println("\nQ" + (i + 1) + ". " + q.getQuestionText());

            String[] opts = q.getOptions();
            for (int j = 0; j < opts.length; j++) {
                int optionNum = j + 1;
                String marker = "";
                if (userAns == optionNum && optionNum == correct) {
                    marker = " (Correct)";
                } 
                else if (userAns == optionNum && optionNum != correct) {
                    marker = " (Your Answer, Wrong)";
                } 
                else if (userAns != correct && optionNum == correct) {
                    marker = " (Correct)";
                }

                System.out.println(optionNum + ". " + opts[j] + marker);
            }
        }
    }
}
