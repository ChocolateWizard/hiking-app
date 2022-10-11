/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.client.view.components;

import com.borak.hikingapp.commonlib.domain.classes.Place;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.commonlib.view.components.design.CompOneComboBox;
import com.borak.hikingapp.commonlib.view.components.validators.intf.IValidator;

/**
 *
 * @author Despot
 */
public class CompPlaceInput extends CompOneComboBox<Place, Object> {

    public CompPlaceInput(Place[] arrayOfElements) {
        super(arrayOfElements, null);
    }

    public CompPlaceInput(Place[] arrayOfElements, IValidator<Object> validator) {
        super(arrayOfElements, validator);
    }

    @Override
    public Place getValue() throws CustomException {
        Object p = cbField.getSelectedItem();
        if (validator != null) {
            validator.validate(p);
        }
        return (Place) p;
    }

    @Override
    public void setValue(Place value) throws CustomException {
        cbField.setSelectedItem(value);
    }

}
