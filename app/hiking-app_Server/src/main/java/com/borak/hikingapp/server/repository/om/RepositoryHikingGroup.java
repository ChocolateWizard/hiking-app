/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.repository.om;

import com.borak.hikingapp.commonlib.domain.classes.HikingGroup;
import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.server.repository.IRepository;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author User
 */
public class RepositoryHikingGroup implements IRepository<HikingGroup> {

    private List<HikingGroup> hikingGroups;

    public RepositoryHikingGroup() {
        hikingGroups = new LinkedList<>();
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
    public List<HikingGroup> getAll() throws CustomException {
        return hikingGroups;
    }

    @Override
    public void insert(HikingGroup object) throws CustomException {
        try {
            for (HikingGroup group : hikingGroups) {
                if (group.getCrn().equals(object.getCrn())) {
                    throw new CustomException(ErrorType.INSERT_QUERY_ERROR, "Duplicate hiking group crn");
                }
            }
        } catch (NullPointerException | CustomException e) {
            throw new CustomException(ErrorType.INSERT_QUERY_ERROR, "Unable to insert hiking group", e);
        }
        object.setId(getMaxId());
        hikingGroups.add(object);
    }

    @Override
    public HikingGroup find(HikingGroup object) throws CustomException {
        try {
            for (HikingGroup hikingGroup : hikingGroups) {
                if (hikingGroup.equals(object)) {
                    return hikingGroup;
                }
            }
        } catch (NullPointerException e) {
            throw new CustomException(ErrorType.SELECT_QUERY_ERROR, "Unable to find hiking group", e);
        }
        return null;
    }

    @Override
    public void delete(HikingGroup object) throws CustomException {
        try {
            hikingGroups.remove(object);
        } catch (NullPointerException e) {
            throw new CustomException(ErrorType.DELETE_QUERY_ERROR, "Unable to delete hiking group", e);
        }
    }

    @Override
    public void update(HikingGroup object) throws CustomException {
        int i = hikingGroups.indexOf(object);
        if (i != -1) {
            hikingGroups.set(i, object);
        } else {
            throw new CustomException(ErrorType.UPDATE_QUERY_ERROR, "Unable to update hiking group: " + object);
        }
    }

    @Override
    public void insertAll(List<HikingGroup> object) throws CustomException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteAll() throws CustomException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private Long getMaxId() {
        Long i = 0l;
        for (HikingGroup group : hikingGroups) {
            if (i.compareTo(group.getId()) < 0) {
                i = group.getId();
            }
        }
        return i;
    }

}
