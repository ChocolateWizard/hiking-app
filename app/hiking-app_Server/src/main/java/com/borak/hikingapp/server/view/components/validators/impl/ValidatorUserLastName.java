/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.view.components.validators.impl;

import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.commonlib.view.components.validators.intf.IValidator;

/**
 *
 * @author Despot
 */
public class ValidatorUserLastName implements IValidator<String> {

    @Override
    public void validate(String entity) throws CustomException {
        if (entity == null) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Last name is mandatory!");
        }
        String name = entity.trim();
        if (name.isEmpty()) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Last name is mandatory!");
        }
        int upperBound = 100;
        if (entity.trim().length() > upperBound) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Last name must be less than " + upperBound + " characters!");
        }
    }

}
