/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.view.tables;

import com.borak.hikingapp.commonlib.domain.classes.User;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.borak.hikingapp.server.DUMMYCLASSES.UserViewObserver;

/**
 *
 * @author Despot
 */
public class TableLoggedUsers extends AbstractTableModel implements UserViewObserver {

    private List<User> loggedUsers;
    private final String[] colNames = new String[]{"Username", "First and last name"};
    private final Class[] colTypes = new Class[]{String.class, String.class};

    public TableLoggedUsers() {
        this.loggedUsers = new LinkedList<>();
    }

    @Override
    public int getRowCount() {
        if (loggedUsers == null) {
            return 0;
        }
        return loggedUsers.size();
    }

    @Override
    public int getColumnCount() {
        if (colNames == null) {
            return 0;
        }
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User u = loggedUsers.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return u.getUsername();
            case 1:
                return u.getFirstName().concat(" ").concat(u.getLastName());
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (colTypes == null || columnIndex < 0 || columnIndex >= colTypes.length) {
            return null;
        }
        return colTypes[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        if (colNames == null || column < 0 || column >= colNames.length) {
            return "N/A";
        }
        return colNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public User removeUser(int row) {
        if (loggedUsers != null && row >= 0 && row < loggedUsers.size()) {
            return loggedUsers.remove(row);
        }
        return null;
    }

    @Override
    public void addUser(User user) {
        if (loggedUsers == null) {
            loggedUsers = new LinkedList<>();
        }
        loggedUsers.add(user);
        fireTableDataChanged();
    }

    @Override
    public void removeUser(User user) {
        if (loggedUsers != null) {
            loggedUsers.remove(user);
            fireTableDataChanged();
        }
    }

    @Override
    public void removeAllUsers() {
        loggedUsers = new LinkedList<>();
        fireTableDataChanged();
    }

}
