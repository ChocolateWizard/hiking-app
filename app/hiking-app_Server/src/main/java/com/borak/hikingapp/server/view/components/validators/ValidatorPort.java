/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.view.components.validators;

import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.commonlib.view.components.validators.api.IValidator;

/**
 *
 * @author Despot
 */
public class ValidatorPort implements IValidator<String> {

    @Override
    public void validate(String entity) throws CustomException {
        if (entity == null) {
            throw new CustomException(ErrorType.UNEXPECTED_ERROR, "Port configuration unexpeced error!");
        }
        String pom = entity.trim();

        if (!pom.matches("^\\-?(([1-9][0-9]*(\\.[0-9]+)?)|([0-9](\\.[0-9]+)?))$")) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Port must be a number!");
        }
        if (pom.contains(".")) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Port must be a whole number!");
        }
        if (pom.startsWith("-")) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Port must be a positive number!");
        }
        int i = Integer.parseInt(pom);
        if (i <= 3306 || i > 10024) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Port must be a between 3306 and 10024!");
        }
    }

}
