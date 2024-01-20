package OrderOperations;
import java.sql.*;
import java.util.Date;

public class DeleteOrder {
    public static void main(String[] args) {
        try {
            // Load the driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish a connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourDatabase", "username", "password");

            // Create a PreparedStatement
            String sql = "DELETE FROM Bookings WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Specify the ID of the booking you want to delete
            int bookingIdToDelete = 1;
            stmt.setInt(1, bookingIdToDelete);

            // Execute the SQL DELETE command
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Booking successfully deleted.");
            } else {
                System.out.println("No booking found with the given ID.");
            }

            // Close the connection
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
