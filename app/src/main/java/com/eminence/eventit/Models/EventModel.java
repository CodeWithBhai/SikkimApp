package com.eminence.eventit.Models;

public class EventModel {
    private int pic;
    private String title;
    private String title2;
    private String price;

    public EventModel(int pic, String title, String title2, String price) {
        this.pic = pic;
        this.title = title;
        this.title2 = title2;
        this.price = price;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
