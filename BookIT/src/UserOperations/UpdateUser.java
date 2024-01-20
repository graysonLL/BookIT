package UserOperations;

import java.sql.*;

public class UpdateUser {
    public static void main(String[] args) {
        try {
            // Load the driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish a connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourDatabase", "username", "password");

            // Create a PreparedStatement
            String sql = "UPDATE Users SET firstName = ?, lastName = ?, email = ?, phoneNumber = ? WHERE userId = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Specify the new user data
            stmt.setString(1, "NewFirstName");
            stmt.setString(2, "NewLastName");
            stmt.setString(3, "newemail@example.com");
            stmt.setString(4, "1234567890");

            // Specify the ID of the user you want to update
            stmt.setInt(5, 1);

            // Execute the SQL UPDATE command
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User successfully updated.");
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

