package com.example.practice2.domain;

import java.util.Date;

public class OfferDetails extends Entity<Integer>{
    public String nameHotel;
    public String nameLocation;
    public Date startDate;
    public Date endDate;

    public Integer percents;

    public OfferDetails(Integer id,String nameHotel, String nameLocation, Date startDate, Date endDate, Integer percents) {
        setId(id);
        this.nameHotel = nameHotel;
        this.nameLocation = nameLocation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percents=percents;
    }

    public Integer getPercents() {
        return percents;
    }

    public void setPercents(Integer percents) {
        this.percents = percents;
    }

    public String getNameHotel() {
        return nameHotel;
    }

    public void setNameHotel(String nameHotel) {
        this.nameHotel = nameHotel;
    }

    public String getNameLocation() {
        return nameLocation;
    }

    public void setNameLocation(String nameLocation) {
        this.nameLocation = nameLocation;
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
}
