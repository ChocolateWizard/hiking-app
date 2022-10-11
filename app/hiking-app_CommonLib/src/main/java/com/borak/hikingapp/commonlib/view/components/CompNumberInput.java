/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.commonlib.view.components;

import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.commonlib.view.components.design.CompOneTextBox;
import com.borak.hikingapp.commonlib.view.components.validators.intf.IValidator;
import javax.swing.SwingConstants;

/**
 *
 * @author Despot
 */
public class CompNumberInput extends CompOneTextBox<Integer, String> {

    public CompNumberInput(IValidator<String> validator) {
        super(validator);
        txtField.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    @Override
    public Integer getValue() throws CustomException {
        String text = txtField.getText();
        if (validator != null) {
            validator.validate(text);
        }
        return Integer.parseInt(text);
    }

    @Override
    public void setValue(Integer value) throws CustomException {
        if (value != null) {
            txtField.setText(String.valueOf(value));
        } else {
            txtField.setText("");
        }
    }

}
