
import java.time.LocalDate;

public class Task implements Comparable<Task> {
    private String title;
    private String description;
    private boolean Completion_status;
    private LocalDate dueDate;
    private String priority;

public Task(String title, String description, boolean Completion_status, LocalDate dueDate, String priority){
    this.title = title;
    this.description = description;
    this.Completion_status = Completion_status;
    this.dueDate = dueDate;
    if (priority.equals("LOW") || priority.equals("MEDIUM") || priority.equals("HIGH"))
         {
            this.priority = priority;
        } else {
            System.out.println("Invalid priority. Must be LOW, MEDIUM, or HIGH.");
        }
}

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", completionStatus=" + Completion_status +
                ", dueDate=" + dueDate +
                ", priority='" + priority + '\'' +
                '}';
    }

  public void markAsCompleted() {
        this.Completion_status = true;
    }

    @Override
    public int compareTo(Task other) {
        int priorityComparison = this.priority.compareTo(other.priority);
        if (priorityComparison != 0) {
            return priorityComparison;
        }
        return this.dueDate.compareTo(other.dueDate);
    }
    }
