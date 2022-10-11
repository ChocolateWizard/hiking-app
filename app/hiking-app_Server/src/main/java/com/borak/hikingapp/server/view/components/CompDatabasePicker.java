/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.view.components;

import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.commonlib.view.components.design.CompOneComboBox;
import com.borak.hikingapp.server.domain.enums.DatabaseType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

/**
 *
 * @author Despot
 */
public class CompDatabasePicker extends CompOneComboBox<DatabaseType, Object> {

    public CompDatabasePicker() {
        super(DatabaseType.values(), null);
    }

    @Override
    public DatabaseType getValue() throws CustomException {
        return (DatabaseType) cbField.getSelectedItem();
    }
  
    @Override
    public void setValue(DatabaseType value) throws CustomException {
        cbField.setSelectedItem(value);
    }
    
    public void addActionListener(ActionListener listener) {
        cbField.addActionListener(listener);       
    }
}
