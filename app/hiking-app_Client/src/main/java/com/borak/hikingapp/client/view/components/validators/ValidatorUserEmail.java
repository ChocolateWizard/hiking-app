/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.client.view.components.validators;

import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.commonlib.view.components.validators.api.IValidator;

/**
 *
 * @author Despot
 */
public class ValidatorUserEmail implements IValidator<String> {

    @Override
    public void validate(String entity) throws CustomException {
        if (entity == null) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Email is mandatory!");
        }
        String pom = entity.trim();
        if (pom.isEmpty()) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Email is mandatory!");
        }
        int upperBound = 500;
        if (pom.length() > upperBound) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Email must be less than " + upperBound + " characters!");
        }
        String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        if (!pom.matches(regex)) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Given email is not valid!");
        }
    }

}
