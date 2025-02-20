import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLConnectionExample {
    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:mysql://localhost:3306/exampledb"; // Replace 'exampledb' with your DB name
        String username = "root"; // Replace with your MySQL username
        String password = ""; // Replace with your MySQL password

        // Connection and SQL execution
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connected to the database!");

            // Example query: Insert a record
            String insertQuery = "INSERT INTO users (name, email) VALUES (?, ?)";
            try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                insertStmt.setString(1, "John Doe");
                insertStmt.setString(2, "john.doe@example.com");
                insertStmt.executeUpdate();
                System.out.println("Record inserted successfully!");
            }

            // Example query: Fetch records
            String selectQuery = "SELECT * FROM users";
            try (PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
                 ResultSet resultSet = selectStmt.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }
}
