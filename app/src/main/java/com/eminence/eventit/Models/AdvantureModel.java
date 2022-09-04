package com.eminence.eventit.Models;

public class AdvantureModel {
    int imgAdvanture;
    String locationAdvanture, nameAdvanture;

    public AdvantureModel(int imgAdvanture, String locationAdvanture, String nameAdvanture) {
        this.imgAdvanture = imgAdvanture;
        this.locationAdvanture = locationAdvanture;
        this.nameAdvanture = nameAdvanture;
    }

    public int getImgAdvanture() {
        return imgAdvanture;
    }

    public void setImgAdvanture(int imgAdvanture) {
        this.imgAdvanture = imgAdvanture;
    }

    public String getLocationAdvanture() {
        return locationAdvanture;
    }

    public void setLocationAdvanture(String locationAdvanture) {
        this.locationAdvanture = locationAdvanture;
    }

    public String getNameAdvanture() {
        return nameAdvanture;
    }

    public void setNameAdvanture(String nameAdvanture) {
        this.nameAdvanture = nameAdvanture;
    }
}
