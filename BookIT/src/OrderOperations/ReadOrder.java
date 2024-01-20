package OrderOperations;
import Connection.DatabaseUtil;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class ReadOrder extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DatabaseUtil.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Orders");

            while (rs.next()) {
                System.out.println("Order ID: " + rs.getInt("orderId"));
                System.out.println("User ID: " + rs.getInt("userId"));
                System.out.println("Product ID: " + rs.getInt("productId"));
                System.out.println("Quantity: " + rs.getInt("quantity"));
                System.out.println();
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}
