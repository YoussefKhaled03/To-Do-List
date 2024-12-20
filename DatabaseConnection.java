import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
       private static final String URL = "ToDoList.db";  // Path to SQLite database

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }
    
}
