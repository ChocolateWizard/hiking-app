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
        String query1 = "SELECT g.hiking_group_id,g.crn,g.name,g.description,g.resources,g.has_liscence,g.place_id,p.name "
                + "FROM hiking_group g INNER JOIN place p ON(g.place_id=p.place_id)";
        try {
            Statement statement = connection.createStatement();
            PreparedStatement statement1;
            ResultSet rs = statement.executeQuery(query1);
            Place p;
            HikingGroup g = null;
            List<HikingGroup> groups = new LinkedList<>();
            while (rs.next()) {
                Long gId = rs.getLong(1);
                String gCrn = rs.getString(2);
                String gName = rs.getString(3);
                String gDescription = rs.getString(4);
                String gResources = rs.getString(5);
                Boolean gHasLiscence = rs.getBoolean(6);
                Long pId = rs.getLong(7);
                String pName = rs.getString(8);
                p = new Place(pId, pName);
                g = new HikingGroup(gCrn, gName, gDescription, gResources, gHasLiscence, p, null);

                //find all activities of found hiking group
                String query2 = "SELECT a.hiking_group_id,a.activity_order_number,a.name,a.description,a.date "
                        + "FROM group_activity a WHERE a.hiking_group_id=?";
                statement1 = connection.prepareStatement(query2);
                statement1.setLong(1, gId);
                rs = statement1.executeQuery();
                List<HikingActivity> groupActivities = new LinkedList<>();
                while (rs.next()) {
                    Integer aOrN = rs.getInt(2);
                    String aName = rs.getString(3);
                    String aDescription = rs.getString(4);
                    GregorianCalendar aDate = new GregorianCalendar();
                    Date d = rs.getDate(5);
                    if (d == null) {
                        aDate = null;
                    } else {
                        aDate.setTimeInMillis(d.getTime());
                    }
                    groupActivities.add(new HikingActivity(aOrN, aName, aDescription, aDate, null, g));
                }
                g.setGroupActivities(groupActivities);
                groups.add(g);
            }
            rs.close();
            statement.close();
            return groups;
        } catch (NullPointerException ex) {
            throw new CustomException(ErrorType.SELECT_QUERY_ERROR, "Connection must be established first, in order to get hiking groups!", ex);
        } catch (SQLException ex) {
            throw new CustomException(ErrorType.SELECT_QUERY_ERROR, "Unable to get hiking groups!", ex);
        }
    }

    @Override
    public void insert(HikingGroup object) throws CustomException {
        String query1 = "INSERT INTO hiking_group(crn,name,description,resources,has_liscence,place_id) "
                + "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, object.getCrn());
            statement.setString(2, object.getName());
            statement.setString(3, object.getDescription());
            statement.setString(4, object.getResources());
            statement.setBoolean(5, object.isHasLiscence());
            statement.setLong(6, object.getPlace().getId());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            Long aId = null;
            while (rs.next()) {
                aId = rs.getLong(1);
            }
            if (object.getGroupActivities() != null && !object.getGroupActivities().isEmpty()) {
                String query2 = "INSERT INTO group_activity(hiking_group_id,activity_order_number,name,description,date) "
                        + "VALUES(?,?,?,?,?)";
                statement = connection.prepareStatement(query2);
                for (HikingActivity groupActivity : object.getGroupActivities()) {
                    statement.setLong(1, aId);
                    statement.setInt(2, groupActivity.getOrderNum());
                    statement.setString(3, groupActivity.getName());
                    statement.setString(4, groupActivity.getDescription());
                    GregorianCalendar g = groupActivity.getDate();
                    if (g == null) {
                        statement.setNull(5, java.sql.Types.DATE);
                    } else {
                        statement.setDate(5, new Date(g.getTimeInMillis()));
                    }
                    statement.addBatch();
                }
                statement.executeBatch();
            }
            rs.close();
            statement.close();
        } catch (NullPointerException ex) {
            throw new CustomException(ErrorType.INSERT_QUERY_ERROR, "Connection must be established first, in order to insert hiker: " + object, ex);
        } catch (SQLException ex) {
            throw new CustomException(ErrorType.INSERT_QUERY_ERROR, "Unable to insert hiker: " + object, ex);
        }

    }

    @Override
    public HikingGroup find(HikingGroup object) throws CustomException {
        String query1 = "SELECT g.hiking_group_id,g.crn,g.name,g.description,g.resources,g.has_liscence,g.place_id,p.name "
                + "FROM hiking_group g INNER JOIN place p ON(g.place_id=p.place_id) WHERE g.crn=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query1);
            statement.setString(1, object.getCrn());
            ResultSet rs = statement.executeQuery();
            Place p;
            HikingGroup g = null;
            while (rs.next()) {
                Long gId = rs.getLong(1);
                String gCrn = rs.getString(2);
                String gName = rs.getString(3);
                String gDescription = rs.getString(4);
                String gResources = rs.getString(5);
                Boolean gHasLiscence = rs.getBoolean(6);
                Long pId = rs.getLong(7);
                String pName = rs.getString(8);
                p = new Place(pId, pName);
                g = new HikingGroup(gCrn, gName, gDescription, gResources, gHasLiscence, p, null);

                //find all activities of found hiking group
                String query2 = "SELECT a.hiking_group_id,a.activity_order_number,a.name,a.description,a.date "
                        + "FROM group_activity a WHERE a.hiking_group_id=?";
                statement = connection.prepareStatement(query2);
                statement.setLong(1, gId);
                rs = statement.executeQuery();
                List<HikingActivity> groupActivities = new LinkedList<>();
                while (rs.next()) {
                    Integer aOrN = rs.getInt(2);
                    String aName = rs.getString(3);
                    String aDescription = rs.getString(4);
                    GregorianCalendar aDate = new GregorianCalendar();
                    Date d = rs.getDate(5);
                    if (d == null) {
                        aDate = null;
                    } else {
                        aDate.setTimeInMillis(d.getTime());
                    }
                    groupActivities.add(new HikingActivity(aOrN, aName, aDescription, aDate, null, g));
                }
                g.setGroupActivities(groupActivities);
                break;
            }
            rs.close();
            statement.close();
            return g;
        } catch (NullPointerException ex) {
            throw new CustomException(ErrorType.SELECT_QUERY_ERROR, "Connection must be established first, in order to find hiking group!", ex);
        } catch (SQLException ex) {
            throw new CustomException(ErrorType.SELECT_QUERY_ERROR, "Unable to find hiking group!", ex);
        }
    }

    @Override
    public void delete(HikingGroup object) throws CustomException {
        String query = "DELETE FROM hiking_group WHERE crn=?";
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
