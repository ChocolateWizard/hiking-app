/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.logic.controllers;

import com.borak.hikingapp.commonlib.domain.classes.Hiker;
import com.borak.hikingapp.commonlib.domain.classes.HikingGroup;
import com.borak.hikingapp.commonlib.domain.classes.Place;
import com.borak.hikingapp.commonlib.domain.classes.User;
import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.server.repository.db.mysql.impl.RepositoryHiker;
import com.borak.hikingapp.server.repository.db.mysql.impl.RepositoryHikingGroup;
import com.borak.hikingapp.server.repository.db.mysql.impl.RepositoryPlace;
import com.borak.hikingapp.server.repository.db.mysql.impl.RepositoryUser;
import com.borak.hikingapp.server.repository.intf.IRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Despot
 */
public class ControllerSO {

    private IRepository<Place> storagePlace;
    private IRepository<User> storageUser;
    private IRepository<Hiker> storageHiker;
    private IRepository<HikingGroup> storageHikingGroup;

    private static ControllerSO instance;

    private ControllerSO() {
        storagePlace = new RepositoryPlace();
        storageUser = new RepositoryUser();
        storageHiker = new RepositoryHiker();
        storageHikingGroup = new RepositoryHikingGroup();
    }

    public static ControllerSO getInstance() {
        if (instance == null) {
            instance = new ControllerSO();
        }
        return instance;
    }
//====================================================================================   
//===============================SYSTEM OPERATIONS===================================
//=====================================================================================    

    public List<Place> getAllPlaces() throws CustomException {
        try {
            storagePlace.connect();
            List<Place> places = storagePlace.getAll();
            storagePlace.commit();
            return places;
        } catch (CustomException ex) {
            storagePlace.rollback();
            throw ex;
        } finally {
            storagePlace.disconnect();
        }

    }

    public List<Hiker> getAllHikers() throws CustomException {
        try {
            storageHiker.connect();
            List<Hiker> hikers = storageHiker.getAll();
            storageHiker.commit();
            return hikers;
        } catch (CustomException ex) {
            storageHiker.rollback();
            throw ex;
        } finally {
            storageHiker.disconnect();
        }

    }

    public void register(User user) throws CustomException {
        try {
            storageUser.connect();
            User u = storageUser.find(user);
            if (u == null) {
                storageUser.insert(user);
            } else {
                throw new CustomException(ErrorType.REGISTRATION_ERROR, "Username " + user.getUsername() + " already exists!");
            }
            storageUser.commit();;
        } catch (CustomException ex) {
            storageUser.rollback();
            throw ex;
        } finally {
            storageUser.disconnect();
        }
    }

    public User login(User user) throws CustomException {
        User u;
        try {
            storageUser.connect();
            u = storageUser.find(user);
            if (u != null) {
                if (!u.getPassword().equals(user.getPassword())) {
                    throw new CustomException(ErrorType.LOGIN_ERROR, "Invalid password for given username!");
                }
            } else {
                throw new CustomException(ErrorType.LOGIN_ERROR, "Username " + user.getUsername() + " does not exist!");
            }
            storageUser.commit();
        } catch (CustomException ex) {
            storageUser.rollback();
            throw ex;
        } finally {
            storageUser.disconnect();
        }
        return u;
    }

    public void createHiker(Hiker hiker) throws CustomException {
        try {
            storageHiker.connect();
            Hiker h = storageHiker.find(hiker);
            if (h == null) {
                storageHiker.insert(hiker);
            } else {
                throw new CustomException(ErrorType.HIKER_CREATION_ERROR, "Hiker " + hiker.getUcin() + " already exists!");
            }
            storageHiker.commit();
        } catch (CustomException ex) {
            storageHiker.rollback();
            throw ex;
        } finally {
            storageHiker.disconnect();
        }
    }

    public List<Hiker> findHikers(String name) throws CustomException {
        List<Hiker> hikersToBeReturned = null;
        name = name.trim();
        try {
            storageHiker.connect();
            List<Hiker> hikers = storageHiker.getAll();
            if (hikers != null) {
                hikersToBeReturned = new ArrayList<>(hikers.size());
                if (name.isEmpty()) {
                    hikersToBeReturned = hikers;
                } else {
                    String[] n = name.split(" ");
                    name = "";
                    for (String n1 : n) {
                        if (!n1.isEmpty()) {
                            name += n1 + " ";
                        }
                    }
                    name = name.trim();
                    String dummyName = "";
                    for (Hiker hiker : hikers) {
                        dummyName = hiker.getFirstName().concat(" ").concat(hiker.getLastName());
                        if (dummyName.contains(name)) {
                            hikersToBeReturned.add(hiker);
                        }
                    }
                }
            }
            storageHiker.commit();
        } catch (CustomException ex) {
            storageHiker.rollback();
            throw ex;
        } finally {
            storageHiker.disconnect();
        }
        return hikersToBeReturned;
    }

    public void deleteHiker(Hiker h) throws CustomException {
        try {
            storageHiker.connect();
            storageHiker.delete(h);
            storageHiker.commit();
        } catch (CustomException ex) {
            storageHiker.rollback();
            throw ex;
        } finally {
            storageHiker.disconnect();
        }
    }

    public void updateHiker(Hiker h) throws CustomException {
        try {
            storageHiker.connect();
            storageHiker.update(h);
            storageHiker.commit();
        } catch (CustomException ex) {
            storageHiker.rollback();
            throw ex;
        } finally {
            storageHiker.disconnect();
        }
    }

    public void createHikingGroup(HikingGroup mainGroup) throws CustomException {
        try {
            storageHikingGroup.connect();
            HikingGroup g = storageHikingGroup.find(mainGroup);
            if (g == null) {
                storageHikingGroup.insert(mainGroup);
            } else {
                throw new CustomException(ErrorType.HIKING_GROUP_CREATION_ERROR, "Hiking group " + mainGroup.getCrn() + " already exists!");
            }
            storageHikingGroup.commit();
        } catch (CustomException ex) {
            storageHikingGroup.rollback();
            throw ex;
        } finally {
            storageHikingGroup.disconnect();
        }
    }

    public List<HikingGroup> findHikingGroups(String name) throws CustomException {
        List<HikingGroup> groupsToBeReturned = null;
        name = name.trim();
        try {
            storageHikingGroup.connect();
            List<HikingGroup> groups = storageHikingGroup.getAll();
            if (groups != null) {
                groupsToBeReturned = new ArrayList<>(groups.size());
                if (name.isEmpty()) {
                    groupsToBeReturned = groups;
                } else {
                    String[] n = name.split(" ");
                    name = "";
                    for (String n1 : n) {
                        if (!n1.isEmpty()) {
                            name += n1 + " ";
                        }
                    }
                    name = name.trim();
                    for (HikingGroup group : groups) {
                        if (group.getName().contains(name)) {
                            groupsToBeReturned.add(group);
                        }
                    }
                }
            }
            storageHikingGroup.commit();
        } catch (CustomException ex) {
            storageHikingGroup.rollback();
            throw ex;
        } finally {
            storageHikingGroup.disconnect();
        }
        return groupsToBeReturned;
    }

    public void deleteHikingGroup(HikingGroup g) throws CustomException {
        try {
            storageHikingGroup.connect();
            storageHikingGroup.delete(g);
            storageHikingGroup.commit();
            
        } catch (CustomException ex) {
            storageHikingGroup.rollback();
            throw ex;
        } finally {
            storageHikingGroup.disconnect();
        }
    }
}
