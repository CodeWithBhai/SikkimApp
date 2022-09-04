package com.eminence.eventit.Models;

public class ToDoModel {
    int placeImgTDo;
    String parkNameToDo, locationNameToDo;

    public ToDoModel(int placeImgTDo, String parkNameToDo, String locationNameToDo) {
        this.placeImgTDo = placeImgTDo;
        this.parkNameToDo = parkNameToDo;
        this.locationNameToDo = locationNameToDo;
    }

    public int getPlaceImgTDo() {
        return placeImgTDo;
    }

    public void setPlaceImgTDo(int placeImgTDo) {
        this.placeImgTDo = placeImgTDo;
    }

    public String getParkNameToDo() {
        return parkNameToDo;
    }

    public void setParkNameToDo(String parkNameToDo) {
        this.parkNameToDo = parkNameToDo;
    }

    public String getLocationNameToDo() {
        return locationNameToDo;
    }

    public void setLocationNameToDo(String locationNameToDo) {
        this.locationNameToDo = locationNameToDo;
    }
}
