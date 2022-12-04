/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.repository.om;

import com.borak.hikingapp.commonlib.domain.classes.Profile;
import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.server.repository.IRepository;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Mr. Poyo
 */
public class RepositoryProfile implements IRepository<Profile> {

    private List<Profile> profiles;

    public RepositoryProfile() {
        profiles = new LinkedList<>();
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
    public List<Profile> getAll() throws CustomException {
        return profiles;
    }

    @Override
    public void insert(Profile object) throws CustomException {
        try {
            for (Profile profile : profiles) {
                if (profile.getHikingGroup().equals(object.getHikingGroup()) && profile.getHiker().equals(object.getHiker())) {
                    throw new CustomException(ErrorType.INSERT_QUERY_ERROR, "Duplicate profile");
                }
            }
        } catch (NullPointerException | CustomException e) {
            throw new CustomException(ErrorType.INSERT_QUERY_ERROR, "Unable to insert profile", e);
        }
        profiles.add(object);
    }

    @Override
    public void insertAll(List<Profile> objects) throws CustomException {
        try {
            for (Profile object : objects) {
                for (Profile profile : profiles) {
                    if (profile.getHikingGroup().equals(object.getHikingGroup()) && profile.getHiker().equals(object.getHiker())) {
                        throw new CustomException(ErrorType.INSERT_QUERY_ERROR, "Duplicate profile");
                    }
                }
            }
            for (Profile object : objects) {
                profiles.add(object);
            }
        } catch (NullPointerException | CustomException e) {
            throw new CustomException(ErrorType.INSERT_QUERY_ERROR, "Unable to insert profile", e);
        }
    }

    @Override
    public Profile find(Profile object) throws CustomException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Profile object) throws CustomException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteAll() throws CustomException {
        profiles = new LinkedList<>();
    }

    @Override
    public void update(Profile object) throws CustomException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
