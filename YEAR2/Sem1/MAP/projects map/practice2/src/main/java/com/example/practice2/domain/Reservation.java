package com.example.practice2.domain;

import java.time.LocalDateTime;
import java.util.Date;

public class Reservation extends Entity<Double>{
    public Long clientId;
    public Double hotelId;
    public Date startDate;
    public Integer noNights;

    public Reservation(Double id,Long clientId, Double hotelId, Date startDate, Integer noNights) {
        setId(id);
        this.clientId = clientId;
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.noNights = noNights;
    }

    public void setId(Double id){
        super.setId(id);
    }

    public Double getIdRes(){
        return getId();
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Double getHotelId() {
        return hotelId;
    }

    public void setHotelId(Double hotelId) {
        this.hotelId = hotelId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getNoNights() {
        return noNights;
    }

    public void setNoNights(Integer noNights) {
        this.noNights = noNights;
    }
}
