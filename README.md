# To-Do List Application

### **Project Description**
This is a **terminal-based To-Do List application** implemented in Java, designed with Object-Oriented Programming (OOP) principles. The application allows users to manage tasks efficiently by adding priorities, sorting, searching, and saving tasks to a file for future use.

---

### **Features**
- **Add Tasks**: Create new tasks with a title, description, due date, and priority.
- **Remove Tasks**: Delete tasks by selecting from the list.
- **List Tasks**: View all tasks with their details, including priority and due date. Tasks are color-coded based on their priority and status.
  - **High Priority**: Red
  - **Medium Priority**: Yellow
  - **Low Priority**: Green
  - **Pending Status**: Yellow
  - **Completed Status**: Green
- **Mark as Completed**: Mark tasks as completed to track progress.
- **Review Tasks**: View detailed information about a specific task, including its description.
- **Search Tasks**: Search for tasks by title, priority, or due date.
- **Sort Tasks**: Organize tasks by priority or due date.
- **Save/Load Tasks**: Save tasks to a file and load them back later.
- **Exit Application**: Exit the application with a loading effect.

---

### **Project Structure**

#### **1. Task Class**
Manages individual tasks with the following fields:
- Title
- Description
- Completion status
- Due date
- Priority (LOW, MEDIUM, HIGH)

#### **2. TaskList Class**
Handles operations on the list of tasks, including:
- Adding, removing, and listing tasks.
- Sorting tasks by priority or due date.
- Searching tasks by title, priority, or due date.
- Saving and loading tasks to/from a file.

#### **3. ToDoListApp Class**
Provides a user-friendly terminal interface to:
- Navigate the menu.
- Interact with the `TaskList` class to perform actions.

---

### **Setup Instructions**

#### **Requirements**
- Java Development Kit (JDK) 8 or later.
- A terminal or command prompt.

#### **Steps to Run**
1. Clone the repository or download the source code.
2. Navigate to the project directory in your terminal.
3. Compile the project using the following command:
   ```bash
   javac *.java
   ```
4. Run the application using:
   ```bash
   java ToDoListApp
   ```

---

### **How to Use the Application**

1. **Main Menu**: The main menu provides options to add, remove, list, review, and mark tasks as completed, search, sort, and exit the application.
2. **Adding Tasks**: When adding a task, you will be prompted to enter the title, description, due date, and priority.
3. **Removing Tasks**: To remove a task, enter the task number from the list.
4. **Listing Tasks**: Lists all tasks with color-coded priorities and statuses.
5. **Reviewing Tasks**: Enter the task number to view detailed information about the task.
6. **Marking Tasks as Completed**: Enter the task number to mark it as completed.
7. **Searching Tasks**: Search tasks by title, priority, or due date.
8. **Sorting Tasks**: Sort tasks by priority or due date.
9. **Exiting**: Exit the application with a visual loading effect.

3. Save tasks to a file to preserve your list and load it later to continue managing tasks.

---

### **Team Contribution**
This project was developed by a team of five students:

| **Student**  | **Contribution**                                                            |
|--------------|-----------------------------------------------------------------------------|
| **Mohamed Abououf** | Implemented the `Task` class, including fields and sorting logic.    |
| **Youssef Khaled** | Developed the `TaskList` class.  |
| **Nour Gamal** | Built the menu system in `ToDoListApp` for user interaction.              |
| **Nada Hassan** | Implemented methods for adding, removing, and listing tasks, Testing the app.             |
| **Lourin** | Added the search feature, integrated it into the app, and implemented validation. |

---

### **Future Enhancements**
- Add support for recurring tasks.
- Implement a graphical user interface (GUI).
- Allow task sharing via email or other platforms.

---

### **Acknowledgments**
- **Java Documentation**: For providing excellent resources on Java features.
- **Our Team**: For collaboration and teamwork throughout the project.

---

### **Contact**
For questions or feedback, please, feel free to contact us


