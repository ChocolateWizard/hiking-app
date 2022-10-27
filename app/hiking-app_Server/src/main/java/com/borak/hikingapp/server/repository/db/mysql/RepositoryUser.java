/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.repository.db.mysql;

import com.borak.hikingapp.commonlib.domain.classes.Place;
import com.borak.hikingapp.commonlib.domain.classes.User;
import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.server.repository.db.connections.DatabaseConnectionManager;
import com.borak.hikingapp.server.repository.db.mysql.queries.QueryUser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Despot
 */
public class RepositoryUser extends DatabaseConnectionManager<User> {

    @Override
    public List<User> getAll() throws CustomException {
        String query = QueryUser.getAll();
        List<User> users = new LinkedList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            User u;
            Place p;
            while (rs.next()) {
                Long id = rs.getLong(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String username = rs.getString(4);
                String password = rs.getString(5);
                String email = rs.getString(6);
                Long placeId = rs.getLong(7);
                String placeName = rs.getString(8);
                p = new Place(placeId, placeName);
                u = new User(firstName, lastName, username, password, email, p);
                u.setId(id);
                users.add(u);
            }
            rs.close();
            statement.close();
        } catch (NullPointerException ex) {
            throw new CustomException(ErrorType.SELECT_QUERY_ERROR, "Connection must be established first, in order to retreive all users!", ex);
        } catch (SQLException ex) {
            throw new CustomException(ErrorType.SELECT_QUERY_ERROR, "Unable to retreive users from database!", ex);
        }
        return users;
    }

    @Override
    public void insert(User object) throws CustomException {
        String query = QueryUser.insert();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, object.getFirstName());
            statement.setString(2, object.getLastName());
            statement.setString(3, object.getUsername());
            statement.setString(4, object.getPassword());
            statement.setString(5, object.getEmail());
            statement.setLong(6, object.getPlace().getId());
            statement.executeUpdate();
            statement.close();

        } catch (NullPointerException ex) {
            throw new CustomException(ErrorType.INSERT_QUERY_ERROR, "Connection must be established first, in order to insert user: " + object, ex);
        } catch (SQLException ex) {
            throw new CustomException(ErrorType.INSERT_QUERY_ERROR, "Unable to insert user: " + object, ex);
        }
    }

    @Override
    public User find(User object) throws CustomException {
        String query = QueryUser.findByUsername();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, object.getUsername());
            ResultSet rs = statement.executeQuery();
            Place p;
            User u;
            while (rs.next()) {
                Long id = rs.getLong(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String username = rs.getString(4);
                String password = rs.getString(5);
                String email = rs.getString(6);
                Long placeId = rs.getLong(7);
                String placeName = rs.getString(8);
                p = new Place(placeId, placeName);
                u = new User(firstName, lastName, username, password, email, p);
                u.setId(id);
                return u;
            }
            rs.close();
            statement.close();
        } catch (NullPointerException ex) {
            throw new CustomException(ErrorType.SELECT_QUERY_ERROR, "Connection must be established first, in order to find user", ex);
        } catch (SQLException ex) {
            throw new CustomException(ErrorType.SELECT_QUERY_ERROR, "Unable to find user!", ex);
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

}
