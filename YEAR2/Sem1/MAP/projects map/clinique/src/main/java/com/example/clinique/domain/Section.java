package com.example.clinique.domain;

public class Section extends Entity<Integer> {
    public String name;
    public Integer idSectionChief;
    public Integer pricePerCheckup;
    public Integer maxDurationCheckup;

    public Section(Integer idSection, String name, Integer idSectionChief, Integer pricePerCheckup, Integer maxDurationCheckup) {
        setId(idSection);
        this.name = name;
        this.idSectionChief = idSectionChief;
        this.pricePerCheckup = pricePerCheckup;
        this.maxDurationCheckup = maxDurationCheckup;
    }



    public Integer getIdSection(){
        return getId();
    }
    public void setId(int id){
        super.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdSectionChief() {
        return idSectionChief;
    }

    public void setIdSectionChief(Integer idSectionChief) {
        this.idSectionChief = idSectionChief;
    }

    public Integer getPricePerCheckup() {
        return pricePerCheckup;
    }

    public void setPricePerCheckup(Integer pricePerCheckup) {
        this.pricePerCheckup = pricePerCheckup;
    }

    public Integer getMaxDurationCheckup() {
        return maxDurationCheckup;
    }

    public void setMaxDurationCheckup(Integer maxDurationCheckup) {
        this.maxDurationCheckup = maxDurationCheckup;
    }
}
