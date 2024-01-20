package UserOperations;
import Connection.DatabaseUtil;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class ReadUser extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DatabaseUtil.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Users");

            while (rs.next()) {
                System.out.println("User ID: " + rs.getInt("userId"));
                System.out.println("First Name: " + rs.getString("firstName"));
                System.out.println("Last Name: " + rs.getString("lastName"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Phone Number: " + rs.getString("phoneNumber"));
                System.out.println();
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}

