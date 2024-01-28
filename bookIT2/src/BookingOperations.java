import Entities.Booking;
import Entities.Room;
import Entities.User;

import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class BookingOperations {
    public String roomTypeSelectedItem = "";
    public int totalDays;
    String startDate;
    Scanner input = new Scanner(System.in);

    public BookingOperations(int choice, int userId) {
        switch (choice) {
            case 1 :
                getBookingDetails(userId);
                break;
            case 2 :
                int bookingCount = displayBookings(userId);
                boolean bookingCond = true;
                    if(bookingCount == 0) {
                        System.out.println("No bookings currently");
                        return;
                    }
                    System.out.print("Select booking number to edit or press '0' to exit: ");
                    int bookingChoice = input.nextInt();
                    if(bookingChoice == 0) {
                        bookingCond = false;
                        break;
                    }
                    if(bookingChoice != 0 && bookingChoice > 0 && bookingChoice <= bookingCount) {
                        System.out.println("\n\n--------------------------------\n|         BOOKING MENU         |\n|1. Update                     |\n|2. Delete                     |\n|0. Cancel                     |\n--------------------------------");
                        System.out.print("Selection from booking menu: ");

                        int bookingAction = input.nextInt();
                        switch (bookingAction) {
                            case 1 :
                                updateBooking(userId, bookingChoice);
                                break;
                            case 2 :
                                deleteBooking(userId, bookingChoice);
                                break;
                            case 0 :
                                System.out.println("Canceling booking alter");
                                break;
                            default:
                                System.out.println("Please select valid booking operation");
                                break;
                        }
                    } else {
                        System.out.println("Booking does not exist");
                    }
                break;
            case 3 :
                System.out.println("Returning to user menu");
                break;
            default:
                System.out.println("please select a number from the menu");
                break;
        }
    }

    public void getBookingDetails(int userId) {
        System.out.println("Room types available:\n1. Single Bed\n2. Double Bed\n3. Suite\n4. Deluxe Double Bed ");
        System.out.print("Room Choice: ");
        int choice = input.nextInt();

        switch (choice) {
            case 1 :
                roomTypeSelectedItem = "single_bed";
                break;
            case 2 :
                roomTypeSelectedItem = "double_bed";
                break;
            case 3 :
                roomTypeSelectedItem = "suite";
                break;
            case 4 :
                roomTypeSelectedItem = "deluxe_double_bed";
                break;
        }
        input.nextLine();
        System.out.print("Start Date [YYYY-MM-DD]: ");
        startDate = input.nextLine();
        System.out.print("Number of days: ");
        totalDays = input.nextInt();

        room = getRoom(roomTypeSelectedItem);
        double totalCost = room.price*totalDays;
        createBooking(room.roomNumber, roomTypeSelectedItem, totalDays, totalCost, userId, startDate);
    }
    //(int roomNumber, int userId, String roomType, Date startDate, int totalDays, double totalCost)
    private void createBooking(int roomNumber, String roomType, int totalDays, double totalCost, int userId, String startDate) {
        final String DB_URL = "jdbc:mysql://localhost/bookit?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        String sql = "INSERT INTO booking (room_number, user_id, room_type, start_date, total_days, total_cost) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, roomNumber);
            statement.setInt(2, userId);
            statement.setString(3, roomType);
            statement.setString(4, startDate);
            statement.setInt(5, totalDays);
            statement.setDouble(6, totalCost);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                String sqlUpdate = "UPDATE room SET status='taken' WHERE room_number=?";
                PreparedStatement preparedStatementUpdate = conn.prepareStatement(sqlUpdate);
                preparedStatementUpdate.setInt(1, room.roomNumber);
                preparedStatementUpdate.executeUpdate();
                System.out.println("Booking successfully created");
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
            }

            conn.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return room;
    }

    private int displayBookings(int userId) {
        final String DB_URL = "jdbc:mysql://localhost/bookit?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";
        List<Booking> bookings = new ArrayList<>();
        int count = 0;

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            //connection successful

            String sql = "SELECT * FROM booking WHERE user_id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Booking booking = new Booking();
                booking.bookingId = resultSet.getInt("booking_id");
                booking.roomNumber = resultSet.getInt("room_number");
                booking.userId = resultSet.getInt("user_id");
                booking.roomType = resultSet.getString("room_type");
                booking.totalDays = resultSet.getInt("total_days");
                booking.totalCost = resultSet.getInt("total_cost");
                booking.startDate = resultSet.getString("start_date");
                bookings.add(booking);
            }

            conn.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n\n-------------------------------------------\n|              BOOKING LIST               |\n-------------------------------------------");
        for (Booking booking : bookings) {
            count++;
            System.out.printf("|%-20s %-20d|\n", "Booking number: ", count);
            System.out.printf("|%-20s %-20d|\n", "Room Number: ", booking.roomNumber);
            System.out.printf("|%-20s %-20s|\n", "Room Type: ", booking.roomType);
            System.out.printf("|%-20s %-20s|\n", "Start Date: ", booking.startDate);
            System.out.printf("|%-20s %-20d|\n", "Total Days: ", booking.totalDays);
            System.out.printf("|%-20s %-20.2f|\n", "Total Cost: ", booking.totalCost);
            System.out.println("-------------------------------------------");
        }


        return count;
    }

    private void deleteBooking(int userId, int bookingChoice) {
        final String DB_URL = "jdbc:mysql://localhost/bookit?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            int bookingId;
            String sql = "SELECT * FROM booking WHERE user_id=? LIMIT 1 OFFSET ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, bookingChoice - 1);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                bookingId = resultSet.getInt("booking_id");
                int roomNumber = resultSet.getInt("room_number");
                sql = "UPDATE room SET status='free' WHERE room_number=?";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, roomNumber);
                preparedStatement.executeUpdate();
                sql = "DELETE FROM booking WHERE booking_id=?";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, bookingId);
                preparedStatement.executeUpdate();
                System.out.println("Successfully deleted booking\n\n");
            } else {
                System.out.println("Could not delete booking\n\n");
            }

            conn.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    private void updateBooking(int userId, int bookingChoice) {
        final String DB_URL = "jdbc:mysql://localhost/bookit?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";
        String newRoomType = "";

        System.out.println("\n\n--------------------------------\n|         UPDATE MENU          |\n|Room types available:         |\n|1. Single Bed                 |\n|2. Double Bed                 |\n|3. Suite                      |\n|4. Deluxe Double Bed          |\n--------------------------------");
        System.out.print("Select room type: ");

        int choice = input.nextInt();

        while (choice < 1 || choice > 4) {
            System.out.println("Please enter a valid number for the room type");
            choice = input.nextInt();
        }

        switch (choice) {
            case 1:
                newRoomType = "single_bed";
                break;
            case 2:
                newRoomType = "double_bed";
                break;
            case 3:
                newRoomType = "suite";
                break;
            case 4:
                newRoomType = "deluxe_double_bed";
                break;
        }
        input.nextLine();
        System.out.print("Start Date [YYYY-MM-DD]: ");
        String newStartDate = input.nextLine();
        System.out.print("Enter amount of days: ");
        int newTotalDays = input.nextInt();
        Room newRoom = getRoom(newRoomType);
        double newTotalCost = newTotalDays * newRoom.price;

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            int bookingId;
            String sql = "SELECT * FROM booking WHERE user_id=? LIMIT 1 OFFSET ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, bookingChoice - 1);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                bookingId = resultSet.getInt("booking_id");
                int oldRoomNumber = resultSet.getInt("room_number");
                sql = "UPDATE room SET status='free' WHERE room_number=?";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, oldRoomNumber);
                preparedStatement.executeUpdate();

                sql = "UPDATE booking SET room_type=?, total_days=?, total_cost=?, room_number=?, start_date=? WHERE booking_id=?";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, newRoomType);
                preparedStatement.setInt(2, newTotalDays);
                preparedStatement.setDouble(3, newTotalCost);
                preparedStatement.setInt(4, newRoom.roomNumber);
                preparedStatement.setString(5, newStartDate);
                preparedStatement.setInt(6, bookingId);
                preparedStatement.executeUpdate();

                sql = "UPDATE room SET status='taken' WHERE room_number=?";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, newRoom.roomNumber);
                preparedStatement.executeUpdate();

                System.out.println("Successfully updated booking\n\n");
            } else {
                System.out.println("Could not update booking\n\n");
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


