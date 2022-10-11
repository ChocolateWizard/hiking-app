/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.client.view.components.validators;

import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.commonlib.view.components.validators.intf.IValidator;

/**
 *
 * @author Despot
 */
public class ValidatorGroupDescription implements IValidator<String> {

    @Override
    public void validate(String entity) throws CustomException {
        if (entity == null) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Description is mandatory!");
        }
        String description = entity.trim();
        if (description.isEmpty()) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Description is mandatory!");
        }
        int upperBound = 500;
        if (entity.trim().length() > upperBound) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Description must be less than " + upperBound + " characters!");
        }
    }

}
