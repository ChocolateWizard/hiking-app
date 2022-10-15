/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.repository.db.mysql;

import com.borak.hikingapp.commonlib.domain.classes.Place;
import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.server.repository.db.connections.DatabaseConnectionManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Despot
 */
public class RepositoryPlace extends DatabaseConnectionManager<Place> {

    @Override
    public List<Place> getAll() throws CustomException {
        String query = Place.getAllQuery();
        List<Place> places = new LinkedList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            Place p;
            while (rs.next()) {
                Long id = rs.getLong(1);
                String name = rs.getString(2);
                p = new Place(id, name);
                places.add(p);
            }
            rs.close();
            statement.close();
        } catch (NullPointerException ex) {
            throw new CustomException(ErrorType.SELECT_QUERY_ERROR, "Connection must be established first, in order to retreive all places!", ex);
        } catch (SQLException ex) {
            throw new CustomException(ErrorType.SELECT_QUERY_ERROR, "Unable to retreive places from database!", ex);
        }
        return places;
    }

    @Override
    public void insert(Place object) throws CustomException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Place find(Place object) throws CustomException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Place object) throws CustomException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Place object) throws CustomException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
