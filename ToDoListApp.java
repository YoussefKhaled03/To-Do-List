import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
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
            scanner.nextLine();

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
    //add method
    private void addTask() {
        //title
        System.out.print("Enter task title: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("Task title cannot be empty. Please try again.");
            return;
        }
        //description
        System.out.print("Enter task description: ");
        String description = scanner.nextLine().trim();

        LocalDate dueDate;
        while (true) {
            try {
                // year
                System.out.print("Enter year (YYYY): ");
                int year = Integer.parseInt(scanner.nextLine().trim());

                //month
                System.out.print("Enter month (1-12): ");
                int month = Integer.parseInt(scanner.nextLine().trim());
                if (month < 1 || month > 12) {
                    System.out.println("Invalid month. Please try again.");
                    continue;
                }

                //day
                System.out.print("Enter day (1-31): ");
                int day = Integer.parseInt(scanner.nextLine().trim());
                if (day < 1 || day > 31) {
                    System.out.println("Invalid day. Please try again.");
                    continue;
                }

                //comparing the due date with the current date
                dueDate = LocalDate.of(year, month, day);

                if (!dueDate.isBefore(LocalDate.now())) {
                    break;
                } else {
                    System.out.println("Due date cannot be in the past. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
            }
        }

        //priority
        String priority;
        while (true) {
            System.out.print("Enter priority\n1. LOW\n2. MEDIUM\n3.HIGH): ");
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
                System.out.println("Invalid input. Please enter 1 for LOW, 2 for MEDIUM, or 3 for HIGH.");
            }
        }
        //creating a new task
        Task newTask = new Task(title, description, false, dueDate, priority);
        taskList.addTask(newTask);
        System.out.println("Task added successfully.");
    }

    //remove method
    private void removeTask() {
        if (taskList.getSize() == 0) {
            System.out.println("No tasks to remove.");
            return;
        }
        listTasks(); // Display the list of tasks
        System.out.print("Enter the number of the task to remove: ");
        int index;
        try {
            index = Integer.parseInt(scanner.nextLine()) - 1; // Convert input to zero-based index
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return;
        }
    
        if (index >= 0 && index < taskList.getSize()) {
            taskList.removeTask(index); 
            System.out.println("Task removed successfully!");
            listTasks();
        } else {
            System.out.println("Invalid task number. Please choose a valid number from the list.");
        }
    }

    //list method
    private void listTasks() {
        System.out.println("\n--- Task List ---");
        if (taskList.getSize() == 0) {
            System.out.println("No tasks available.");
        } else {
            System.out.printf("%-20s %-30s %-15s %-15s %-10s%n", "Task Name", "Description", "Status", "Due Date", "Priority");
            System.out.println("--------------------------------------------------------------------------------------------------");
            taskList.listTasks();

        }
    }
    

    private void searchTasks() {
        System.out.print("Enter keyword to search (title, priority, or due date): ");
        String keyword = scanner.nextLine();

        Task[] matchingTasks = taskList.searchTasks(keyword);
        if (matchingTasks.length > 0) {
            System.out.println("\n--- Search Results ---");
            System.out.printf("%-20s %-30s %-15s %-15s %-10s%n","Task Name", "Description", "Status", "Due Date", "Priority");
            System.out.println("--------------------------------------------------------------------------------------------------");
            for (Task task : matchingTasks) {
                if (task != null) { 
                    System.out.printf("%-20s %-30s %-15s %-15s %-10s%n", 
                                      task.getTitle(), 
                                      task.getDescription(), 
                                      task.isCompleted() ? "Completed" : "Pending",
                                      task.getDueDate() != null ? task.getDueDate().toString() : "No due date",
                                      task.getPriority());
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