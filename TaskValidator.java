public class TaskValidator {
    public static String isPriority(String priority) {
        if (priority == null) {
            return "Invalid priority";  // Return "Invalid priority" if it's null
        }
        if (priority.matches("LOW|MEDIUM|HIGH")) {
            return "Valid priority";
        } else {
            return "Invalid priority";
        }
    }
}
