package com.example.practice2.domain;

import java.util.Date;

public class SpecialOffer extends Entity<Double>{

    public Double hotelId;
    public Date startDate;
    public Date endDate;
    public Integer percents;

    public SpecialOffer(Double id,Double hotelId, Date startDate, Date endDate, Integer percents) {
        setId(id);
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percents = percents;
    }


    public Double getIdOff(){
        return getId();
    }

    public void setId(Double id){
        super.setId(id);
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getPercents() {
        return percents;
    }

    public void setPercents(Integer percents) {
        this.percents = percents;
    }
}
