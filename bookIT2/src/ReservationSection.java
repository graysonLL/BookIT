import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class ReservationSection extends JDialog {
    private JComboBox cbRoomType;
    private JButton btnReserve;
    private JPanel reservationPanel;
    private JButton btnOk;
    private JTextField tfTotalDays;
    public String roomTypeSelectedItem = ""; // Assign a default value

    public ReservationSection(JFrame parent) {
        super(parent);
        setTitle("Reservation");
        setContentPane(reservationPanel);
        setMinimumSize(new Dimension(1280,720));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        cbRoomType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomTypeSelectedItem = (String) cbRoomType.getSelectedItem();
            }
        });

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                room = getRoom(roomTypeSelectedItem);
                System.out.println(room.roomNumber);

                String totalDaysStr = tfTotalDays.getText();
                int totalDays = Integer.parseInt(totalDaysStr); // Parse string to int
                System.out.println(totalDays);
                double totalCost = room.price*totalDays;

                createBooking(room.roomNumber, roomTypeSelectedItem, totalDays, totalCost);
            }
        });

        setVisible(true);
    }

    private Booking booking;
    //(int roomNumber, int userId, String roomType, Date startDate, int totalDays, double totalCost)
    private void createBooking(int roomNumber, String roomType, int totalDays, double totalCost) {
        final String DB_URL = "jdbc:mysql://localhost/bookit?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";
        // add user_id and one more ? in values
        String sql = "INSERT INTO booking (room_number, user_id, room_type, start_date, total_days, total_cost) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Date specificDate = new Date(124, 0, 24);

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, roomNumber);
            statement.setInt(2, 1);
            statement.setString(3, roomType);
            statement.setDate(4, specificDate);
            statement.setInt(5, totalDays);
            statement.setDouble(6, totalCost);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new booking was inserted successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Room room;
    private Room getRoom(String roomTypeSelectedItem) {
        Room room = null;

        final String DB_URL = "jdbc:mysql://localhost/bookit?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            //connection successful

            String sql = "SELECT * FROM room WHERE room_type=? AND status='free' LIMIT 1";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, roomTypeSelectedItem);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                room = new Room();
                room.roomNumber = resultSet.getInt("room_number");
                room.roomType = resultSet.getString("room_type");
                room.status = resultSet.getString("status");
                room.price = resultSet.getDouble("price");

                String sqlUpdate = "UPDATE room SET status='taken' WHERE room_number=?";
                PreparedStatement preparedStatementUpdate = conn.prepareStatement(sqlUpdate);
                preparedStatementUpdate.setInt(1, room.roomNumber);
                preparedStatementUpdate.executeUpdate();
            }

            conn.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return room;
    }


    public static void main(String[] args) {
        ReservationSection reservationSection = new ReservationSection(null);
    }
}


