/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.repository.db.mysql;

import com.borak.hikingapp.commonlib.domain.classes.HikingActivity;
import com.borak.hikingapp.commonlib.domain.classes.HikingGroup;
import com.borak.hikingapp.commonlib.domain.classes.Place;
import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.server.repository.db.connections.DatabaseConnectionManager;
import com.borak.hikingapp.server.repository.db.mysql.queries.QueryHikingActivity;
import com.borak.hikingapp.server.repository.db.mysql.queries.QueryHikingGroup;
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
public class RepositoryHikingGroup extends DatabaseConnectionManager<HikingGroup> {

    @Override
    public List<HikingGroup> getAll() throws CustomException {
        String queryGroup = QueryHikingGroup.getAll();
        try {
            Statement statementGroups;
            ResultSet rsGroups;

            PreparedStatement statementActivities;
            ResultSet rsActivities;

            statementGroups = connection.createStatement();
            rsGroups = statementGroups.executeQuery(queryGroup);
            HikingGroup g = null;
            List<HikingGroup> groups = new LinkedList<>();
            while (rsGroups.next()) {
                Long gId = rsGroups.getLong(1);
                String gCrn = rsGroups.getString(2);
                String gName = rsGroups.getString(3);
                String gDescription = rsGroups.getString(4);
                String gResources = rsGroups.getString(5);
                Boolean gHasLiscence = rsGroups.getBoolean(6);

                Long pId = rsGroups.getLong(7);
                String pName = rsGroups.getString(8);

                g = new HikingGroup(gId, gCrn, gName, gDescription, gResources, gHasLiscence, new Place(pId, pName), null);

                //find all activities of found hiking group
                String queryActivities = QueryHikingActivity.getAllByHikingGroup();
                statementActivities = connection.prepareStatement(queryActivities);
                statementActivities.setLong(1, gId);
                rsActivities = statementActivities.executeQuery();
                List<HikingActivity> groupActivities = new LinkedList<>();
                while (rsActivities.next()) {
                    Integer aOrN = rsActivities.getInt(2);
                    String aName = rsActivities.getString(3);
                    String aDescription = rsActivities.getString(4);
                    GregorianCalendar aDate = new GregorianCalendar();
                    aDate.setTimeInMillis(rsActivities.getDate(5).getTime());

                    Long apId = rsActivities.getLong(6);
                    String apName = rsActivities.getString(7);

                    groupActivities.add(new HikingActivity(aOrN, aName, aDescription, aDate, new Place(apId, apName), g));
                }
                g.setGroupActivities(groupActivities);
                groups.add(g);

                rsActivities.close();
                statementActivities.close();
            }
            rsGroups.close();
            statementGroups.close();
            return groups;
        } catch (NullPointerException ex) {
            throw new CustomException(ErrorType.SELECT_QUERY_ERROR, "Connection must be established first, in order to get hiking groups!", ex);
        } catch (SQLException ex) {
            throw new CustomException(ErrorType.SELECT_QUERY_ERROR, "Unable to get hiking groups!", ex);
        }
    }

    @Override
    public void insert(HikingGroup object) throws CustomException {
        String queryGroup = QueryHikingGroup.insert();
        try {
            PreparedStatement statement = connection.prepareStatement(queryGroup, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, object.getCrn());
            statement.setString(2, object.getName());
            statement.setString(3, object.getDescription());
            statement.setString(4, object.getResources());
            statement.setBoolean(5, object.isHasLiscence());
            statement.setLong(6, object.getPlace().getId());
            statement.executeUpdate();

            Long aId = null;
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                aId = rs.getLong(1);
            }
            if (object.getGroupActivities() != null && !object.getGroupActivities().isEmpty()) {
                String queryActivities = QueryHikingActivity.insert();
                statement = connection.prepareStatement(queryActivities);
                for (HikingActivity groupActivity : object.getGroupActivities()) {
                    statement.setLong(1, aId);
                    statement.setInt(2, groupActivity.getOrderNum());
                    statement.setString(3, groupActivity.getName());
                    statement.setString(4, groupActivity.getDescription());
                    statement.setDate(5, new Date(groupActivity.getDate().getTimeInMillis()));
                    statement.setLong(6, groupActivity.getPlace().getId());
                    statement.addBatch();
                }
                statement.executeBatch();
            }
            rs.close();
            statement.close();
        } catch (NullPointerException ex) {
            throw new CustomException(ErrorType.INSERT_QUERY_ERROR, "Connection must be established first, in order to insert hiking group: " + object, ex);
        } catch (SQLException ex) {
            throw new CustomException(ErrorType.INSERT_QUERY_ERROR, "Unable to insert hiking group: " + object, ex);
        }

    }

    @Override
    public HikingGroup find(HikingGroup object) throws CustomException {
        String queryGroup = QueryHikingGroup.findByCRN();
        try {
            PreparedStatement statementGroup;
            ResultSet rsGroup;

            PreparedStatement statementActivities;
            ResultSet rsActivities;

            statementGroup = connection.prepareStatement(queryGroup);
            statementGroup.setString(1, object.getCrn());
            rsGroup = statementGroup.executeQuery();
            HikingGroup g = null;
            while (rsGroup.next()) {
                Long gId = rsGroup.getLong(1);
                String gCrn = rsGroup.getString(2);
                String gName = rsGroup.getString(3);
                String gDescription = rsGroup.getString(4);
                String gResources = rsGroup.getString(5);
                Boolean gHasLiscence = rsGroup.getBoolean(6);
                Long pId = rsGroup.getLong(7);
                String pName = rsGroup.getString(8);
                g = new HikingGroup(gCrn, gName, gDescription, gResources, gHasLiscence, new Place(pId, pName), null);

                //find all activities of found hiking group
                String queryActivities = QueryHikingActivity.getAllByHikingGroup();
                statementActivities = connection.prepareStatement(queryActivities);
                statementActivities.setLong(1, gId);
                rsActivities = statementActivities.executeQuery();
                List<HikingActivity> groupActivities = new LinkedList<>();
                while (rsActivities.next()) {
                    Integer aOrN = rsGroup.getInt(2);
                    String aName = rsGroup.getString(3);
                    String aDescription = rsGroup.getString(4);
                    GregorianCalendar aDate = new GregorianCalendar();
                    aDate.setTimeInMillis(rsGroup.getDate(5).getTime());
                    Long apId = rsGroup.getLong(6);
                    String apName = rsGroup.getString(7);
                    groupActivities.add(new HikingActivity(aOrN, aName, aDescription, aDate, new Place(apId, apName), g));
                }
                rsActivities.close();
                statementActivities.close();
                g.setGroupActivities(groupActivities);
                break;
            }
            rsGroup.close();
            statementGroup.close();
            return g;
        } catch (NullPointerException ex) {
            throw new CustomException(ErrorType.SELECT_QUERY_ERROR, "Connection must be established first, in order to find hiking group!", ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new CustomException(ErrorType.SELECT_QUERY_ERROR, "Unable to find hiking group!", ex);
        }
    }

    @Override
    public void delete(HikingGroup object) throws CustomException {
        String query = QueryHikingGroup.deleteByCRN();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, object.getCrn());
            statement.executeUpdate();
            statement.close();
        } catch (NullPointerException ex) {
            throw new CustomException(ErrorType.DELETE_QUERY_ERROR, "Connection must be established first, in order to delete hiking group", ex);
        } catch (SQLException ex) {
            throw new CustomException(ErrorType.DELETE_QUERY_ERROR, "Unable to delete hiking group!", ex);
        }
    }

    @Override
    public void update(HikingGroup object) throws CustomException {
        String queryUpdateGroup = QueryHikingGroup.updateByCRN();
        try {
            PreparedStatement statementUpdateGroup = connection.prepareStatement(queryUpdateGroup);
            statementUpdateGroup.setString(1, object.getCrn());
            statementUpdateGroup.setString(2, object.getName());
            statementUpdateGroup.setString(3, object.getDescription());
            statementUpdateGroup.setString(4, object.getResources());
            statementUpdateGroup.setBoolean(5, object.isHasLiscence());
            statementUpdateGroup.setLong(6, object.getPlace().getId());
            statementUpdateGroup.setString(7, object.getCrn());
            statementUpdateGroup.executeUpdate();

            String queryDeleteActivities = QueryHikingActivity.deleteByHikingGroup();
            PreparedStatement statementDeleteActivities = connection.prepareStatement(queryDeleteActivities);
            statementDeleteActivities.setLong(1, object.getId());
            statementDeleteActivities.executeUpdate();

            if (object.getGroupActivities() != null && !object.getGroupActivities().isEmpty()) {
                String queryInsertActivities = QueryHikingActivity.insert();
                PreparedStatement statementInsertActivities = connection.prepareStatement(queryInsertActivities);
                for (HikingActivity groupActivity : object.getGroupActivities()) {
                    statementInsertActivities.setLong(1, object.getId());
                    statementInsertActivities.setInt(2, groupActivity.getOrderNum());
                    statementInsertActivities.setString(3, groupActivity.getName());
                    statementInsertActivities.setString(4, groupActivity.getDescription());
                    statementInsertActivities.setDate(5, new Date(groupActivity.getDate().getTimeInMillis()));
                    statementInsertActivities.setLong(6, groupActivity.getPlace().getId());
                    statementInsertActivities.addBatch();
                }
                statementInsertActivities.executeBatch();
                statementInsertActivities.close();
            }
            statementDeleteActivities.close();
            statementUpdateGroup.close();
        } catch (NullPointerException ex) {
            throw new CustomException(ErrorType.UPDATE_QUERY_ERROR, "Connection must be established first, in order to update hiking group", ex);
        } catch (SQLException ex) {
            throw new CustomException(ErrorType.UPDATE_QUERY_ERROR, "Unable to update hiking group!", ex);
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
}
