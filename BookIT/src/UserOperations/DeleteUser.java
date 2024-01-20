package UserOperations;
import java.sql.*;

public class DeleteUser {
    public static void main(String[] args) {
        try {
            // Load the driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish a connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourDatabase", "username", "password");

            // Create a Statement
            Statement stmt = conn.createStatement();

            // Specify the ID of the user you want to delete
            int userIdToDelete = 1;

            // Execute the SQL DELETE command
            String sql = "DELETE FROM Users WHERE userId = " + userIdToDelete;
            int rowsAffected = stmt.executeUpdate(sql);

            if (rowsAffected > 0) {
                System.out.println("User successfully deleted.");
            } else {
                System.out.println("No user found with the given ID.");
            }

            // Close the connection
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}