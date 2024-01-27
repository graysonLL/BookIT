import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class UserOperations {
    Scanner input = new Scanner(System.in);

    public UserOperations(int choice, int userId) {
        switch (choice) {
            case 1 :
                updateEmail(userId);
                break;
            case 2 :
                updatePassword(userId);
                break;
            case 3 :
                updateFirstName(userId);
                break;
            case 4 :
                updateLastName(userId);
                break;
            case 5 :
                updatePhoneNumber(userId);
                break;
            case 0 :
                System.out.println("Exit");
                return;
            default:
                System.out.println("Please select a valid choice");
                break;
        }
    }


    public void updateEmail(int userId) {
        final String DB_URL = "jdbc:mysql://localhost/bookit?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        System.out.println("Enter new email: ");
        String newEmail = input.nextLine();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "UPDATE user SET email=? WHERE user_id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, newEmail);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
            System.out.println("Successfully updated email\n\n");
            conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    public void updatePassword(int userId) {
        final String DB_URL = "jdbc:mysql://localhost/bookit?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        System.out.println("Enter new password: ");
        String newPassword = input.nextLine();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "UPDATE user SET password=? WHERE user_id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
            System.out.println("Successfully updated password\n\n");
            conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void updateFirstName(int userId) {
        final String DB_URL = "jdbc:mysql://localhost/bookit?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        System.out.println("Enter new first name: ");
        String newFirstName = input.nextLine();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "UPDATE user SET first_name=? WHERE user_id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, newFirstName);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
            System.out.println("Successfully updated first name\n\n");
            conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void updateLastName(int userId) {
        final String DB_URL = "jdbc:mysql://localhost/bookit?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        System.out.println("Enter new last name: ");
        String newLastName = input.nextLine();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "UPDATE user SET last_name=? WHERE user_id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, newLastName);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
            System.out.println("Successfully updated last name\n\n");
            conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePhoneNumber(int userId) {
        final String DB_URL = "jdbc:mysql://localhost/bookit?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        System.out.println("Enter new phone number: ");
        String newPhone = input.nextLine();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "UPDATE user SET phone_number=? WHERE user_id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, newPhone);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
            System.out.println("Successfully updated phone number\n\n");
            conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
