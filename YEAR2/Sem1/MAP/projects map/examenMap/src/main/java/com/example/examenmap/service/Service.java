package com.example.examenmap.service;

import com.example.examenmap.domain.Nevoie;
import com.example.examenmap.domain.Persoana;
import com.example.examenmap.domain.nevPers;
import com.example.examenmap.repo.Repository;
import com.example.examenmap.utils.events.EntityChangeEvent;
import com.example.examenmap.utils.observer.Observable;
import com.example.examenmap.utils.observer.Observer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Service implements Observable<EntityChangeEvent> {
    private Repository<Integer, Persoana> persoaneRepository;
    private Repository<Integer, Nevoie> nevoiRepository;

    private List<Observer<EntityChangeEvent>> observers = new ArrayList<>();


    public Service(Repository<Integer, Persoana> persoaneRepository, Repository<Integer, Nevoie> nevoiRepository) {
        this.persoaneRepository = persoaneRepository;
        this.nevoiRepository = nevoiRepository;
    }

    @Override
    public void addObserver(Observer<EntityChangeEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<EntityChangeEvent> e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers(EntityChangeEvent t) {
        observers.stream().forEach(x -> x.update(t));
    }



    public Persoana save(Persoana entity)
    {
        persoaneRepository.save(entity);
        return entity;
    }
    public Iterable<Nevoie> getAllNevoi() {
        return nevoiRepository.findAll();
    }
    public Iterable<Persoana> getAllPersoane() {
        return persoaneRepository.findAll();
    }

    public Nevoie updateNevoie(Nevoie n){

        return nevoiRepository.update(n);}

    public void add(Nevoie n){nevoiRepository.save(n);}


    public List<Persoana> getListPers() {
        List<Persoana> sect =new ArrayList((Collection) persoaneRepository.findAll());
        return sect;
    }
    public List<Nevoie> getListNevs() {
        List<Nevoie> sect =new ArrayList((Collection) nevoiRepository.findAll());
        return sect;
    }

    public Persoana getPersById(Integer id){
        List<Persoana> sect=getListPers();
        for(Persoana p:sect){
            if(p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }
    public List<Nevoie> getListNevsForUserOras(Persoana p) {
        List<Nevoie> sect =new ArrayList((Collection) nevoiRepository.findAll());
        List<Nevoie> newNev=new ArrayList<>();
        for(Nevoie n:sect){
            if(!n.getOminNevoie().equals(p.getId()) && getPersById(n.getOminNevoie()).getOras().equals(p.getOras())){
                newNev.add(n);
            }
        }
        return newNev;
    }

    public List<nevPers> getNevNume(Persoana p){
        List<Nevoie>nev=getListNevsForUserOras(p);
        List<nevPers>nevN=new ArrayList<>();

        for(Nevoie n:nev){
            nevPers nn=new nevPers(n.getId(), n.getTitlu(),n.getDescriere(), n.getDeadline(), getPersById(n.getOminNevoie()).getNume(), getPersById(n.getOnSalvator()).getNume(),n.getStatus());
            nevN.add(nn);
        }
        return nevN;
    }
    public List<nevPers> getFapNume(){
        List<Nevoie>nev=getListNevs();
        List<nevPers>nevN=new ArrayList<>();
        for(Nevoie n:nev){
            if(n.getOminNevoie()>0 && n.getOnSalvator()>0){
                nevPers nn=new nevPers(n.getId(), n.getTitlu(),n.getDescriere(), n.getDeadline(), getPersById(n.getOminNevoie()).getNume(), getPersById(n.getOnSalvator()).getNume(),n.getStatus());
                nevN.add(nn);
            }

        }
        return nevN;

    }




}
