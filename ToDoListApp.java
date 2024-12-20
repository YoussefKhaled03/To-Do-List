import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class ToDoListApp {

    private static final Scanner scanner = new Scanner(System.in);
    private TaskList taskList = new TaskList();

    public void start() {
        while (true) {
            System.out.println("\n--- To-Do List Menu ---");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. List Tasks");
            System.out.println("4. Search Tasks");
            System.out.println("5. Sort Tasks by Priority");
            System.out.println("6. Sort Tasks by Due Date");
            System.out.println("7. Mark Task as Completed");
            System.out.println("8. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addTask();
                case 2 -> removeTask();
                case 3 -> listTasks();
                case 4 -> searchTasks();
                case 5 -> sortTasksByPriority();
                case 6 -> sortTasksByDueDate();
                case 7 -> markTaskAsCompleted();
                case 8 -> {
                    System.out.println("Exiting To-Do List. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Add user
    public static void addUser(User user) {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addTask() {
        scanner.nextLine(); // Consume newline
        System.out.print("Enter task title: ");
        String title = scanner.nextLine().trim();

        System.out.print("Enter task description: ");
        String description = scanner.nextLine().trim();

        // Validate due date
        LocalDate dueDate = null;
        boolean validDate = false;
        while (!validDate) {
            System.out.print("Enter due date (YYYY-MM-DD): ");
            String dueDateInput = scanner.nextLine().trim();
            try {
                dueDate = LocalDate.parse(dueDateInput);
                validDate = true;
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use the format YYYY-MM-DD.");
            }
        }

        // Validate priority
        String priority = null;
        boolean validPriority = false;
        while (!validPriority) {
            System.out.print("Enter priority (LOW, MEDIUM, HIGH): ");
            priority = scanner.nextLine().trim().toUpperCase();
            if (priority.equals("LOW") || priority.equals("MEDIUM") || priority.equals("HIGH")) {
                validPriority = true;
            } else {
                System.out.println("Invalid priority. Please enter LOW, MEDIUM, or HIGH.");
            }
        }

        taskList.addTask(new Task(title, description, false, dueDate, priority));
        System.out.println("Task added successfully!");
    }

    private void removeTask() {
        listTasks();
        System.out.print("Enter the number of the task to remove: ");
        int index = scanner.nextInt() - 1;

        if (index >= 0 && index < taskList.getSize()) {
            taskList.removeTask(index);
            System.out.println("Task removed successfully!");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private void listTasks() {
        System.out.println("\n--- Task List ---");
        if (taskList.getSize() == 0) {
            System.out.println("No tasks available.");
        } else {
            taskList.listTasks();
        }
    }

    private void searchTasks() {
        System.out.print("Enter keyword to search (title, priority, or due date): ");
        String keyword = scanner.nextLine();

        Task[] matchingTasks = taskList.searchTasks(keyword);
        if (matchingTasks.length > 0) {
            System.out.println("\n--- Search Results ---");
            for (Task task : matchingTasks) {
                if (task != null) {
                    System.out.println(task);
                }
            }
        } else {
            System.out.println("No tasks match the keyword.");
        }
    }

    private void sortTasksByPriority() {
        taskList.sortTasks((t1, t2) -> t1.getPriority().compareTo(t2.getPriority()));
        System.out.println("Tasks sorted by priority.");
        listTasks();
    }

    private void sortTasksByDueDate() {
        taskList.sortTasks((t1, t2) -> t1.getDueDate().compareTo(t2.getDueDate()));
        System.out.println("Tasks sorted by due date.");
        listTasks();
    }

    private void markTaskAsCompleted() {
        listTasks();
        System.out.print("Enter the number of the task to mark as completed: ");
        int index = scanner.nextInt() - 1;

        if (index >= 0 && index < taskList.getSize()) {
            taskList.getTask(index).markAsCompleted();
            System.out.println("Task marked as completed!");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public static void main(String[] args) {
        ToDoListApp app = new ToDoListApp();
        app.start();
    }
}

