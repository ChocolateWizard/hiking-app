/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.view.components.impl;

import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.commonlib.view.components.design.CompOneTextArea;
import com.borak.hikingapp.commonlib.view.components.validators.intf.IValidator;


/**
 *
 * @author Despot
 */
public class CompStringInputLarge extends CompOneTextArea<String, String>{

    public CompStringInputLarge(IValidator<String> validator) {
        super(validator);
    }

    @Override
    public String getValue() throws CustomException {
        String text=txtArea.getText();
        if(validator!=null)
            validator.validate(text);
        return text;
    }

    @Override
    public void setValue(String value) throws CustomException {
        txtArea.setText(value);
    }
    
}
