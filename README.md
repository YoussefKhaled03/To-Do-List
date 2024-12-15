# To-Do List Application

### **Project Description**
This is a **terminal-based To-Do List application** implemented in Java, designed with Object-Oriented Programming (OOP) principles. The application allows users to manage tasks efficiently by adding priorities, sorting, searching, and saving tasks to a file for future use.

---

### **Features**
- **Add Tasks**: Create new tasks with a title, description, due date, and priority.
- **Remove Tasks**: Delete tasks by selecting from the list.
- **List Tasks**: View all tasks with their details, including priority and due date.
- **Mark as Completed**: Mark tasks as completed to track progress.
- **Search Tasks**: Search for tasks by title, priority, or due date.
- **Sort Tasks**: Organize tasks by priority or due date.
- **Save/Load Tasks**: Save tasks to a file and load them back later.

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

1. Run the program to display the main menu:
   - Add Task
   - Remove Task
   - List Tasks
   - Search Tasks
   - Sort Tasks by Priority
   - Save/Load Tasks
   - Exit

2. Follow the on-screen instructions to:
   - Input task details (e.g., title, description, priority).
   - Perform actions like searching, sorting, and saving.

3. Save tasks to a file to preserve your list and load it later to continue managing tasks.

---

### **Team Contribution**
This project was developed by a team of five students:

| **Student**  | **Contribution**                                                            |
|--------------|-----------------------------------------------------------------------------|
| **Mohamed Abououf** | Implemented the `Task` class, including fields and sorting logic.    |
| **Youssef Khaled** | Developed the `TaskList` class, including sorting and file handling.  |
| **Nour Gamal** | Built the menu system in `ToDoListApp` for user interaction.              |
| **Nada Hassan** | Implemented methods for adding, removing, and listing tasks.             |
| **Loren ** | Added the search feature and integrated it into the app.                      |

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


