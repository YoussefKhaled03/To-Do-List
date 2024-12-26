import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ToDoListApp {
    private final ArrayList<Task> taskList = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ToDoListApp app = new ToDoListApp();
        app.run();
    }

    public void run() {
        while (true) {
            displayMenu();
            int choice = getUserChoice();
            handleChoice(choice);
        }
    }

    private void displayMenu() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║           To-Do List Menu          ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.println("║ 1. Add Task                        ║");
        System.out.println("║ 2. Remove Task                     ║");
        System.out.println("║ 3. List Tasks                      ║");
        System.out.println("║ 4. Search Tasks                    ║");
        System.out.println("║ 5. Sort Tasks by Priority          ║");
        System.out.println("║ 6. Sort Tasks by Due Date          ║");
        System.out.println("║ 7. Mark Task as Completed          ║");
        System.out.println("║ 8. Exit                            ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.print("Choose an option: ");
    }

    private int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
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
            case 4 -> searchTasks();
            case 5 -> sortTasksByPriority();
            case 6 -> sortTasksByDueDate();
            case 7 -> markTaskAsCompleted();
            case 8 -> exitApp();
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    private void addTask() {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();

        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        LocalDate dueDate = getValidDueDate();

        System.out.print("Enter task priority (LOW, MEDIUM, HIGH): ");
        String priority = scanner.nextLine().toUpperCase();

        if (!priority.matches("LOW|MEDIUM|HIGH")) {
            System.out.println("Invalid priority. Task not added.");
            return;
        }

        Task newTask = new Task(title, description, false, dueDate, priority);
        taskList.add(newTask);
        System.out.println("Task added successfully.");
    }

    private LocalDate getValidDueDate() {
        while (true) {
            System.out.print("Enter due date (YYYY-MM-DD): ");
            String dateInput = scanner.nextLine();
            try {
                LocalDate date = LocalDate.parse(dateInput);
                if (date.isBefore(LocalDate.now())) {
                    System.out.println("Due date cannot be in the past. Try again.");
                } else {
                    return date;
                }
            } catch (Exception e) {
                System.out.println("Invalid date format. Try again.");
            }
        }
    }

    private void removeTask() {
        listTasks();
        System.out.print("Enter the task number to remove: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index >= 0 && index < taskList.size()) {
                taskList.remove(index);
                System.out.println("Task removed successfully.");
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    private void listTasks() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                             Task List                              ║");
        System.out.println("╠════╦══════════════════╦══════════════╦════════════╦════════════════╣");
        System.out.println("║ #  ║ Title            ║ Status       ║ Due Date   ║ Priority       ║");
        System.out.println("╠════╬══════════════════╬══════════════╬════════════╬════════════════╣");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.printf("║ %-2d ║ %-16s ║ %-12s ║ %-10s ║ %-14s ║%n",
                i + 1,
                task.getTitle(),
                task.isCompleted() ? "Completed" : "Pending",
                task.getDueDate(),
                task.getPriority()
            );
        }
        System.out.println("╚════╩══════════════════╩══════════════╩════════════╩════════════════╝");
    }

    private void searchTasks() {
        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine();
        System.out.println("\nSearch Results:");
        taskList.stream()
            .filter(task -> task.getTitle().contains(keyword) || task.getPriority().equalsIgnoreCase(keyword))
            .forEach(System.out::println);
    }

    private void sortTasksByPriority() {
        taskList.sort(Comparator.comparing(Task::getPriority));
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
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index >= 0 && index < taskList.size()) {
                taskList.get(index).markAsCompleted();
                System.out.println("Task marked as completed.");
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    private void exitApp() {
        System.out.println("Exiting To-Do List App. Goodbye!");
        System.exit(0);
    }
}
