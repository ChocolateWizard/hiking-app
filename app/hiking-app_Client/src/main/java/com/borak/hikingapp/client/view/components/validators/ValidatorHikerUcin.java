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
public class ValidatorHikerUcin implements IValidator<String> {

    @Override
    public void validate(String entity) throws CustomException {
        if (entity == null) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Ucin is mandatory!");
        }
        String pom = entity.trim();
        if (pom.isEmpty()) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Ucin is mandatory!");
        }
        int upperBound = 13;
        if (pom.length() == upperBound) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Ucin must be " + upperBound + " characters!");
        }
        if (pom.contains(" ")) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Ucin must be made of one word!");
        }
    }

}
