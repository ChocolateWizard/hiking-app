/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.client.view.components;

import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.commonlib.view.components.design.CompOnePswField;
import com.borak.hikingapp.commonlib.view.components.validators.api.IValidator;

/**
 *
 * @author Despot
 */
public class CompPswInput extends CompOnePswField<String,String>{

      
    public CompPswInput(IValidator<String> inputValidator) {
        super(inputValidator);
    }      

    @Override
    public String getValue() throws CustomException {
        String text=String.valueOf(pswField.getPassword());
        if(validator!=null){
           validator.validate(text);
        }
        return text;
    }

    @Override
    public void setValue(String value) throws CustomException {
        pswField.setText(value);
    }
    
}
