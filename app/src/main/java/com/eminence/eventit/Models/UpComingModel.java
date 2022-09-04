package com.eminence.eventit.Models;

public class UpComingModel {
    int id;
    String date, orderNumber, bookingDate, categoryName, locationName;

    public UpComingModel(int id, String date, String orderNumber, String bookingDate, String categoryName, String locationName) {
        this.id = id;
        this.date = date;
        this.orderNumber = orderNumber;
        this.bookingDate = bookingDate;
        this.categoryName = categoryName;
        this.locationName = locationName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
