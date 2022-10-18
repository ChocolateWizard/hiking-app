/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.client.view.components.validators;

import com.borak.hikingapp.commonlib.domain.classes.Place;
import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.commonlib.view.components.validators.api.IValidator;

/**
 *
 * @author Despot
 */
public class ValidatorPlace implements IValidator<Object> {

    @Override
    public void validate(Object entity) throws CustomException {
        if (entity == null) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Place must be selected!");
        }
        if (!(entity instanceof Place)) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Place must be selected!");
        }
        Place place = (Place) entity;
        if (place.getId() == null) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Place id is mandatory!");
        }
        if (place.getId() <= 0) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Place id must be >0!");
        }
        String name = place.getName();
        if (name == null || name.trim().isEmpty()) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Place name is mandatory!");
        }
        int upperBound = 300;
        if (name.trim().length() > upperBound) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Place name must be less than " + upperBound + " characters!");
        }
    }

}
