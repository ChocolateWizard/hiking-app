/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.client.view.tables;

import com.borak.hikingapp.commonlib.domain.classes.HikingGroup;
import com.borak.hikingapp.commonlib.domain.classes.Place;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Despot
 */
public class GroupTableModel extends AbstractTableModel {

    private List<HikingGroup> groups;
    private final String[] colNames = {"CRN", "Name", "Place"};
    private final Class[] colTypes = {String.class, String.class, Place.class};

    public GroupTableModel() {
        groups = new LinkedList<>();
    }

    public GroupTableModel(List<HikingGroup> groups) {
        this.groups = groups;
    }

    @Override
    public int getRowCount() {
        if (groups == null) {
            return 0;
        }
        return groups.size();
    }

    @Override
    public int getColumnCount() {
        if (colNames == null) {
            return 0;
        }
        return colNames.length;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (colTypes != null && columnIndex < colTypes.length) {
            return colTypes[columnIndex];
        }
        return Object.class;
    }

    @Override
    public String getColumnName(int column) {
        if (colNames != null && column < colNames.length) {
            return colNames[column];
        }
        return "NaN";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        HikingGroup group = groups.get(rowIndex);
        return switch (columnIndex) {
            case 0 ->
                group.getCrn();
            case 1 ->
                group.getName();
            case 2 ->
                group.getPlace();
            default ->
                null;
        };

    }

    public void loadGroups(List<HikingGroup> groups) {
        this.groups = groups;
        fireTableDataChanged();
    }

    public HikingGroup removeGroup(int row) {
        if (groups != null && row >= 0 && row < groups.size()) {
            HikingGroup g = groups.remove(row);
            fireTableRowsDeleted(row, row);
            return g;
        }
        return null;
    }

    public HikingGroup getGroup(int row) {
        if (groups != null && row >= 0 && row < groups.size()) {
            return groups.get(row);
        }
        return null;
    }

    public void removeAllGroups() {
        groups = new LinkedList<>();
        fireTableDataChanged();
    }

}
