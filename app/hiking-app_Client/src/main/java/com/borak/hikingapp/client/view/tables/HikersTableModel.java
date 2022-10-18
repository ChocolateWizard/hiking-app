/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.client.view.tables;

import com.borak.hikingapp.commonlib.domain.classes.Hiker;
import com.borak.hikingapp.commonlib.domain.classes.Place;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Despot
 */
public class HikersTableModel extends AbstractTableModel {

    private List<Hiker> hikers;
    private final String[] colNames = {"UCIN", "Name", "Place"};
    private final Class[] colTypes = {String.class, String.class, Place.class};

    public HikersTableModel() {
        this.hikers = new LinkedList<>();
    }

    public HikersTableModel(List<Hiker> hikers) {
        this.hikers = hikers;
    }

    @Override
    public int getRowCount() {
        if (hikers == null) {
            return 0;
        }
        return hikers.size();
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
        Hiker h = hikers.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return h.getUcin();
            case 1:
                return h.getFirstName().concat(" ").concat(h.getLastName());
            case 2:
                return h.getPlace();
            default:
                return null;
        }
    }

    public void loadHikers(List<Hiker> hikers) {
        this.hikers = hikers;
        fireTableDataChanged();
    }

    public Hiker removeHiker(int row) {
        if (hikers != null && row >= 0 && row < hikers.size()) {
            Hiker hiker = hikers.remove(row);
            fireTableRowsDeleted(row, row);
            return hiker;
        }
        return null;
    }

    public Hiker getHiker(int row) {
        if (hikers != null && row >= 0 && row < hikers.size()) {
            return hikers.get(row);
        }
        return null;
    }

    public void removeAllHikers() {
        hikers = new LinkedList<>();
        fireTableDataChanged();
    }
}
