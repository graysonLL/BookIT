import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm extends JDialog {
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JButton btnOk;
    private JButton btnCancel;
    private JPanel loginPanel;

    public LoginForm(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(1280,720));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());

                user = getAuthenticatedUser(email, password);

                if(user != null) {
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginForm.this,"Email or password invalid","Try again",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public User user;
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
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                user = new User();
                user.firstName = resultSet.getString("first_name");
                user.lastName = resultSet.getString("last_name");
                user.email = resultSet.getString("email");
                user.phone = resultSet.getString("phone_number");
                user.password = resultSet.getString("password");
            }

            stmt.close();
            conn.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm(null);
        User user = loginForm.user;
        if(user != null) {
            System.out.println("Succcessful authentication of "+user.firstName+" "+user.lastName);
            System.out.println("Name: "+user.firstName+" "+user.lastName);
            System.out.println("Email: "+user.email);
            System.out.println("Phone: "+user.phone);
        } else {
            System.out.println("authentication cancelled");
        }
    }
}
