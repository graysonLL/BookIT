package Entities;
import java.util.Date;

public class Booking {
    private int id;
    private String roomType;
    private Date startDate;
    private Date endDate;
    private double totalCost;

    // Constructor
    public Booking(int id, String roomType, Date startDate, Date endDate) {
        this.id = id;
        this.roomType = roomType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getRoomType() {
        return roomType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
