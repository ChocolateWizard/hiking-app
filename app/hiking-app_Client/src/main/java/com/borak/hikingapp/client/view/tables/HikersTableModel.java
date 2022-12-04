/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.client.view.tables;

import com.borak.hikingapp.commonlib.domain.classes.Hiker;
import com.borak.hikingapp.commonlib.domain.classes.Place;
import com.borak.hikingapp.commonlib.domain.enums.Gender;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Despot
 */
public class HikersTableModel extends AbstractTableModel {

    private List<Hiker> hikers;
    private final String[] colNamesSimple = {"UCIN", "Name", "Place"};
    private final String[] colNamesExtended = {"UCIN", "Name", "Gender", "Date of birth", "Years of experiance", "Place"};
    private final Class[] colTypesSimple = {String.class, String.class, Place.class};
    private final Class[] colTypesExtended = {String.class, String.class, Gender.class, String.class, String.class, Place.class};

    private boolean additionalInfo;

    public HikersTableModel() {
        this.hikers = new LinkedList<>();
        this.additionalInfo = false;
    }

    public HikersTableModel(boolean additionalInfo) {
        this.hikers = new LinkedList<>();
        this.additionalInfo = additionalInfo;
    }

    public HikersTableModel(List<Hiker> hikers) {
        if (hikers == null) {
            this.hikers = new LinkedList<>();
        } else {
            this.hikers = hikers;
        }
        this.additionalInfo = false;
    }

    public HikersTableModel(List<Hiker> hikers, boolean additionalInfo) {
        if (hikers == null) {
            this.hikers = new LinkedList<>();
        } else {
            this.hikers = hikers;
        }
        this.additionalInfo = additionalInfo;
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
        if (!additionalInfo) {
            if (colNamesSimple == null) {
                return 0;
            }
            return colNamesSimple.length;
        } else {
            if (colNamesExtended == null) {
                return 0;
            }
            return colNamesExtended.length;
        }
    }

    @Override
    public String getColumnName(int column) {
        if (!additionalInfo) {
            if (colNamesSimple != null && column < colNamesSimple.length) {
                return colNamesSimple[column];
            }
        } else {
            if (colNamesExtended != null && column < colNamesExtended.length) {
                return colNamesExtended[column];
            }
        }
        return "NaN";
    }
    

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (!additionalInfo) {
            if (colTypesSimple != null && columnIndex < colTypesSimple.length) {
                return colTypesSimple[columnIndex];
            }
        } else {
            if (colTypesExtended != null && columnIndex < colTypesExtended.length) {
                return colTypesExtended[columnIndex];
            }
        }
        return Object.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Hiker h = hikers.get(rowIndex);
        if (!additionalInfo) {
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
        } else {
            switch (columnIndex) {
                case 0:
                    return h.getUcin();
                case 1:
                    return h.getFirstName().concat(" ").concat(h.getLastName());
                case 2:
                    return h.getGender().toString();
                case 3:
                    SimpleDateFormat smpl = new SimpleDateFormat("dd/MM/yyyy");
                    return smpl.format(h.getDateOfBirth().getTime());
                case 4:
                    return h.getYearsOfExperience().toString();
                case 5:
                    return h.getPlace();
                default:
                    return null;
            }
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
