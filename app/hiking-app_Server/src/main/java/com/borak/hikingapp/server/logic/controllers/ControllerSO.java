/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.logic.controllers;

import com.borak.hikingapp.server.repository.RepositoryManager;
import com.borak.hikingapp.commonlib.domain.classes.Hiker;
import com.borak.hikingapp.commonlib.domain.classes.HikingGroup;
import com.borak.hikingapp.commonlib.domain.classes.HikingGroupPlan;
import com.borak.hikingapp.commonlib.domain.classes.Place;
import com.borak.hikingapp.commonlib.domain.classes.Profile;
import com.borak.hikingapp.commonlib.domain.classes.User;
import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import java.util.ArrayList;
import java.util.LinkedList;
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
        System.out.println("================SYSTEM CONTROLLER INITIALIZED=================");
    }

    public void terminate() throws CustomException {
        instance = null;
        repositoryManager.getRepositoryLoggedUsers().removeAll();
        System.out.println("================SYSTEM CONTROLLER TERMINATED==================");
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
        } catch (CustomException e) {
            throw new CustomException(ErrorType.PLACE_GET_ALL_ERROR, "Unable to retreive places");
        }

    }

    public List<Hiker> getAllHikers() throws CustomException {
        try {
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
        } catch (CustomException e) {
            throw new CustomException(ErrorType.HIKER_GET_ALL_ERROR, "Unable to retreive hikers");
        }
    }

    public List<HikingGroup> getAllHikingGroups() throws CustomException {
        try {
            try {
                repositoryManager.getRepositoryHikingGroup().connect();
                List<HikingGroup> groups = repositoryManager.getRepositoryHikingGroup().getAll();
                repositoryManager.getRepositoryHikingGroup().commit();
                return groups;
            } catch (CustomException ex) {
                repositoryManager.getRepositoryHikingGroup().rollback();
                throw ex;
            } finally {
                repositoryManager.getRepositoryHikingGroup().disconnect();
            }
        } catch (CustomException e) {
            throw new CustomException(ErrorType.HIKING_GROUP_GET_ALL_ERROR, "Unable to retreive hiking groups");
        }
    }

    public List<HikingGroup> getAllHikingGroupsWithHikers() throws CustomException {
        try {
            List<HikingGroup> groups = new LinkedList<>();
            try {
                repositoryManager.getRepositoryHikingGroup().connect();
                groups = repositoryManager.getRepositoryHikingGroup().getAll();
                repositoryManager.getRepositoryHikingGroup().commit();
            } catch (CustomException ex) {
                repositoryManager.getRepositoryHikingGroup().rollback();
                throw ex;
            } finally {
                repositoryManager.getRepositoryHikingGroup().disconnect();
            }
            try {
                repositoryManager.getRepositoryProfiles().connect();
                List<Profile> profiles = repositoryManager.getRepositoryProfiles().getAll();
                repositoryManager.getRepositoryProfiles().commit();
                for (HikingGroup group : groups) {
                    for (Profile profile : profiles) {
                        if (group.equals(profile.getHikingGroup())) {
                            group.getProfiles().add(profile);
                        }
                    }
                }
                return groups;
            } catch (CustomException | NullPointerException ex) {
                repositoryManager.getRepositoryProfiles().rollback();
                throw ex;
            } finally {
                repositoryManager.getRepositoryProfiles().disconnect();
            }

        } catch (CustomException | NullPointerException e) {
            throw new CustomException(ErrorType.HIKING_GROUP_GET_ALL_ERROR, "Unable to retreive hiking groups");
        }
    }

    public List<Hiker> getAllHikersWithHikingGroups() throws CustomException {
        try {
            List<Hiker> hikers = new LinkedList<>();
            try {
                repositoryManager.getRepositoryHiker().connect();
                hikers = repositoryManager.getRepositoryHiker().getAll();
                repositoryManager.getRepositoryHiker().commit();
            } catch (CustomException ex) {
                repositoryManager.getRepositoryHiker().rollback();
                throw ex;
            } finally {
                repositoryManager.getRepositoryHiker().disconnect();
            }
            try {
                repositoryManager.getRepositoryProfiles().connect();
                List<Profile> profiles = repositoryManager.getRepositoryProfiles().getAll();
                repositoryManager.getRepositoryProfiles().commit();
                for (Hiker hiker : hikers) {
                    for (Profile profile : profiles) {
                        if (hiker.equals(profile.getHiker())) {
                            hiker.getProfiles().add(profile);
                        }
                    }
                }
                return hikers;
            } catch (CustomException | NullPointerException ex) {
                repositoryManager.getRepositoryProfiles().rollback();
                throw ex;
            } finally {
                repositoryManager.getRepositoryProfiles().disconnect();
            }
        } catch (CustomException | NullPointerException e) {
            throw new CustomException(ErrorType.HIKER_GET_ALL_ERROR, "Unable to retreive hikers");
        }
    }

    public List<Profile> getAllProfiles() throws CustomException {
        try {
            try {
                repositoryManager.getRepositoryProfiles().connect();
                List<Profile> profiles = repositoryManager.getRepositoryProfiles().getAll();
                repositoryManager.getRepositoryProfiles().commit();
                return profiles;
            } catch (CustomException ex) {
                repositoryManager.getRepositoryProfiles().rollback();
                throw ex;
            } finally {
                repositoryManager.getRepositoryProfiles().disconnect();
            }
        } catch (CustomException e) {
            throw new CustomException(ErrorType.PROFILES_GET_ALL_ERROR, "Unable to retreive profiles");
        }

    }

    public void register(User user) throws CustomException {
        try {
            try {
                repositoryManager.getRepositoryUser().connect();
                User databaseUser = repositoryManager.getRepositoryUser().find(user);
                if (databaseUser == null) {
                    repositoryManager.getRepositoryUser().insert(user);
                } else {
                    throw new CustomException(ErrorType.REGISTRATION_DUPLICATE_USER_ERROR, "Username " + user.getUsername() + " already exists");
                }
                repositoryManager.getRepositoryUser().commit();
            } catch (CustomException ex) {
                repositoryManager.getRepositoryUser().rollback();
                throw ex;
            } finally {
                repositoryManager.getRepositoryUser().disconnect();
            }
        } catch (CustomException e) {
            if (e.getErrorType() == ErrorType.REGISTRATION_DUPLICATE_USER_ERROR) {
                throw new CustomException(ErrorType.REGISTRATION_ERROR, "Given user already exists");
            } else {
                throw new CustomException(ErrorType.REGISTRATION_ERROR, "Unable to register user");
            }
        }
    }

    public User login(User user) throws CustomException {
        try {
            User databaseUser;
            try {
                repositoryManager.getRepositoryUser().connect();
                databaseUser = repositoryManager.getRepositoryUser().find(user);
                if (databaseUser != null) {
                    if (!databaseUser.getPassword().equals(user.getPassword())) {
                        throw new CustomException(ErrorType.LOGIN_INVALID_USER_ERROR, "Invalid password for given username!");
                    }
                } else {
                    throw new CustomException(ErrorType.LOGIN_INVALID_USER_ERROR, "Username " + user.getUsername() + " does not exist!");
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
        } catch (CustomException e) {
            if (e.getErrorType() == ErrorType.LOGIN_INVALID_USER_ERROR) {
                throw new CustomException(ErrorType.LOGIN_ERROR, "Given user does not exist");
            } else {
                throw new CustomException(ErrorType.LOGIN_ERROR, "Unable to login user");
            }
        }

    }

    public void createHiker(Hiker hiker) throws CustomException {
        try {
            try {
                repositoryManager.getRepositoryHiker().connect();
                Hiker h = repositoryManager.getRepositoryHiker().find(hiker);
                if (h == null) {
                    repositoryManager.getRepositoryHiker().insert(hiker);
                } else {
                    throw new CustomException(ErrorType.HIKER_INVALID_ERROR, "Hiker " + hiker.getUcin() + " already exists!");
                }
                repositoryManager.getRepositoryHiker().commit();
            } catch (CustomException ex) {
                repositoryManager.getRepositoryHiker().rollback();
                throw ex;
            } finally {
                repositoryManager.getRepositoryHiker().disconnect();
            }
        } catch (CustomException e) {
            if (e.getErrorType() == ErrorType.HIKER_INVALID_ERROR) {
                throw new CustomException(ErrorType.HIKER_CREATION_ERROR, "Given hiker already exists");
            } else {
                throw new CustomException(ErrorType.HIKER_CREATION_ERROR, "Unable to create hiker");
            }
        }
    }

    public List<Hiker> findHikers(String name) throws CustomException {
        List<Hiker> hikersToBeReturned = null;
        name = name.trim();
        try {
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
        } catch (CustomException e) {
            throw new CustomException(ErrorType.SEARCH_ERROR, "Unable to find hikers");
        }
        return hikersToBeReturned;
    }

    public void updateHiker(Hiker h) throws CustomException {
        try {
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
        } catch (CustomException e) {
            throw new CustomException(ErrorType.HIKER_UPDATE_ERROR, "Unable to update hiker");
        }
    }

    public void deleteHiker(Hiker h) throws CustomException {
        try {
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
        } catch (CustomException e) {
            throw new CustomException(ErrorType.HIKER_DELETE_ERROR, "Unable to delete hiker");
        }
    }

    public void createHikingGroup(HikingGroup mainGroup) throws CustomException {
        try {
            try {
                repositoryManager.getRepositoryHikingGroup().connect();
                HikingGroup g = repositoryManager.getRepositoryHikingGroup().find(mainGroup);
                if (g == null) {
                    repositoryManager.getRepositoryHikingGroup().insert(mainGroup);
                } else {
                    System.out.println("aaaaaaaa");
                    throw new CustomException(ErrorType.HIKING_GROUP_INVALID_ERROR, "Hiking group " + mainGroup.getCrn() + " already exists!");
                }
                repositoryManager.getRepositoryHikingGroup().commit();
            } catch (CustomException ex) {
                repositoryManager.getRepositoryHikingGroup().rollback();
                throw ex;
            } finally {
                repositoryManager.getRepositoryHikingGroup().disconnect();
            }
        } catch (CustomException e) {
            if (e.getErrorType() == ErrorType.HIKING_GROUP_INVALID_ERROR) {
                throw new CustomException(ErrorType.HIKING_GROUP_CREATION_ERROR, "Given hiking group already exists");
            } else {
                throw new CustomException(ErrorType.HIKING_GROUP_CREATION_ERROR, "Unable to create hiking group");
            }
        }
    }

    public List<HikingGroup> findHikingGroups(String name) throws CustomException {
        List<HikingGroup> groupsToBeReturned = null;
        name = name.trim();
        try {
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
        } catch (CustomException e) {
            throw new CustomException(ErrorType.SEARCH_ERROR, "Unable to find hiking groups");
        }
        return groupsToBeReturned;
    }

    public void deleteHikingGroup(HikingGroup g) throws CustomException {
        try {
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
        } catch (CustomException e) {
            throw new CustomException(ErrorType.HIKING_GROUP_UPDATE_ERROR, "Unable to delete hiking group");
        }
    }

    public void updateHikingGroup(HikingGroup group) throws CustomException {
        try {
            try {
                repositoryManager.getRepositoryHikingGroup().connect();
                repositoryManager.getRepositoryHikingGroup().update(group);
                repositoryManager.getRepositoryHikingGroup().commit();
            } catch (CustomException ex) {
                repositoryManager.getRepositoryHikingGroup().rollback();
                throw ex;
            } finally {
                repositoryManager.getRepositoryHikingGroup().disconnect();
            }
        } catch (CustomException e) {
            e.printStackTrace();
            throw new CustomException(ErrorType.HIKING_GROUP_UPDATE_ERROR, "Unable to update hiking group");
        }
    }

    public void logout(User loggedUser) throws CustomException {
        repositoryManager.getRepositoryLoggedUsers().remove(loggedUser);
    }

    public void saveProfiles(List<Profile> profiles) throws CustomException {
        try {
            try {
                repositoryManager.getRepositoryProfiles().connect();
                repositoryManager.getRepositoryProfiles().deleteAll();
                repositoryManager.getRepositoryProfiles().insertAll(profiles);
                repositoryManager.getRepositoryProfiles().commit();
            } catch (CustomException ex) {
                repositoryManager.getRepositoryProfiles().rollback();
                throw ex;
            } finally {
                repositoryManager.getRepositoryProfiles().disconnect();
            }
        } catch (CustomException e) {
            throw new CustomException(ErrorType.PROFILES_SAVE_ERROR, "Unable to save profiles");
        }
    }

    public HikingGroupPlan getHikingGroupPlan(HikingGroup group) throws CustomException {
        try {
            List<Hiker> members = new LinkedList<>();
            try {
                repositoryManager.getRepositoryProfiles().connect();
                List<Profile> profiles = repositoryManager.getRepositoryProfiles().getAll();
                for (Profile profile : profiles) {
                    if (profile.getHikingGroup() != null && profile.getHikingGroup().equals(group) && profile.getHiker() != null) {
                        members.add(profile.getHiker());
                    }
                }
                repositoryManager.getRepositoryProfiles().commit();
            } catch (CustomException ex) {
                repositoryManager.getRepositoryProfiles().rollback();
                throw ex;
            } finally {
                repositoryManager.getRepositoryProfiles().disconnect();
            }
            try {
                repositoryManager.getRepositoryHikingGroup().connect();
                HikingGroup g = repositoryManager.getRepositoryHikingGroup().find(group);
                HikingGroupPlan plan = new HikingGroupPlan(members, g.getGroupActivities());
                repositoryManager.getRepositoryHikingGroup().commit();
                return plan;
            } catch (CustomException ex) {
                repositoryManager.getRepositoryHikingGroup().rollback();
                throw ex;
            } finally {
                repositoryManager.getRepositoryHikingGroup().disconnect();
            }
        } catch (CustomException e) {
            throw new CustomException(ErrorType.GET_HIKING_GROUP_PLAN_ERROR, "Unable to retreive group plan");
        }
    }

}
