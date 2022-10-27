/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.client.view.components;

import com.borak.hikingapp.commonlib.domain.classes.HikingGroup;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.commonlib.view.components.design.CompOneComboBox;
import com.borak.hikingapp.commonlib.view.components.validators.api.IValidator;
import java.awt.event.ActionListener;

/**
 *
 * @author User
 */
public class CompHikingGroupInput extends CompOneComboBox<HikingGroup, Object> {

    public CompHikingGroupInput(HikingGroup[] arrayOfElements) {
        super(arrayOfElements, null);
    }

    public CompHikingGroupInput(HikingGroup[] arrayOfElements, IValidator validator) {
        super(arrayOfElements, validator);
    }

    @Override
    public HikingGroup getValue() throws CustomException {
        Object g = cbField.getSelectedItem();
        if (validator != null) {
            validator.validate(g);
        }
        return (HikingGroup) g;
    }

    @Override
    public void setValue(HikingGroup value) throws CustomException {
        cbField.setSelectedItem(value);
    }
    
    public void addActionListener(ActionListener listener) {
        cbField.addActionListener(listener);       
    }

}
