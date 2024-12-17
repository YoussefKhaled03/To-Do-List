import java.util.Arrays;
import java.util.Comparator;


public class TaskList {
    private Task[] tasks;
    private int size;

    public TaskList() {
        tasks = new Task[10];
        size = 0;
    }

    public void listTasks() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + 1 + ". " + tasks[i]);
        }
    }
    

    public void addTask(Task task) {
        if (size == tasks.length) {
            Task[] newTasks = new Task[size * 2];
            for (int i = 0; i < size; i++) {
                newTasks[i] = tasks[i];
            }
            tasks = newTasks;
        }
        tasks[size] = task;
        size++;
    }

    public Task getTask(int index) {
        return tasks[index];
    }

    public void removeTask(int index) {
        for (int i = index; i < size - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        size--;
    }

    public void updateTask(int index, Task task) {
        tasks[index] = task;
    }

    public void sortTasks(Comparator<Task> comparator) {
        Arrays.sort(tasks, 0, size, comparator);
    }

     public Task[] searchTasks(String keyword) {
    Task[] matchingTasks = new Task[size];  
    int count = 0;  

    for (int i = 0; i < size; i++) {
       if (tasks[i] != null && (tasks[i].getTitle().equals(keyword.trim()) || 
                         tasks[i].getPriority().equals(keyword.trim()) || 
                         tasks[i].getDueDate().toString().equals(keyword.trim()))) {
            matchingTasks[count++] = tasks[i];  
        }
    }

    return matchingTasks;  
}
    
}
