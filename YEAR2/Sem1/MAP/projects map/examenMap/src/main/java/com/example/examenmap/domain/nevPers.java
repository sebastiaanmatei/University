package com.example.examenmap.domain;

import java.time.LocalDateTime;

public class nevPers extends Entity<Integer>{

    private String titlu;
    private String descriere;
    private LocalDateTime deadline;
    private String ominNevoie;
    private  String onSalvator;
    private String status;

    public nevPers(Integer integer, String titlu, String descriere, LocalDateTime deadline, String omNev, String omAj, String status) {
        setId(integer);
        this.titlu = titlu;
        this.descriere = descriere;
        this.deadline = deadline;
        this.ominNevoie = omNev;
        this.onSalvator = omAj;
        this.status = status;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public String getOminNevoie() {
        return ominNevoie;
    }

    public void setOminNevoie(String ominNevoie) {
        this.ominNevoie = ominNevoie;
    }

    public String getOnSalvator() {
        return onSalvator;
    }

    public void setOnSalvator(String onSalvator) {
        this.onSalvator = onSalvator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
