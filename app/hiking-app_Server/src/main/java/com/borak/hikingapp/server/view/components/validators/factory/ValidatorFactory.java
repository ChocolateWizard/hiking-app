/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.view.components.validators.factory;

import com.borak.hikingapp.commonlib.view.components.validators.api.IValidator;
import com.borak.hikingapp.server.view.components.validators.ValidatorPort;

/**
 *
 * @author Despot
 */
public class ValidatorFactory {

    
//    private IValidator<String> portValidator;
    
//=================================================================================
    private static ValidatorFactory instance;

    private ValidatorFactory() {
    }

    public static ValidatorFactory getInstance() {
        if (instance == null) {
            instance = new ValidatorFactory();
        }
        return instance;
    }
//======================================================================================

    public IValidator<String> getPortValidator() {
        return new ValidatorPort();
    }
}
