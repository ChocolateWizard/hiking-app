/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.client.view.tables;

import com.borak.hikingapp.commonlib.domain.classes.HikingActivity;
import com.borak.hikingapp.commonlib.domain.classes.Place;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Despot
 */
public class GroupActivityTableModel extends AbstractTableModel {

    private List<HikingActivity> groupActivities;
    private final String[] colNames = {"Order number", "Name", "Description", "Date", "Place"};
    private final Class[] colTypes = {Integer.class, String.class, String.class, String.class, Place.class};

    public GroupActivityTableModel() {
        groupActivities = new ArrayList<>();
    }

    public GroupActivityTableModel(List<HikingActivity> groupActivities) {
        this.groupActivities = groupActivities;
    }

    @Override
    public int getRowCount() {
        if (groupActivities == null) {
            return 0;
        }
        return groupActivities.size();
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
        HikingActivity g = groupActivities.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return g.getOrderNum();
            case 1:
                return g.getName();
            case 2:
                return g.getDescription();
            case 3:
                if (g.getDate() == null) {
                    return "";
                }
                String date = g.getDate().get(GregorianCalendar.DAY_OF_MONTH) + "/" + (g.getDate().get(GregorianCalendar.MONTH) + 1) + "/" + g.getDate().get(GregorianCalendar.YEAR);
                return date;
            case 4:
                return g.getPlace();
            default:
                return null;
        }
    }

    public void addActivity(HikingActivity a) {
        if (groupActivities == null) {
            groupActivities = new ArrayList<>();
        }
        if (a == null) {
            a = new HikingActivity();
        }
        a.setOrderNum(groupActivities.size() + 1);
        groupActivities.add(a);
        fireTableDataChanged();
    }

    public HikingActivity removeActivity(int row) {
        if (groupActivities != null && row >= 0 && row < groupActivities.size()) {
            HikingActivity removedActivity = groupActivities.remove(row);
            for (int i = row; i < groupActivities.size(); i++) {
                groupActivities.get(i).setOrderNum(i + 1);
            }
            fireTableDataChanged();
            return removedActivity;
        }
        return null;
    }

    public HikingActivity getActivity(int row) {
        if (groupActivities != null && row >= 0 && row < groupActivities.size()) {
            return groupActivities.get(row);
        }
        return null;
    }

    public void updateActivity(HikingActivity a) {
        if (a != null && a.getOrderNum() != null) {
            int row = a.getOrderNum() - 1;
            if (groupActivities != null && row >= 0 && row < groupActivities.size()) {
                groupActivities.set(row, a);
                fireTableDataChanged();
            }
        }
    }

    public void resetOrderNumbers() {
        int i = 1;
        if (groupActivities != null) {
            for (HikingActivity groupActivity : groupActivities) {
                groupActivity.setOrderNum(i++);
            }
        }
        fireTableDataChanged();
    }

    public List<HikingActivity> getAllActivities() {
        return groupActivities;
    }

    public void loadActivities(List<HikingActivity> activities) {
        this.groupActivities = activities;
        fireTableDataChanged();
    }

}
