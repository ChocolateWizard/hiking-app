/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.client.view.components.validators.factory;

import com.borak.hikingapp.commonlib.view.components.validators.intf.IValidator;
import com.borak.hikingapp.client.view.components.validators.ValidatorGroupCrn;
import com.borak.hikingapp.client.view.components.validators.ValidatorGroupDescription;
import com.borak.hikingapp.client.view.components.validators.ValidatorGroupFilter;
import com.borak.hikingapp.client.view.components.validators.ValidatorGroupName;
import com.borak.hikingapp.client.view.components.validators.ValidatorGroupResources;
import com.borak.hikingapp.client.view.components.validators.ValidatorHikerFilter;
import com.borak.hikingapp.client.view.components.validators.ValidatorHikerFirstName;
import com.borak.hikingapp.client.view.components.validators.ValidatorHikerLastName;
import com.borak.hikingapp.client.view.components.validators.ValidatorHikerUcin;
import com.borak.hikingapp.client.view.components.validators.ValidatorHikerYearsOfExperience;
import com.borak.hikingapp.client.view.components.validators.ValidatorHikingActivityDescription;
import com.borak.hikingapp.client.view.components.validators.ValidatorHikingActivityName;
import com.borak.hikingapp.client.view.components.validators.ValidatorHikingActivityOrderNumber;
import com.borak.hikingapp.client.view.components.validators.ValidatorPlace;
import com.borak.hikingapp.client.view.components.validators.ValidatorUserEmail;
import com.borak.hikingapp.client.view.components.validators.ValidatorUserFirstName;
import com.borak.hikingapp.client.view.components.validators.ValidatorUserLastName;
import com.borak.hikingapp.client.view.components.validators.ValidatorUserPasswordStrict;
import com.borak.hikingapp.client.view.components.validators.ValidatorUserUsernameStrict;

/**
 *
 * @author Despot
 */
public class ValidatorFactory {

//=========Client==============================================================
    /*
    private IValidator<String> userFirstNameValidator;
    private IValidator<String> userLastNameValidator;
    private IValidator<String> userUsernameValidator;
    private IValidator<String> userPasswordValidator;
    private IValidator<String> userEmailValidator;

    private IValidator<String> hikerUcinValidator;
    private IValidator<String> hikerFirstNameValidator;
    private IValidator<String> hikerLastNameValidator;
    private IValidator<String> hikerYearsOfExperienceValidator;
    private IValidator<String> hikerFilterValidator;

    private IValidator<String> groupCrnValidator;
    private IValidator<String> groupNameValidator;
    private IValidator<String> groupDescriptionValidator;
    private IValidator<String> groupResourcesValidator;
    private IValidator<String> groupFilterValidator;

    private IValidator<String> hikingActivityOrderNumberValidator;
    private IValidator<String> hikingActivityNameValidator;
    private IValidator<String> hikingActivityDescriptionValidator;

    private IValidator<Object> placeValidator;
    
     */
//========Server===============================================================
    private IValidator<String> portValidator;
//=================================================================================
    private static ValidatorFactory instance;

    private ValidatorFactory() {
        /*
        userFirstNameValidator = new ValidatorUserFirstName();
        userLastNameValidator = new ValidatorUserLastName();
        userUsernameValidator = new ValidatorUserUsernameStrict();
        userPasswordValidator = new ValidatorUserPasswordStrict();
        userEmailValidator = new ValidatorUserEmail();

        hikerUcinValidator = new ValidatorHikerUcin();
        hikerFirstNameValidator = new ValidatorHikerFirstName();
        hikerLastNameValidator = new ValidatorHikerLastName();
        hikerYearsOfExperienceValidator = new ValidatorHikerYearsOfExperience();
        hikerFilterValidator = new ValidatorHikerFilter();

        groupCrnValidator = new ValidatorGroupCrn();
        groupNameValidator = new ValidatorGroupName();
        groupDescriptionValidator = new ValidatorGroupDescription();
        groupResourcesValidator = new ValidatorGroupResources();
        groupFilterValidator = new ValidatorGroupFilter();

        hikingActivityOrderNumberValidator = new ValidatorHikingActivityOrderNumber();
        hikingActivityNameValidator = new ValidatorHikingActivityName();
        hikingActivityDescriptionValidator = new ValidatorHikingActivityDescription();

        placeValidator = new ValidatorPlace();

        portValidator = new ValidatorPort();
         */
    }

    public static ValidatorFactory getInstance() {
        if (instance == null) {
            instance = new ValidatorFactory();
        }
        return instance;
    }
//======================================================================================

    public IValidator<String> getUserFirstNameValidator() {
        return new ValidatorUserFirstName();
    }

    public IValidator<String> getUserLastNameValidator() {
        return new ValidatorUserLastName();
    }

    public IValidator<String> getUserUsernameValidator() {
        return new ValidatorUserUsernameStrict();
    }

    public IValidator<String> getUserPasswordValidator() {
        return new ValidatorUserPasswordStrict();
    }

    public IValidator<String> getUserEmailValidator() {
        return new ValidatorUserEmail();
    }

    public IValidator<String> getHikerUcinValidator() {
        return new ValidatorHikerUcin();
    }

    public IValidator<String> getHikerFirstNameValidator() {
        return new ValidatorHikerFirstName();
    }

    public IValidator<String> getHikerLastNameValidator() {
        return new ValidatorHikerLastName();
    }

    public IValidator<String> getHikerYearsOfExperienceValidator() {
        return new ValidatorHikerYearsOfExperience();
    }

    public IValidator<String> getHikerFilterValidator() {
        return new ValidatorHikerFilter();
    }

    public IValidator<String> getGroupCrnValidator() {
        return new ValidatorGroupCrn();
    }

    public IValidator<String> getGroupNameValidator() {
        return new ValidatorGroupName();
    }

    public IValidator<String> getGroupDescriptionValidator() {
        return new ValidatorGroupDescription();
    }

    public IValidator<String> getGroupResourcesValidator() {
        return new ValidatorGroupResources();
    }

    public IValidator<String> getGroupFilterValidator() {
        return new ValidatorGroupFilter();
    }

    public IValidator<String> getHikingActivityOrderNumberValidator() {
        return new ValidatorHikingActivityOrderNumber();
    }

    public IValidator<String> getHikingActivityNameValidator() {
        return new ValidatorHikingActivityName();
    }

    public IValidator<String> getHikingActivityDescriptionValidator() {
        return new ValidatorHikingActivityDescription();
    }

    public IValidator<Object> getPlaceValidator() {
        return new ValidatorPlace();
    }

}
