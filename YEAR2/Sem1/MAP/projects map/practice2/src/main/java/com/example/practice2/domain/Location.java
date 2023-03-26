package com.example.practice2.domain;

public class Location extends Entity<Double> {

    public String locationName;

    public Location(Double id, String locationName) {
        setId(id);
        this.locationName = locationName;
    }

    public void setId(Double id){
        super.setId(id);
    }

    public Double getIdLoc(){
        return getId();
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
