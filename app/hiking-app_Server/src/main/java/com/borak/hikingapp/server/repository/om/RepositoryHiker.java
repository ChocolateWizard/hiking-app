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
        try {
            for (Hiker hiker : hikers) {
                if (hiker.getUcin().equals(object.getUcin())) {
                    throw new CustomException(ErrorType.INSERT_QUERY_ERROR, "Duplicate hiker ucin");
                }
            }
        } catch (NullPointerException | CustomException e) {
            throw new CustomException(ErrorType.INSERT_QUERY_ERROR, "Unable to insert hiker", e);
        }
        object.setId(getMaxId());
        hikers.add(object);
    }

    @Override
    public Hiker find(Hiker object) throws CustomException {
        try {
            for (Hiker hiker : hikers) {
                if (hiker.equals(object)) {
                    return hiker;
                }
            }
        } catch (NullPointerException e) {
            throw new CustomException(ErrorType.SELECT_QUERY_ERROR, "Unable to find hiker", e);
        }
        return null;
    }

    @Override
    public void delete(Hiker object) throws CustomException {
        try {
            hikers.remove(object);
        } catch (NullPointerException e) {
            throw new CustomException(ErrorType.DELETE_QUERY_ERROR, "Unable to delete hiker", e);
        }
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

    @Override
    public void insertAll(List<Hiker> object) throws CustomException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteAll() throws CustomException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private Long getMaxId() {
        Long i = 0l;
        for (Hiker hiker : hikers) {
            if (i.compareTo(hiker.getId()) < 0) {
                i = hiker.getId();
            }
        }
        return i;
    }

}
