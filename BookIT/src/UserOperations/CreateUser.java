package UserOperations;
import Connection.DatabaseUtil;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");

        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "INSERT INTO Users (firstName, lastName, email, phoneNumber) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, phoneNumber);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}
