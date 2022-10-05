/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.repository.om;

import com.borak.hikingapp.commonlib.domain.classes.Hiker;
import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.server.repository.IRepository;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author User
 */
public class RepositoryHiker implements IRepository<Hiker> {

    private List<Hiker> hikers;

    public RepositoryHiker() {
        this.hikers = new LinkedList<>();
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
    public List<Hiker> getAll() throws CustomException {
        return hikers;
    }

    @Override
    public void insert(Hiker object) throws CustomException {
        hikers.add(object);
    }

    @Override
    public Hiker find(Hiker object) throws CustomException {
        for (Hiker hiker : hikers) {
            if (hiker.equals(object)) {
                return hiker;
            }
        }
        return null;
    }

    @Override
    public void delete(Hiker object) throws CustomException {
        hikers.remove(object);
    }

    @Override
    public void update(Hiker object) throws CustomException {
        int i = hikers.indexOf(object);
        if (i != -1) {
            hikers.set(i, object);
        } else {
            throw new CustomException(ErrorType.UPDATE_QUERY_ERROR, "Unable to update hiker: " + object);
        }
    }

}
