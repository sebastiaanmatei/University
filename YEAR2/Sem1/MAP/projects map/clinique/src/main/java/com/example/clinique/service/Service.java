package com.example.clinique.service;

import com.example.clinique.domain.Checkup;
import com.example.clinique.domain.Doctor;
import com.example.clinique.domain.Section;
import com.example.clinique.repo.Repository;
import com.example.clinique.utils.events.CheckupEntityChangeEvent;
import com.example.clinique.utils.observer.Observable;
import com.example.clinique.utils.observer.Observer;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Service<ID>implements Observable<CheckupEntityChangeEvent> {


    private Repository<Integer, Checkup> checkupRepo;
    private Repository<Integer, Section> sectionRepo;
    private Repository<Integer, Doctor> doctorRepo;
    private List<Observer<CheckupEntityChangeEvent>> observers = new ArrayList<>();

    public Service(Repository<Integer, Checkup> checkupRepo, Repository<Integer, Section> sectionRepo, Repository<Integer, Doctor> doctorRepo) {
        this.checkupRepo = checkupRepo;
        this.sectionRepo = sectionRepo;
        this.doctorRepo = doctorRepo;
    }

    @Override
    public void addObserver(Observer<CheckupEntityChangeEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<CheckupEntityChangeEvent> e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers(CheckupEntityChangeEvent t) {
        observers.stream().forEach(x -> x.update(t));
    }

    public List<Section> getListSections() {
        List<Section> sect =new ArrayList((Collection) sectionRepo.findAll());
        for(Section s:sect){
            System.out.println(s.getName());
        }
        return sect;
    }

    public List<Doctor> getListDoctorsAtSection(Section sec) {
        List<Doctor> doc =new ArrayList((Collection) doctorRepo.findAll());
        List<Doctor> docs= doc.stream()
                .filter(x->x.getIdSection()==sec.getIdSection()).toList();
        return docs;
    }

    public List<Doctor> getListDoctors() {
        List<Doctor> doc =new ArrayList((Collection) doctorRepo.findAll());
        return doc;
    }

    public List<Checkup> getListCheckupsForDoctor(int idDoc) {
        List<Checkup> checkk =new ArrayList((Collection) checkupRepo.findAll());
        List<Checkup> checks= checkk.stream()
                .filter(x->x.getIdDoctor()==idDoc).toList();
        return checks;
    }

    public Checkup addCheck(Integer idDoctor, Integer idPacient, String namePacient, LocalDate dateCheck, Integer hourCheck){
        Random rand = new Random();
        int upperbound = 10000;
        int id=rand.nextInt(upperbound);
        Checkup check=new Checkup(id, idDoctor,idPacient,namePacient, dateCheck, hourCheck);
        return checkupRepo.save(check);
    }







}
