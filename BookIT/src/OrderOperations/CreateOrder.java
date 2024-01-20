package OrderOperations;
import java.sql.*;

public class CreateOrder {
    public static void main(String[] args) {
        try {
            // Load the driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish a connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourDatabase", "username", "password");

            // Create a PreparedStatement
            String sql = "INSERT INTO Orders (userId, productId, quantity) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Specify the order data
            stmt.setInt(1, 1);
            stmt.setInt(2, 1);
            stmt.setInt(3, 1);

            // Execute the SQL INSERT command
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Order successfully created.");
            } else {
                System.out.println("Failed to create order.");
            }

            // Close the connection
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

