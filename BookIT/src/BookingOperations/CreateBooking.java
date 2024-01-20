package BookingOperations;
import java.sql.*;

public class CreateBooking {
    public static void main(String[] args) {
        try {
            // Load the driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish a connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourDatabase", "username", "password");

            // Create a PreparedStatement
            String sql = "INSERT INTO Orders (userId, roomNumber, roomType, numberOfDays, totalCost) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Specify the order data
            int userId = 1;
            int roomNumber = Integer.parseInt(args[0]);
            String roomType = determineRoomType(roomNumber);
            int numberOfDays = Integer.parseInt(args[1]);
            double totalCost = calculateTotalCost(roomType, numberOfDays);

            stmt.setInt(1, userId);
            stmt.setInt(2, roomNumber);
            stmt.setString(3, roomType);
            stmt.setInt(4, numberOfDays);
            stmt.setDouble(5, totalCost);

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

    private static String determineRoomType(int roomNumber) {
        if (roomNumber >= 100 && roomNumber <= 120) {
            return "OneBedroom";
        } else if (roomNumber >= 200 && roomNumber <= 220) {
            return "TwoBedroom";
        } else if (roomNumber >= 300 && roomNumber <= 320) {
            return "ThreeBedroom";
        } else if (roomNumber >= 400 && roomNumber <= 420) {
            return "PremiumOneBedroom";
        } else {
            throw new IllegalArgumentException("Invalid room number: " + roomNumber);
        }
    }

    private static double calculateTotalCost(String roomType, int numberOfDays) {
        switch (roomType) {
            case "OneBedroom":
                return numberOfDays * 2000;
            case "TwoBedroom":
                return numberOfDays * 2500;
            case "ThreeBedroom":
                return numberOfDays * 3000;
            case "PremiumOneBedroom":
                return numberOfDays * 5000;
            default:
                throw new IllegalArgumentException("Invalid room type: " + roomType);
        }
    }
}
