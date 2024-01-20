package OrderOperations;
import java.sql.*;

public class UpdateOrder {
    public static void main(String[] args) {
        try {
            // Load the driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish a connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourDatabase", "username", "password");

            // Create a PreparedStatement
            String sql = "UPDATE Orders SET quantity = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Specify the new quantity and the ID of the order you want to update
            int newQuantity = 2;
            int orderIdToUpdate = 1;
            stmt.setInt(1, newQuantity);
            stmt.setInt(2, orderIdToUpdate);

            // Execute the SQL UPDATE command
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Order successfully updated.");
            } else {
                System.out.println("No order found with the given ID.");
            }

            // Close the connection
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
