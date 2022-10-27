/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.repository.db.mysql;

import com.borak.hikingapp.commonlib.domain.classes.Hiker;
import com.borak.hikingapp.commonlib.domain.classes.Place;
import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.domain.enums.Gender;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.server.repository.db.connections.DatabaseConnectionManager;
import com.borak.hikingapp.server.repository.db.mysql.queries.QueryHiker;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Despot
 */
public class RepositoryHiker extends DatabaseConnectionManager<Hiker> {

    @Override
    public List<Hiker> getAll() throws CustomException {
        String query = QueryHiker.getAll();
        List<Hiker> hikers = new LinkedList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            Hiker h;
            Place p;
            while (rs.next()) {
                Long id = rs.getLong(1);
                String ucin = rs.getString(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                Gender gender = Gender.getGender(rs.getString(5));
                GregorianCalendar date = new GregorianCalendar();
                date.setTimeInMillis(rs.getDate(6).getTime());
                Integer years = rs.getInt(7);
                Long placeId = rs.getLong(8);
                String name = rs.getString(9);
                p = new Place(placeId, name);
                h = new Hiker(id,ucin, firstName, lastName, gender, date, years, p);
                hikers.add(h);
            }
            rs.close();
            statement.close();
        } catch (NullPointerException ex) {
            throw new CustomException(ErrorType.SELECT_QUERY_ERROR, "Connection must be established first, in order to retreive all hikers!", ex);
        } catch (SQLException ex) {
            throw new CustomException(ErrorType.SELECT_QUERY_ERROR, "Unable to retreive hikers from database!", ex);
        }
        return hikers;
    }

    @Override
    public void insert(Hiker object) throws CustomException {
        String query = QueryHiker.insert();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, object.getUcin());
            statement.setString(2, object.getFirstName());
            statement.setString(3, object.getLastName());
            statement.setString(4, object.getGender().toString());
            Date d = new Date(object.getDateOfBirth().getTimeInMillis());
            statement.setDate(5, d);
            statement.setInt(6, object.getYearsOfExperience());
            statement.setLong(7, object.getPlace().getId());
            statement.executeUpdate();
            statement.close();
        } catch (NullPointerException ex) {
            throw new CustomException(ErrorType.INSERT_QUERY_ERROR, "Connection must be established first, in order to insert hiker: " + object, ex);
        } catch (SQLException ex) {
            throw new CustomException(ErrorType.INSERT_QUERY_ERROR, "Unable to insert hiker: " + object, ex);
        }
    }

    @Override
    public Hiker find(Hiker object) throws CustomException {
        String query = QueryHiker.findByUCIN();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, object.getUcin());
            ResultSet rs = statement.executeQuery();
            Place p;
            Hiker h;
            while (rs.next()) {
                Long id = rs.getLong(1);
                String ucin = rs.getString(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                Gender gender = Gender.getGender(rs.getString(5));
                GregorianCalendar date = new GregorianCalendar();
                date.setTimeInMillis(rs.getDate(6).getTime());
                Integer years = rs.getInt(7);
                Long placeId = rs.getLong(8);
                String name = rs.getString(9);
                p = new Place(placeId, name);
                h = new Hiker(ucin, firstName, lastName, gender, date, years, p);
                return h;
            }
            rs.close();
            statement.close();
        } catch (NullPointerException ex) {
            throw new CustomException(ErrorType.SELECT_QUERY_ERROR, "Connection must be established first, in order to find hiker", ex);
        } catch (SQLException ex) {
            throw new CustomException(ErrorType.SELECT_QUERY_ERROR, "Unable to find hiker!", ex);
        }
        return null;
    }

    @Override
    public void delete(Hiker object) throws CustomException {
        String query = QueryHiker.deleteByUCIN();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, object.getUcin());
            statement.executeUpdate();
            statement.close();
        } catch (NullPointerException ex) {
            throw new CustomException(ErrorType.DELETE_QUERY_ERROR, "Connection must be established first, in order to delete hiker", ex);
        } catch (SQLException ex) {
            throw new CustomException(ErrorType.DELETE_QUERY_ERROR, "Unable to delete hiker!", ex);
        }
    }

    @Override
    public void update(Hiker object) throws CustomException {
        String query = QueryHiker.updateByUCIN();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, object.getUcin());
            statement.setString(2, object.getFirstName());
            statement.setString(3, object.getLastName());
            statement.setString(4, object.getGender().toString());
            Date d = new Date(object.getDateOfBirth().getTimeInMillis());
            statement.setDate(5, d);
            statement.setInt(6, object.getYearsOfExperience());
            statement.setLong(7, object.getPlace().getId());
            statement.setString(8, object.getUcin());
            statement.executeUpdate();
            statement.close();
        } catch (NullPointerException ex) {
            throw new CustomException(ErrorType.UPDATE_QUERY_ERROR, "Connection must be established first, in order to update hiker: " + object, ex);
        } catch (SQLException ex) {
            throw new CustomException(ErrorType.UPDATE_QUERY_ERROR, "Unable to update hiker: " + object, ex);
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
}
