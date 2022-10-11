/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.client.view.tables;


import com.borak.hikingapp.commonlib.domain.classes.HikingActivity;
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
    private final String[] colNames = {"Order number", "Name", "Description", "Date"};
    private final Class[] colTypes = {Integer.class, String.class, String.class, String.class};

    public GroupActivityTableModel() {
        groupActivities = new LinkedList<>();
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
            default:
                return null;
        }
    }

    public void addActivity(HikingActivity g) {
        if (groupActivities == null) {
            groupActivities = new LinkedList<>();
        }
        g.setOrderNum(groupActivities.size() + 1);
        groupActivities.add(g);
    }

    public HikingActivity removeActivity(int row) {
        if (groupActivities != null && row >= 0 && row < groupActivities.size()) {
            return groupActivities.remove(row);
        }
        return null;
    }
    
    public HikingActivity getActivity(int row) {
        if (groupActivities != null && row >= 0 && row < groupActivities.size()) {
            return groupActivities.get(row);
        }
        return null;
    }

    

    public void updateActivity(HikingActivity g) {
        if (groupActivities != null) {
            for (int i = 0; i < groupActivities.size(); i++) {
                if (Objects.equals(groupActivities.get(i).getOrderNum(), g.getOrderNum())) {
                    groupActivities.set(i, g);
                }
            }
        }
    }
    public void resetOrderNumbers(){
        int i=1;
        if(groupActivities!=null){
            for (HikingActivity groupActivity : groupActivities) {
                groupActivity.setOrderNum(i++);
            }
        }
    }
    public List<HikingActivity> getAllActivities(){
        return groupActivities;
    }
    
}
