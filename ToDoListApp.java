import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ToDoListApp {
    private final ArrayList<Task> taskList = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ToDoListApp app = new ToDoListApp();
        app.run();
    }

    /*private void showMainMenu() {
        System.out.println("\u001B[36mMain Menu:\u001B[0m"); // Cyan color for main menu
        System.out.println("1. Add Task");
        System.out.println("2. Remove Task");
        System.out.println("3. List Tasks");
        System.out.println("4. Review Task");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }*/

    public void run() {
        while (true) {
            displayMenu();
            int userChoice = getUserChoice();
            handleChoice(userChoice);
            
        }
    }

    private void displayMenu() {
        System.out.println("\n\u2554\u2550\u2550\u2550 \u001B[34mTo-Do List Menu\u001B[0m \u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557");
        System.out.println("\u2551 1. Add Task               \u2551");
        System.out.println("\u2551 2. Remove Task            \u2551");
        System.out.println("\u2551 3. List Tasks             \u2551");
        System.out.println("\u2551 4. Review Task            \u2551");
        System.out.println("\u2551 5. Search Tasks           \u2551");
        System.out.println("\u2551 6. Sort Tasks by Priority \u2551");
        System.out.println("\u2551 7. Sort Tasks by Due Date \u2551");
        System.out.println("\u2551 8. Mark Task as Completed \u2551");
        System.out.println("\u2551 9. Exit                   \u2551");
        System.out.println("\u255A\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255D");
        System.out.print("Choose an option: ");
    }

    private int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return -1;
        }
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1 -> addTask();
            case 2 -> removeTask();
            case 3 -> listTasks();
            case 4 -> reviewTask();
            case 5 -> searchTasks();
            case 6 -> sortTasksByPriority();
            case 7 -> sortTasksByDueDate();
            case 8 -> markTaskAsCompleted();
            case 9 -> exitApp();
            default -> System.out.println("\u001B[31mInvalid choice. Please try again.\u001B[0m"); // Red color for invalid choice
        }
    }

    private void addTask() {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("\u001B[31mTask title cannot be empty.\u001B[0m"); // Red color for empty title
            return;
        }

        System.out.print("Enter task description: ");
        String description = scanner.nextLine().trim();

        LocalDate dueDate = getValidDueDate();
        String priority;
        while (true) {
            System.out.print("Enter priority\n1. LOW\n2. MEDIUM\n3.HIGH\n : ");
            String input = scanner.nextLine().trim();
            if (input.equals("1")) {
                priority = "LOW";
                break;
            } else if (input.equals("2")) {
                priority = "MEDIUM";
                break;
            } else if (input.equals("3")) {
                priority = "HIGH";
                break;
            } else {
            System.out.println("\u001B[31mInvalid priority. Please choose from LOW, MEDIUM, or HIGH.\u001B[0m"); // Red color for invalid priority
            }
        }

        System.out.print("\u001B[33mAdding task"); // Yellow color for adding task
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            try {
                TimeUnit.MILLISECONDS.sleep(500); // Simulate loading effect
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        taskList.add(new Task(title, description, false, dueDate, priority));
        System.out.println("\n\u001B[32mTask added successfully!\u001B[0m"); // Green color for success message
    }

    private LocalDate getValidDueDate() {
        while (true) {
            try {
                System.out.print("Enter year (YYYY): ");
                int year = Integer.parseInt(scanner.nextLine().trim());
    
                System.out.print("Enter month (1-12): ");
                int month = Integer.parseInt(scanner.nextLine().trim());
                if (month < 1 || month > 12) {
                    System.out.println("\u001B[31mInvalid month. Please try again.\u001B[0m"); // Red for invalid month
                    continue;
                }
    
                System.out.print("Enter day (1-31): ");
                int day = Integer.parseInt(scanner.nextLine().trim());
                if (day < 1 || day > 31) {
                    System.out.println("\u001B[31mInvalid day. Please try again.\u001B[0m"); // Red for invalid day
                    continue;
                }
    
                LocalDate dueDate = LocalDate.of(year, month, day);
                if (dueDate.isBefore(LocalDate.now())) {
                    System.out.println("\u001B[31mDue date cannot be in the past. Try again.\u001B[0m"); // Red for past date
                } else {
                    return dueDate;
                }
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mInvalid number format. Try again.\u001B[0m"); // Red for invalid number
            } catch (Exception e) {
                System.out.println("\u001B[31mInvalid date. Please try again.\u001B[0m"); // Red for general error
            }
        }
    }
    

    private void removeTask() {
        listTasks();
        System.out.print("Enter the task number to remove: ");
        try {
            int index = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (index >= 0 && index < taskList.size()) {
                taskList.remove(index);
                System.out.println("\u001B[32mTask removed successfully.\u001B[0m"); // Green color for success message
            } else {
                System.out.println("\u001B[31mInvalid task number.\u001B[0m"); // Red color for invalid task number
            }
        } catch (NumberFormatException e) {
            System.out.println("\u001B[31mInvalid input. Please enter a number.\u001B[0m"); // Red color for invalid input
        }
    }

    private void listTasks() {
        if (taskList.isEmpty()) {
            System.out.println("\u001B[31mNo tasks available.\u001B[0m"); // Red color for no tasks
            return;
        }

        System.out.printf("\u001B[34m%-5s %-20s %-15s %-12s %-10s%n\u001B[0m", "#", "Title", "Status", "Due Date", "Priority"); // Blue color for header
        System.out.println("\u001B[34m-----------------------------------------------------------------\u001B[0m"); // Blue color for separator
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String priorityColor;
            switch (task.getPriority().toLowerCase()) {
                case "high":
                    priorityColor = "\u001B[31m"; // Red color for high priority
                    break;
                case "medium":
                    priorityColor = "\u001B[33m"; // Yellow color for medium priority
                    break;
                case "low":
                    priorityColor = "\u001B[32m"; // Green color for low priority
                    break;
                default:
                    priorityColor = "\u001B[0m"; // Default color
            }
            String statusColor = task.isCompleted() ? "\u001B[32m" : "\u001B[33m"; // Green for completed, Yellow for pending
            System.out.printf("%-5d %-20s %s%-15s\u001B[0m %-12s %s%-10s\u001B[0m%n",
                i + 1,
                task.getTitle(),
                statusColor,
                task.isCompleted() ? "Completed" : "Pending",
                task.getDueDate(),
                priorityColor,
                task.getPriority());
        }
    }

    private void searchTasks() {
        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine().trim();

        taskList.stream()
            .filter(task -> task.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                           task.getPriority().equalsIgnoreCase(keyword))
            .forEach(task -> System.out.printf("- %s (Priority: %s, Due: %s)%n", task.getTitle(), task.getPriority(), task.getDueDate()));
    }

    private void sortTasksByPriority() {
        taskList.sort(Comparator.comparingInt(task -> {
            switch (task.getPriority().toLowerCase()) {
                case "high":
                    return 1;
                case "medium":
                    return 2;
                case "low":
                    return 3;
                default:
                    return 4; // For any undefined priority
            }
        }));
        System.out.println("Tasks sorted by priority.");
    }

    private void sortTasksByDueDate() {
        taskList.sort(Comparator.comparing(Task::getDueDate));
        System.out.println("Tasks sorted by due date.");
    }

    private void markTaskAsCompleted() {
        listTasks();
        System.out.print("Enter the task number to mark as completed: ");
        try {
            int index = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (index >= 0 && index < taskList.size()) {
                taskList.get(index).setCompleted();
                System.out.println("Task marked as completed.");
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
        System.out.print("\u001B[33mMarking task as completed"); // Yellow color for marking task
    for (int i = 0; i < 3; i++) {
        System.out.print(".");
        try {
        TimeUnit.MILLISECONDS.sleep(500); // Simulate loading effect
        } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        }
    }
    System.out.println("\n\u001B[32mTask marked as completed successfully!\u001B[0m"); // Green color for success message
    listTasks();
    }

    private void exitApp() {
    System.out.print("\u001B[33mExiting the application"); // Yellow color for exiting
    for (int i = 0; i < 3; i++) {
        System.out.print(".");
        try {
        TimeUnit.MILLISECONDS.sleep(500); // Simulate loading effect
        } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        }
    }
    System.out.println("\n\u001B[32mGoodbye!\u001B[0m"); // Green color for goodbye message
    System.exit(0);
        System.out.println("Exiting the application. Goodbye!");
        System.exit(0);
    }

    private void reviewTask() {
        listTasks();
        System.out.print("Enter the task number to review: ");
        try {
            int index = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (index >= 0 && index < taskList.size()) {
                Task task = taskList.get(index);
                System.out.println("\u001B[34mTask Details:\u001B[0m"); // Blue color for header
                System.out.println("Title: " + task.getTitle());
                System.out.println("Description: " + task.getDescription());
                System.out.println("Status: " + (task.isCompleted() ? "\u001B[32mCompleted\u001B[0m" : "\u001B[33mPending\u001B[0m"));
                System.out.println("Due Date: " + task.getDueDate());
                System.out.println("Priority: " + task.getPriority());
            } else {
                System.out.println("\u001B[31mInvalid task number.\u001B[0m"); // Red color for invalid task number
            }
        } catch (NumberFormatException e) {
            System.out.println("\u001B[31mInvalid input. Please enter a number.\u001B[0m"); // Red color for invalid input
        }
    }
}
/* 
class Task {
    private final String title;
    private final String description;
    private boolean isCompleted;
    private final LocalDate dueDate;
    private final String priority;

    public Task(String title, String description, boolean isCompleted, LocalDate dueDate, String priority) {
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getDescription() {
        return description;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getPriority() {
        return priority;
    }
}*/
