import javax.swing.*;
import javax.swing.plaf.basic.BasicCheckBoxUI;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class ToDoListGui {
    private TaskList taskList = new TaskList(); // Core Logic

    // Swing Components
    private JFrame frame;
    private DefaultListModel<Task> listModel;
    private JList<Task> taskJList;

    public ToDoListGui() {
        initializeGUI();
    }

    private void initializeGUI() {
        // Main Frame
        frame = new JFrame("To-Do List Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel("To-Do List", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Task List Display
        listModel = new DefaultListModel<>();
        taskJList = new JList<>(listModel);
        taskJList.setCellRenderer(new TaskRenderer()); // Custom Renderer
        taskJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add mouse click listener to toggle completion
        taskJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = taskJList.locationToIndex(e.getPoint());
                if (index >= 0) {
                    Task task = listModel.getElementAt(index);
                    task.markAsCompleted(); // Toggle completion status
                    refreshTaskList();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(taskJList);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3, 10, 10));

        JButton addButton = new JButton("Add Task");
        JButton removeButton = new JButton("Remove Task");
        JButton searchButton = new JButton("Search Task");
        JButton sortPriorityButton = new JButton("Sort by Priority");
        JButton sortDateButton = new JButton("Sort by Due Date");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(sortPriorityButton);
        buttonPanel.add(sortDateButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        addButton.addActionListener(e -> addTask());
        removeButton.addActionListener(e -> removeTask());
        searchButton.addActionListener(e -> searchTask());
        sortPriorityButton.addActionListener(e -> sortByPriority());
        sortDateButton.addActionListener(e -> sortByDueDate());

        frame.setVisible(true);
    }

    // Action Methods
    private void addTask() {
        JTextField titleField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField dueDateField = new JTextField();
        JComboBox<String> priorityBox = new JComboBox<>(new String[]{"LOW", "MEDIUM", "HIGH"});

        Object[] fields = {
            "Title:", titleField,
            "Description:", descriptionField,
            "Due Date (YYYY-MM-DD):", dueDateField,
            "Priority:", priorityBox
        };

        int option = JOptionPane.showConfirmDialog(frame, fields, "Add New Task", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String title = titleField.getText();
                String description = descriptionField.getText();
                LocalDate dueDate = LocalDate.parse(dueDateField.getText());
                String priority = (String) priorityBox.getSelectedItem();

                Task task = new Task(title, description, false, dueDate, priority);
                taskList.addTask(task);
                refreshTaskList();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Invalid input! Please check your fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void removeTask() {
        int selectedIndex = taskJList.getSelectedIndex();
        if (selectedIndex >= 0) {
            taskList.removeTask(selectedIndex);
            refreshTaskList();
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a task to remove.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void searchTask() {
        String keyword = JOptionPane.showInputDialog(frame, "Enter keyword (title/priority/due date):");
        if (keyword != null && !keyword.trim().isEmpty()) {
            Task[] results = taskList.searchTasks(keyword.trim());
            listModel.clear();
            for (Task task : results) {
                if (task != null) {
                    listModel.addElement(task);
                }
            }
        }
    }

    private void sortByPriority() {
        taskList.sortTasks((t1, t2) -> t1.getPriority().compareTo(t2.getPriority()));
        refreshTaskList();
    }

    private void sortByDueDate() {
        taskList.sortTasks((t1, t2) -> t1.getDueDate().compareTo(t2.getDueDate()));
        refreshTaskList();
    }

    private void refreshTaskList() {
        listModel.clear();
        for (int i = 0; i < taskList.getSize(); i++) {
            listModel.addElement(taskList.getTask(i));
        }
    }

    // Custom Renderer for Tasks
    private static class TaskRenderer extends JCheckBox implements ListCellRenderer<Task> {
        @Override
        public Component getListCellRendererComponent(JList<? extends Task> list, Task task, int index, boolean isSelected, boolean cellHasFocus) {
            setText(task.getTitle() + " - " + task.getDueDate() + " [" + task.getPriority() + "]");
            setSelected(task.isCompleted());

            // Change color and strikethrough for completed tasks
            if (task.isCompleted()) {
                setForeground(Color.GRAY);
                setFont(new Font("Arial", Font.ITALIC, 12));
            } else {
                setForeground(Color.BLACK);
                setFont(new Font("Arial", Font.PLAIN, 12));
            }

            setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
            setEnabled(list.isEnabled());
            return this;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoListGui::new);
    }
}
