/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.client.view.components;

import com.borak.hikingapp.commonlib.domain.classes.Hiker;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.commonlib.view.components.design.CompOneComboBox;
import com.borak.hikingapp.commonlib.view.components.validators.api.IValidator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author User
 */
public class CompHikerInput extends CompOneComboBox<Hiker, Object> {

    public CompHikerInput(Hiker[] arrayOfElements) {
        super(arrayOfElements, null);
    }

    public CompHikerInput(Hiker[] arrayOfElements, IValidator validator) {
        super(arrayOfElements, validator);
    }

    @Override
    public Hiker getValue() throws CustomException {
        Object g = cbField.getSelectedItem();
        if (validator != null) {
            validator.validate(g);
        }
        return (Hiker) g;
    }

    @Override
    public void setValue(Hiker value) throws CustomException {
        List<Hiker> hikers=new LinkedList<>();
        for(int i=0;i<cbField.getItemCount();i++){
            hikers.add((Hiker)cbField.getItemAt(i));
        }
        if(hikers.contains(value)){
            cbField.setSelectedItem(value);
        }else{
            cbField.addItem(value);
            cbField.setSelectedItem(value);
        }      
    }

    @Override
    public void loadValues(Hiker[] values) throws CustomException {
        cbField.removeAllItems();
        if (values != null) {
            for(int i=0;i<values.length;i++){
                cbField.addItem(values[i]);
            }
        }
    }
    
    
    

}
