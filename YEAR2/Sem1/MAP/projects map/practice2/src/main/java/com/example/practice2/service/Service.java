package com.example.practice2.service;

import com.example.practice2.domain.*;
import com.example.practice2.repo.Repository;
import com.example.practice2.utils.events.EntityChangeEvent;
import com.example.practice2.utils.observer.Observable;
import com.example.practice2.utils.observer.Observer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Service<ID>implements Observable<EntityChangeEvent> {


    private Repository<Double, Location> locationRepo;
    private Repository<Double, Hotel> hotelRepo;
    private Repository<Double, SpecialOffer> offRepo;
    private Repository<Long, Client> clientRepo;

    public Service(Repository<Double, Location> locationRepo, Repository<Double, Hotel> hotelRepo, Repository<Double, SpecialOffer> offRepo, Repository<Long, Client> clientRepo) {
        this.locationRepo = locationRepo;
        this.hotelRepo = hotelRepo;
        this.offRepo = offRepo;
        this.clientRepo = clientRepo;
    }

    private List<Observer<EntityChangeEvent>> observers = new ArrayList<>();



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


    public List<Location> getLocations(){
        List<Location> sect =new ArrayList((Collection) locationRepo.findAll());
        return sect;
    }

    public List<Hotel> getHotels(){
        List<Hotel> sect =new ArrayList((Collection) hotelRepo.findAll());
        return sect;
    }

    public List<Hotel> getHotLoc(Double locId){
        System.out.println("intra prin hotloc");
        List<Hotel> sect =new ArrayList((Collection) hotelRepo.findAll());
        List<Hotel> checkups = StreamSupport.stream(sect.spliterator(), false)
                .filter(x->x.locationId.equals(locId))
                .collect(Collectors.toList());

        return checkups;
    }

    public List<SpecialOffer> getOffs(){
        List<SpecialOffer> sect =new ArrayList((Collection) offRepo.findAll());
        return sect;
    }

    public List<Client> getClients(){
        List<Client> sect =new ArrayList((Collection) clientRepo.findAll());
        return sect;
    }

    public Double getLocIdByName(String name){
        List<Location> ls=getLocations();
        for(Location l:ls){
            if(l.getLocationName().equals(name)){
                return l.getIdLoc();
            }
        }
        return null;
    }

    public String getHotelById(Double id){
        List<Hotel> ht=getHotels();
        for(Hotel h:ht){
            if(h.getIdHotel().equals(id)){
                return h.getHotelName();
            }
        }
        return null;
    }

    public String getLocationById(Double id){
        List<Location> ht=getLocations();
        for(Location h:ht){
            if(h.getIdLoc().equals(id)){
                return h.getLocationName();
            }
        }
        return null;
    }

    public Double getLocationByHotel(Double id){
        List<Hotel>ht=getHotels();
        for(Hotel h:ht){
            if(h.getIdHotel().equals(id)){
                return h.getLocationId();
            }
        }
        return null;
    }

    public List<OfferDetails> getDetails(){
        List<OfferDetails> ofd=new ArrayList<>();
        List<SpecialOffer>spf=getOffs();

        Random rand = new Random();
        int upperbound = 10000;
        int id=rand.nextInt(upperbound);

        for(SpecialOffer s:spf){
            Double idloc=getLocationByHotel(s.getHotelId());
            OfferDetails ofdd=new OfferDetails(id, getHotelById(s.getHotelId()), getLocationById(idloc), s.getStartDate(), s.getEndDate(), s.getPercents());
            ofd.add(ofdd);
        }
        return ofd;

    }



}