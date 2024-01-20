package Entities;

import java.util.Date;

public class Hotel {
    private int hotelId;
    private String name;
    private String address;
    private String phone;
    private String email;
    private int stars;

    // Constructor
    public Hotel(int hotelId, String name, String address, String phone, String email, int stars, Date checkInTime, Date checkOutTime) {
        this.hotelId = hotelId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.stars = stars;
    }

    // Getters
    public int getHotelId() {
        return hotelId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getStars() {
        return stars;
    }

    // Setters
    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
