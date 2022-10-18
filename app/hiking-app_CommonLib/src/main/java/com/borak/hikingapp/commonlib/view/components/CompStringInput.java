/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.commonlib.view.components;

import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.commonlib.view.components.design.CompOneTextBox;
import com.borak.hikingapp.commonlib.view.components.validators.api.IValidator;

/**
 *
 * @author Despot
 */
public class CompStringInput extends CompOneTextBox<String, String> {

    public CompStringInput() {
        super(null);
    }

    public CompStringInput(IValidator<String> inputValidator) {
        super(inputValidator);
    }

    @Override
    public String getValue() throws CustomException {
        String text = txtField.getText();
        if (validator != null) {
            validator.validate(text);
        }
        return text;
    }

    @Override
    public void setValue(String value) throws CustomException {
        txtField.setText(value);
    }

}
