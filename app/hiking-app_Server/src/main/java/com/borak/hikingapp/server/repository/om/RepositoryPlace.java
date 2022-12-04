/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.repository.om;

import com.borak.hikingapp.commonlib.domain.classes.Place;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.server.repository.IRepository;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author User
 */
public class RepositoryPlace implements IRepository<Place> {

    List<Place> places;

    public RepositoryPlace() {
        places = new LinkedList<>();
        places.add(new Place(1l, "Blencathra"));
        places.add(new Place(2l, "Hadrian’s Wall"));
        places.add(new Place(3l, "Helvellyn"));
        places.add(new Place(4l, "The Lizard Coastal Walk"));
        places.add(new Place(6l, "Malham Cove"));
        places.add(new Place(7l, "North Downs Way"));
        places.add(new Place(8l, "Pennine Way"));
        places.add(new Place(9l, "Cuthbert’s Way"));
        places.add(new Place(10l, "South Downs Way"));
        places.add(new Place(11l, "Stanage Edge"));
        places.add(new Place(12l, "Stonehenge"));
        places.add(new Place(13l, "The Thames Path"));
        places.add(new Place(14l, "Serbia"));
    }

    @Override
    public void connect() throws CustomException {
    }

    @Override
    public void disconnect() throws CustomException {
    }

    @Override
    public void commit() throws CustomException {
    }

    @Override
    public void rollback() throws CustomException {
    }

    @Override
    public List<Place> getAll() throws CustomException {
        return places;
    }

    @Override
    public void insert(Place object) throws CustomException {
        places.add(object);
    }

    @Override
    public Place find(Place object) throws CustomException {
        for (Place place : places) {
            if (place.equals(object)) {
                return place;
            }
        }
        return null;
    }

    @Override
    public void delete(Place object) throws CustomException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Place object) throws CustomException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertAll(List<Place> object) throws CustomException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteAll() throws CustomException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
