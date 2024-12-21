import java.time.LocalDate;
import java.util.Arrays;
 public static String isValidDate(String dateStr) {
    // Check if dateStr is null or empty
    if (dateStr == null || dateStr.isEmpty()) {
        return "Not valid date";
    }
    if (dateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
        return "Valid date";
    } else {
        return "Not valid date";
    }
}
public class main {
    public static void main(String[] args) {
        TaskList x = new TaskList();
   String dateStr = "2024-12-18";

    // Validate the date
    if (isValidDate(dateStr).equals("Not valid date")) {  
        System.out.println("Invalid date format. Must be yyyy-MM-dd.");
    } else {
        // Create a task with the valid date
        Task task3 = new Task("Complete Java Assignment", "lll", true, LocalDate.parse(dateStr), "LOW");

        // Validate the priority
        String priorityCheck = TaskValidator.isPriority(task3.getPriority());
        if (priorityCheck.equals("Valid priority")) {
            x.addTask(task3);  
            System.out.println("Task added successfully.");
        } else {
            System.out.println(priorityCheck);  
        }
    }
    x.listTasks();

    }
}
