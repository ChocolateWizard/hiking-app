/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.client.view.tables;

import com.borak.hikingapp.commonlib.domain.classes.Hiker;
import com.borak.hikingapp.commonlib.domain.classes.HikingGroup;
import com.borak.hikingapp.commonlib.domain.classes.Place;
import com.borak.hikingapp.commonlib.domain.classes.Profile;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */
public class HikingGroupTableModel extends AbstractTableModel {

    private HikingGroup group;
    private final String[] colNames = {"UCIN", "Name", "From", "Date of enrollment", "Note"};
    private final Class[] colTypes = {String.class, String.class, Place.class, String.class, String.class};

    public HikingGroupTableModel() {

    }

    public HikingGroupTableModel(HikingGroup group) {
        this.group = group;
    }

    @Override
    public int getRowCount() {
        if (group != null && group.getProfiles() != null) {
            return group.getProfiles().size();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        if (colNames == null) {
            return 0;
        }
        return colNames.length;

    }

    @Override
    public String getColumnName(int column) {
        if (colNames != null && column < colNames.length) {
            return colNames[column];
        }
        return "NaN";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (colTypes != null && columnIndex < colTypes.length) {
            return colTypes[columnIndex];
        }
        return Object.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Profile profile = group.getProfiles().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return profile.getHiker().getUcin();
            case 1:
                return profile.getHiker().getFirstName() + " " + profile.getHiker().getLastName();
            case 2:
                return profile.getHiker().getPlace();
            case 3:
                if (profile.getDateOfEnrollment() == null) {
                    return "";
                }
                String date = profile.getDateOfEnrollment().get(GregorianCalendar.DAY_OF_MONTH) + "/" + (profile.getDateOfEnrollment().get(GregorianCalendar.MONTH) + 1) + "/" + profile.getDateOfEnrollment().get(GregorianCalendar.YEAR);
                return date;
            case 4:
                return profile.getNote();
            default:
                return null;
        }
    }

    public void setGroup(HikingGroup group) {
        this.group = group;
        fireTableDataChanged();
    }

    public HikingGroup getGroup() {
        return group;
    }

    public void addProfile(Profile p) {
        if (group == null) {
            group = new HikingGroup();
        }
        if (group.getGroupActivities() == null) {
            group.setGroupActivities(new LinkedList<>());
        }
        if (p == null) {
            p = new Profile();
        }
        group.getProfiles().add(p);
        fireTableDataChanged();
    }

    public Profile removeProfile(int row) {
        if (group != null && group.getProfiles() != null && row >= 0 && row < group.getProfiles().size()) {
            Profile removedProfile = group.getProfiles().remove(row);
            fireTableDataChanged();
            return removedProfile;
        }
        return null;
    }

    public Profile getProfile(int row) {
        if (group != null && group.getProfiles() != null && row >= 0 && row < group.getProfiles().size()) {
            return group.getProfiles().get(row);
        }
        return null;
    }

    public int getProfileRow(Profile profile) {
        if (group != null && group.getProfiles() != null && profile != null) {
            return group.getProfiles().indexOf(profile);
        }
        return -1;
    }

    public void updateProfile(Profile p, int row) {
        if (group != null && group.getProfiles() != null && p != null && row >= 0 && row < group.getProfiles().size()) {
            group.getProfiles().set(row, p);
            fireTableDataChanged();
        }
    }

}
