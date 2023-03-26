package com.example.practice2.domain;

public class Client extends Entity<Long>{
    public String name;
    public Integer fidelityGrade;
    public Integer age;
    public Hobby hobby;

    public Client(Long id, String name, Integer fidelityGrade, Integer age, String hobby) {
        setId(id);
        this.name = name;
        this.fidelityGrade = fidelityGrade;
        this.age = age;
        Hobby hob=null;
        if(hobby.equals("reading")) {
            this.hobby = hob.reading;
        }
        else if(hobby.equals("music")){
            this.hobby = hob.music;
        }
        else if(hobby.equals("hiking")){
            this.hobby = hob.hiking;
        }
        if(hobby.equals("walking")){
            this.hobby = hob.walking;
        }
        if(hobby.equals("extreme sports")){
            this.hobby = hob.extreme_sports;
        }

    }

    public void setId(Long id){
        super.setId(id);
    }

    public Long getIdClient(){
        return getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFidelityGrade() {
        return fidelityGrade;
    }

    public void setFidelityGrade(Integer fidelityGrade) {
        this.fidelityGrade = fidelityGrade;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Hobby getHobby() {
        return hobby;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }
}
