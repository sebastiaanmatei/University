package com.example.clinique.domain;

public class Doctor extends Entity<Integer>{
    public Integer idSection;

    public String name;
    public Integer seniority;
    public String residence;

    public Doctor(Integer idDoctor, Integer idSection, String name, Integer seniority, String residence) {
        setId(idDoctor);
        this.idSection = idSection;
        this.name = name;
        this.seniority = seniority;
        this.residence = residence;
    }

    public Integer getIdDoctor(){
        return getId();
    }
    public void setId(int id){
        super.setId(id);
    }


    public Integer getIdSection() {
        return idSection;
    }

    public void setIdSection(Integer idSection) {
        this.idSection = idSection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeniority() {
        return seniority;
    }

    public void setSeniority(Integer seniority) {
        this.seniority = seniority;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }


}
