package com.example.practice2.domain;

public class Hotel extends Entity<Double>{
    public Double locationId;
    public String hotelName;
    public Integer noRooms;
    public Double pricePerNight;

    public Type type;

    public Hotel(Double id, Double locationId, String hotelName, Integer noRooms, Double pricePerNight, String type) {

        setId(id);
        this.locationId = locationId;
        this.hotelName = hotelName;
        this.noRooms = noRooms;
        this.pricePerNight = pricePerNight;
        Type t = null;
        if(type.equals("family")){
            this.type = t.family;
        }
        if(type.equals("teenagers")){
            this.type = t.family;
        }
        if(type.equals("oldpeople")){
            this.type = t.family;
        }

    }
    public Double getIdHotel(){
        return getId();
    }
    public void setId(Double id){
        super.setId(id);
    }

    public Double getLocationId() {
        return locationId;
    }

    public void setLocationId(Double locationId) {
        this.locationId = locationId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Integer getNoRooms() {
        return noRooms;
    }

    public void setNoRooms(Integer noRooms) {
        this.noRooms = noRooms;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
