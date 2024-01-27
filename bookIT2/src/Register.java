import Entities.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class Register {
    public Register() {
        registerUser();
    }

    private void registerUser() {
        Scanner input = new Scanner(System.in);
        System.out.print("|First Name: ");
        String firstName = input.nextLine();
        System.out.print("|Last Name: ");
        String lastName = input.nextLine();
        System.out.print("|Email: ");
        String email = input.nextLine();
        System.out.print("|Phone Number: ");
        String phone = input.nextLine();
        System.out.print("|Password: ");
        String password = input.nextLine();
        System.out.print("|Confirm Password: ");
        String confirmPassword = input.nextLine();


        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            System.out.println("Please enter all fields");
            return;
        }

        if (!password.equals(confirmPassword)) {
            System.out.println("Confirm password does not match");
            System.out.println("Failed to register new user");
            return;
        }

        user = addUserToDatabase(firstName, lastName, email, phone, password);

        if (user == null) {
            System.out.println("Failed to register new user");
        }
    }

    public User user;

    private User addUserToDatabase(String firstName, String lastName, String email, String phone, String password) {
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost/bookit?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO user (first_name, last_name, email, phone_number, password) " + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, password);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.firstName = firstName;
                user.lastName = lastName;
                user.email = email;
                user.phone = phone;
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}