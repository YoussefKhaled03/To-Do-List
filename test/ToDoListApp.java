import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Scanner;
//import java.sql.Timestamp;

public class ToDoListApp {

    private static final Scanner scanner = new Scanner(System.in);
    private TaskList taskList = new TaskList();
    private User loggedInUser = null;

    public void start() {
        while (true) {
            System.out.println("\n--- To-Do List App ---");
            if (loggedInUser == null) {
                System.out.println("1. Login");
                System.out.println("2. Sign Up");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> login();
                    case 2 -> signUp();
                    case 3 -> {
                        System.out.println("Exiting the program. Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } else {
                // If the user is logged in, show the task options
                showTaskMenu();
            }
        }
    }

    public void showTaskMenu() {
        while (true) {
            System.out.println("\n--- To-Do List Menu ---");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. List Tasks");
            System.out.println("4. Search Tasks");
            System.out.println("5. Sort Tasks by Priority");
            System.out.println("6. Sort Tasks by Due Date");
            System.out.println("7. Mark Task as Completed");
            System.out.println("8. Logout");

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
                case 8 -> logout();

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

    // remove task
    public static void removeTask() {
        Scanner scanner = new Scanner(System.in);
    
        // Ask the user for the task ID
        System.out.print("Enter the ID of the task you would like to remove: ");
        int taskId = scanner.nextInt();
    
        String query = "DELETE FROM tasks WHERE id = ?";
    
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, taskId);
            int rowsAffected = pstmt.executeUpdate();
    
            if (rowsAffected > 0) {
                System.out.println("Task removed successfully.");
            } else {
                System.out.println("No task found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }


    private void listTasks() {
        String query = "SELECT id, title, description, completed, due_date, priority FROM tasks";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
    
            // Print table header
            System.out.println("\n--- Tasks ---");
            System.out.printf("%-5s %-20s %-30s %-10s %-15s %-10s%n", "ID", "Title", "Description", "Completed", "Due Date", "Priority");
            System.out.println("--------------------------------------------------------------------------");
    
            boolean tasksFound = false;
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                boolean completed = rs.getBoolean("completed");
                LocalDate dueDate = rs.getDate("due_date").toLocalDate();
                String priority = rs.getString("priority");
    
                // Print each task in a row of the table
                System.out.printf("%-5d %-20s %-30s %-10s %-15s %-10s%n", 
                    id, 
                    title, 
                    description, 
                    (completed ? "Yes" : "No"), 
                    dueDate, 
                    priority
                );
                tasksFound = true;
            }
    
            if (!tasksFound) {
                System.out.println("No tasks available.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
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


    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
    
        // Check the database for the username and password
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
    
            if (rs.next()) {
                loggedInUser = new User(rs.getString("username"), rs.getString("password"));
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid username or password.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void signUp() {
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();
    
        // Add the new user to the database
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            System.out.println("Sign-up successful! You can now log in.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void logout() {
        loggedInUser = null;
        System.out.println("Logged out successfully!");
    }

    public static void main(String[] args) {
        ToDoListApp app = new ToDoListApp();
        app.start();
    }
}

