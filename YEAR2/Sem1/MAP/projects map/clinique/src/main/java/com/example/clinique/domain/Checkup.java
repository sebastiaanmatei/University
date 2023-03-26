package com.example.clinique.domain;

import java.time.LocalDate;
import java.util.Date;

public class Checkup extends Entity<Integer>{

    public Integer idDoctor;
    public Integer idPacient;
    public String namePacient;
    public LocalDate date;
    public Integer hour;

    public Checkup(Integer idCheckup, Integer idDoctor, Integer idPacient, String namePacient, LocalDate date, Integer hour) {
        setId(idCheckup);
        this.idDoctor = idDoctor;
        this.idPacient = idPacient;
        this.namePacient = namePacient;
        this.date = date;
        this.hour = hour;
    }


    public Integer getIdCheckup(){
        return getId();
    }
    public void setId(int id){
        super.setId(id);
    }

    public Integer getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Integer idDoctor) {
        this.idDoctor = idDoctor;
    }

    public Integer getIdPacient() {
        return idPacient;
    }

    public void setIdPacient(Integer idPacient) {
        this.idPacient = idPacient;
    }

    public String getNamePacient() {
        return namePacient;
    }

    public void setNamePacient(String namePacient) {
        this.namePacient = namePacient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }
}
