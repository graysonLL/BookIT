package RoomOperations;
import java.sql.*;

public class UpdateRoomStatus {
    public static void main(String[] args) {
        try {
            // Load the driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish a connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourDatabase", "username", "password");

            // Create a PreparedStatement
            String sql = "UPDATE Rooms SET status = ? WHERE roomNumber = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Specify the room number you want to update
            int roomNumberToUpdate = Integer.parseInt(args[0]);
            stmt.setBoolean(1, false);
            stmt.setInt(2, roomNumberToUpdate);

            // Execute the SQL UPDATE command
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Room status successfully updated.");
            } else {
                System.out.println("No room found with the given number.");
            }

            // Close the connection
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
