/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.client.view.components.validators;

import com.borak.hikingapp.commonlib.domain.classes.HikingActivity;
import com.borak.hikingapp.commonlib.domain.classes.Place;
import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.commonlib.view.components.validators.api.IValidator;

/**
 *
 * @author User
 */
public class ValidatorHikingActivity implements IValidator<HikingActivity> {

    private IValidator<String> validatorOrderNum;
    private IValidator<String> validatorName;
    private IValidator<String> validatorDescription;
    private IValidator<Object> validatorPlace;

    public ValidatorHikingActivity() {
        validatorOrderNum = new ValidatorHikingActivityOrderNumber();
        validatorName = new ValidatorHikingActivityName();
        validatorDescription = new ValidatorHikingActivityDescription();
        validatorPlace = new ValidatorPlace();
    }

    @Override
    public void validate(HikingActivity entity) throws CustomException {
        if (entity == null) {
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Hiking activity must not be empty!");
        }      
        validatorOrderNum.validate(entity.getOrderNum().toString());
        validatorName.validate(entity.getName());
        validatorDescription.validate(entity.getDescription());
        if(entity.getDate()==null){
            throw new CustomException(ErrorType.INVALID_INPUT_ERROR, "Hiking activity must have a date!");
        }
        validatorPlace.validate(entity.getPlace());
    }

}
