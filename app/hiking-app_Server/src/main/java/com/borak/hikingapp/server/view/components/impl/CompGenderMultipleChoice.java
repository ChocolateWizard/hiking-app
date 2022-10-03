/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.view.components.impl;

import com.borak.hikingapp.commonlib.domain.enums.Gender;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.commonlib.view.components.design.CompOneComboBox;


/**
 *
 * @author Despot
 */
public class CompGenderMultipleChoice extends CompOneComboBox<Gender, Object>{

    public CompGenderMultipleChoice() {
        super(Gender.values(), null);
    }

    @Override
    public Gender getValue() throws CustomException {
        Object o=cbField.getSelectedItem();
        if(validator!=null)
            validator.validate(o);
        return (Gender)o;
    }

    @Override
    public void setValue(Gender value) throws CustomException {
        cbField.setSelectedItem(value);
    }
    
}
