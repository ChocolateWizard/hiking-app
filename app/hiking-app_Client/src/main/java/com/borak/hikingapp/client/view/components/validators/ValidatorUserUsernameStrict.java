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
public class ValidatorUserUsernameStrict implements IValidator<String> {

//Username should be mandatory, be less than 30 characters, and be one continuous string without blanks inbetween
    @Override
    public void validate(String entity) throws CustomException {
        if (entity == null) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Username is mandatory!");
        }
        String username = entity.trim();
        if (username.isEmpty()) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Username is mandatory!");
        }
        int upperBound = 300;
        if (username.length() > upperBound) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Username must be less than " + upperBound + " characters!");
        }
        if (username.contains(" ")) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Username must be made of one word!");
        }
    }

}
