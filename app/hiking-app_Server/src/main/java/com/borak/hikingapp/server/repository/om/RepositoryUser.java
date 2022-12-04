/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.repository.om;

import com.borak.hikingapp.commonlib.domain.classes.Place;
import com.borak.hikingapp.commonlib.domain.classes.User;
import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.server.repository.IRepository;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author User
 */
public class RepositoryUser implements IRepository<User> {
    
    private List<User> users;
    
    public RepositoryUser() {
        users = new LinkedList<>();
        User u1 = new User("Despot", "Jevtovic", "admin", "admin", "despotjev@gmail.com", new Place(1l, "Beograd"));
        User u2 = new User("Mateja", "Stoisavljevic", "admin1", "admin1", "mataM@gmail.com", new Place(3l, "Novi Sad"));
        User u3 = new User("Milica", "Jevtovic", "admin2", "admin2", "desmil@gmail.com", new Place(2l, "Lazarevac"));
        u1.setId(1l);
        u2.setId(2l);
        u3.setId(3l);
        users.add(u1);
        users.add(u2);
        users.add(u3);
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
    public List<User> getAll() throws CustomException {
        return users;
    }
    
    @Override
    public void insert(User object) throws CustomException {
        try {
            for (User user : users) {
                if (user.getUsername().equals(object.getUsername())) {
                    throw new CustomException(ErrorType.INSERT_QUERY_ERROR, "Duplicate username");
                }
            }
        } catch (NullPointerException | CustomException e) {
            throw new CustomException(ErrorType.INSERT_QUERY_ERROR, "Unable to insert user", e);
        }
        object.setId(getMaxId());
        users.add(object);
    }
    
    @Override
    public User find(User object) throws CustomException {
        try {
            for (User user : users) {
                if (user.getUsername().equals(object.getUsername())) {
                    return user;
                }
            }
        } catch (NullPointerException e) {
            throw new CustomException(ErrorType.SELECT_QUERY_ERROR, "Invalid argument passed");
        }
        return null;
    }
    
    @Override
    public void delete(User object) throws CustomException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void update(User object) throws CustomException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void insertAll(List<User> object) throws CustomException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void deleteAll() throws CustomException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private Long getMaxId() {
        Long i = 0l;
        for (User user : users) {
            if (i.compareTo(user.getId()) < 0) {
                i = user.getId();
            }
        }
        return i;
    }
    
}
