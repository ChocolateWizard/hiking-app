/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.logic.controllers;

import com.borak.hikingapp.server.repository.RepositoryManager;
import com.borak.hikingapp.commonlib.domain.classes.Hiker;
import com.borak.hikingapp.commonlib.domain.classes.HikingGroup;
import com.borak.hikingapp.commonlib.domain.classes.Place;
import com.borak.hikingapp.commonlib.domain.classes.User;
import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.server.threads.ServerThread;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Despot
 */
public final class ControllerSO {

    private RepositoryManager repositoryManager;

    private static ControllerSO instance;

    private ControllerSO() throws CustomException {
        repositoryManager = new RepositoryManager();
    }

    public static void initialize() throws CustomException {
        instance = new ControllerSO();
    }

    public void terminate() throws CustomException {
        instance = null;
        repositoryManager.getRepositoryLoggedUsers().removeAll();
    }

    public static ControllerSO getInstance() throws CustomException {
        if (instance == null) {
            throw new CustomException(ErrorType.CRITICAL_ERROR, "Controller must be initialized first!");
        }
        return instance;
    }

//====================================================================================   
//===============================SYSTEM OPERATIONS===================================
//=====================================================================================    
    public List<Place> getAllPlaces() throws CustomException {
        try {
            repositoryManager.getRepositoryPlace().connect();
            List<Place> places = repositoryManager.getRepositoryPlace().getAll();
            repositoryManager.getRepositoryPlace().commit();
            return places;
        } catch (CustomException ex) {
            repositoryManager.getRepositoryPlace().rollback();
            throw ex;
        } finally {
            repositoryManager.getRepositoryPlace().disconnect();
        }

    }

    public List<Hiker> getAllHikers() throws CustomException {
        try {
            repositoryManager.getRepositoryHiker().connect();
            List<Hiker> hikers = repositoryManager.getRepositoryHiker().getAll();
            repositoryManager.getRepositoryHiker().commit();
            return hikers;
        } catch (CustomException ex) {
            repositoryManager.getRepositoryHiker().rollback();
            throw ex;
        } finally {
            repositoryManager.getRepositoryHiker().disconnect();
        }

    }

    public void register(User user) throws CustomException {
        try {
            repositoryManager.getRepositoryUser().connect();
            User databaseUser = repositoryManager.getRepositoryUser().find(user);
            if (databaseUser == null) {
                repositoryManager.getRepositoryUser().insert(user);
            } else {
                throw new CustomException(ErrorType.REGISTRATION_ERROR, "Username " + user.getUsername() + " already exists!");
            }
            repositoryManager.getRepositoryUser().commit();
        } catch (CustomException ex) {
            repositoryManager.getRepositoryUser().rollback();
            throw ex;
        } finally {
            repositoryManager.getRepositoryUser().disconnect();
        }
    }

    public User login(User user) throws CustomException {
        User databaseUser;
        try {
            repositoryManager.getRepositoryUser().connect();
            databaseUser = repositoryManager.getRepositoryUser().find(user);
            if (databaseUser != null) {
                if (!databaseUser.getPassword().equals(user.getPassword())) {
                    throw new CustomException(ErrorType.LOGIN_ERROR, "Invalid password for given username!");
                }
            } else {
                throw new CustomException(ErrorType.LOGIN_ERROR, "Username " + user.getUsername() + " does not exist!");
            }
            repositoryManager.getRepositoryUser().commit();
        } catch (CustomException ex) {
            repositoryManager.getRepositoryUser().rollback();
            throw ex;
        } finally {
            repositoryManager.getRepositoryUser().disconnect();
        }
        if (repositoryManager.getRepositoryLoggedUsers().isLoggedIn(databaseUser)) {
            throw new CustomException(ErrorType.LOGIN_ERROR, "Given user is already logged in!");
        }
        repositoryManager.getRepositoryLoggedUsers().add(databaseUser);
        return databaseUser;
    }

    public void createHiker(Hiker hiker) throws CustomException {
        try {
            repositoryManager.getRepositoryHiker().connect();
            Hiker h = repositoryManager.getRepositoryHiker().find(hiker);
            if (h == null) {
                repositoryManager.getRepositoryHiker().insert(hiker);
            } else {
                throw new CustomException(ErrorType.HIKER_CREATION_ERROR, "Hiker " + hiker.getUcin() + " already exists!");
            }
            repositoryManager.getRepositoryHiker().commit();
        } catch (CustomException ex) {
            repositoryManager.getRepositoryHiker().rollback();
            throw ex;
        } finally {
            repositoryManager.getRepositoryHiker().disconnect();
        }
    }

    public List<Hiker> findHikers(String name) throws CustomException {
        List<Hiker> hikersToBeReturned = null;
        name = name.trim();
        try {
            repositoryManager.getRepositoryHiker().connect();
            List<Hiker> hikers = repositoryManager.getRepositoryHiker().getAll();
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
            repositoryManager.getRepositoryHiker().commit();
        } catch (CustomException ex) {
            repositoryManager.getRepositoryHiker().rollback();
            throw ex;
        } finally {
            repositoryManager.getRepositoryHiker().disconnect();
        }
        return hikersToBeReturned;
    }

    public void deleteHiker(Hiker h) throws CustomException {
        try {
            repositoryManager.getRepositoryHiker().connect();
            repositoryManager.getRepositoryHiker().delete(h);
            repositoryManager.getRepositoryHiker().commit();
        } catch (CustomException ex) {
            repositoryManager.getRepositoryHiker().rollback();
            throw ex;
        } finally {
            repositoryManager.getRepositoryHiker().disconnect();
        }
    }

    public void updateHiker(Hiker h) throws CustomException {
        try {
            repositoryManager.getRepositoryHiker().connect();
            repositoryManager.getRepositoryHiker().update(h);
            repositoryManager.getRepositoryHiker().commit();
        } catch (CustomException ex) {
            repositoryManager.getRepositoryHiker().rollback();
            throw ex;
        } finally {
            repositoryManager.getRepositoryHiker().disconnect();
        }
    }

    public void createHikingGroup(HikingGroup mainGroup) throws CustomException {
        try {
            repositoryManager.getRepositoryHikingGroup().connect();
            HikingGroup g = repositoryManager.getRepositoryHikingGroup().find(mainGroup);
            if (g == null) {
                repositoryManager.getRepositoryHikingGroup().insert(mainGroup);
            } else {
                throw new CustomException(ErrorType.HIKING_GROUP_CREATION_ERROR, "Hiking group " + mainGroup.getCrn() + " already exists!");
            }
            repositoryManager.getRepositoryHikingGroup().commit();
        } catch (CustomException ex) {
            repositoryManager.getRepositoryHikingGroup().rollback();
            throw ex;
        } finally {
            repositoryManager.getRepositoryHikingGroup().disconnect();
        }
    }

    public List<HikingGroup> findHikingGroups(String name) throws CustomException {
        List<HikingGroup> groupsToBeReturned = null;
        name = name.trim();
        try {
            repositoryManager.getRepositoryHikingGroup().connect();
            List<HikingGroup> groups = repositoryManager.getRepositoryHikingGroup().getAll();
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
            repositoryManager.getRepositoryHikingGroup().commit();
        } catch (CustomException ex) {
            repositoryManager.getRepositoryHikingGroup().rollback();
            throw ex;
        } finally {
            repositoryManager.getRepositoryHikingGroup().disconnect();
        }
        return groupsToBeReturned;
    }

    public void deleteHikingGroup(HikingGroup g) throws CustomException {
        try {
            repositoryManager.getRepositoryHikingGroup().connect();
            repositoryManager.getRepositoryHikingGroup().delete(g);
            repositoryManager.getRepositoryHikingGroup().commit();

        } catch (CustomException ex) {
            repositoryManager.getRepositoryHikingGroup().rollback();
            throw ex;
        } finally {
            repositoryManager.getRepositoryHikingGroup().disconnect();
        }
    }

    public void logout(User loggedUser) throws CustomException {
        repositoryManager.getRepositoryLoggedUsers().remove(loggedUser);
    }

}
