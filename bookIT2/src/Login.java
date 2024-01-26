import Entities.User;

import java.sql.*;
import java.util.Scanner;

public class Login {
    public User user;

    public Login() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Email: ");
        String email = input.nextLine();
        System.out.print("Enter Password: ");
        String password = input.nextLine();

        user = getAuthenticatedUser(email, password);
    }

    private User getAuthenticatedUser(String email, String password) {
        User user = null;

        final String DB_URL = "jdbc:mysql://localhost/bookit?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            //connection successful

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM user WHERE email=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.userId = resultSet.getInt("user_id");
                user.firstName = resultSet.getString("first_name");
                user.lastName = resultSet.getString("last_name");
                user.email = resultSet.getString("email");
                user.phone = resultSet.getString("phone_number");
                user.password = resultSet.getString("password");
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
