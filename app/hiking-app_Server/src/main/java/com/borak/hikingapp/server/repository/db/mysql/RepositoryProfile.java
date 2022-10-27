/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.repository.db.mysql;

import com.borak.hikingapp.commonlib.domain.classes.Hiker;
import com.borak.hikingapp.commonlib.domain.classes.HikingActivity;
import com.borak.hikingapp.commonlib.domain.classes.HikingGroup;
import com.borak.hikingapp.commonlib.domain.classes.Place;
import com.borak.hikingapp.commonlib.domain.classes.Profile;
import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.domain.enums.Gender;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.server.repository.db.connections.DatabaseConnectionManager;
import com.borak.hikingapp.server.repository.db.mysql.queries.QueryHikingActivity;
import com.borak.hikingapp.server.repository.db.mysql.queries.QueryProfile;
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
 * @author User
 */
public class RepositoryProfile extends DatabaseConnectionManager<Profile> {

    @Override
    public List<Profile> getAll() throws CustomException {
        String query = QueryProfile.getAll();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            List<Profile> profiles = new LinkedList<>();
            while (rs.next()) {
                Long gId = rs.getLong(1);
                String gCrn = rs.getString(2);
                String gName = rs.getString(3);
                String gDescription = rs.getString(4);
                String gResources = rs.getString(5);
                Boolean gHasLiscence = rs.getBoolean(6);
                Long gPId = rs.getLong(7);
                String gPName = rs.getString(8);
                HikingGroup g = new HikingGroup(gId, gCrn, gName, gDescription, gResources, gHasLiscence, new Place(gPId, gPName), null);

                String queryActivities = QueryHikingActivity.getAllByHikingGroup();
                PreparedStatement statementActivities = connection.prepareStatement(queryActivities);
                statementActivities.setLong(1, gId);
                ResultSet rsActivities = statementActivities.executeQuery();
                List<HikingActivity> groupActivities = new LinkedList<>();
                while (rsActivities.next()) {
                    Integer aOrN = rsActivities.getInt(2);
                    String aName = rsActivities.getString(3);
                    String aDescription = rsActivities.getString(4);
                    GregorianCalendar aDate = new GregorianCalendar();
                    aDate.setTimeInMillis(rsActivities.getDate(5).getTime());

                    Long aPId = rsActivities.getLong(6);
                    String aPName = rsActivities.getString(7);

                    groupActivities.add(new HikingActivity(aOrN, aName, aDescription, aDate, new Place(aPId, aPName), g));
                }
                g.setGroupActivities(groupActivities);
                rsActivities.close();
                statementActivities.close();

                Long hId = rs.getLong(9);
                String hUcin = rs.getString(10);
                String hFirstName = rs.getString(11);
                String hLastName = rs.getString(12);
                Gender hGender = Gender.getGender(rs.getString(13));
                GregorianCalendar hDate = new GregorianCalendar();
                hDate.setTimeInMillis(rs.getDate(14).getTime());
                Integer hYears = rs.getInt(15);
                Long hPId = rs.getLong(16);
                String hPName = rs.getString(17);
                Hiker h = new Hiker(hId, hUcin, hFirstName, hLastName, hGender, hDate, hYears, new Place(hPId, hPName));
                
                GregorianCalendar date = new GregorianCalendar();
                date.setTimeInMillis(rs.getDate(18).getTime());
                String note = rs.getString(19);
                profiles.add(new Profile(g, h, date, note));
            }
            rs.close();
            statement.close();
            return profiles;
        } catch (NullPointerException ex) {
            throw new CustomException(ErrorType.SELECT_QUERY_ERROR, "Connection must be established first, in order to get profiles!", ex);
        } catch (SQLException ex) {
            throw new CustomException(ErrorType.SELECT_QUERY_ERROR, "Unable to get profiles!", ex);
        }
    }

    @Override
    public void insert(Profile object) throws CustomException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Profile find(Profile object) throws CustomException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertAll(List<Profile> object) throws CustomException {
        try {
            String query = QueryProfile.insert();
            PreparedStatement statement = connection.prepareStatement(query);
            for (Profile profile : object) {
                statement.setLong(1, profile.getHikingGroup().getId());
                statement.setLong(2, profile.getHiker().getId());
                Date d = new Date(profile.getDateOfEnrollment().getTimeInMillis());
                statement.setDate(3, d);
                statement.setString(4, profile.getNote());
                statement.addBatch();
            }
            statement.executeBatch();
            statement.close();
        } catch (NullPointerException ex) {
            throw new CustomException(ErrorType.INSERT_QUERY_ERROR, "Connection must be established first, in order to insert profiles", ex);
        } catch (SQLException ex) {
            throw new CustomException(ErrorType.INSERT_QUERY_ERROR, "Unable to insert profiles", ex);
        }
    }

    @Override
    public void delete(Profile object) throws CustomException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteAll() throws CustomException {
        try {
            String query = QueryProfile.deleteAll();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (NullPointerException ex) {
            throw new CustomException(ErrorType.PROFILES_DELETE_ERROR, "Connection must be established first, in order to delete profiles", ex);
        } catch (SQLException ex) {
            throw new CustomException(ErrorType.PROFILES_DELETE_ERROR, "Unable to delete profiles!", ex);
        }
    }

    @Override
    public void update(Profile object) throws CustomException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
