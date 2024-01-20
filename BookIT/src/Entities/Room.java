package Entities;

public class Room {
    private int roomNumber;
    private String roomType;
    private String status;

    // Constructor
    public Room(int roomNumber, String roomType, String status) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.status = status;
    }

    // Getters
    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
