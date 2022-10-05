/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.view.components.validators.factory;

import com.borak.hikingapp.commonlib.view.components.validators.intf.IValidator;
import com.borak.hikingapp.server.view.components.validators.impl.ValidatorGroupCrn;
import com.borak.hikingapp.server.view.components.validators.impl.ValidatorGroupDescription;
import com.borak.hikingapp.server.view.components.validators.impl.ValidatorGroupFilter;
import com.borak.hikingapp.server.view.components.validators.impl.ValidatorGroupName;
import com.borak.hikingapp.server.view.components.validators.impl.ValidatorGroupResources;
import com.borak.hikingapp.server.view.components.validators.impl.ValidatorHikerFilter;
import com.borak.hikingapp.server.view.components.validators.impl.ValidatorHikerFirstName;
import com.borak.hikingapp.server.view.components.validators.impl.ValidatorHikerLastName;
import com.borak.hikingapp.server.view.components.validators.impl.ValidatorHikerUcin;
import com.borak.hikingapp.server.view.components.validators.impl.ValidatorHikerYearsOfExperience;
import com.borak.hikingapp.server.view.components.validators.impl.ValidatorHikingActivityDescription;
import com.borak.hikingapp.server.view.components.validators.impl.ValidatorHikingActivityName;
import com.borak.hikingapp.server.view.components.validators.impl.ValidatorHikingActivityOrderNumber;
import com.borak.hikingapp.server.view.components.validators.impl.ValidatorPlace;
import com.borak.hikingapp.server.view.components.validators.impl.ValidatorPort;
import com.borak.hikingapp.server.view.components.validators.impl.ValidatorUserEmail;
import com.borak.hikingapp.server.view.components.validators.impl.ValidatorUserFirstName;
import com.borak.hikingapp.server.view.components.validators.impl.ValidatorUserLastName;
import com.borak.hikingapp.server.view.components.validators.impl.ValidatorUserPasswordStrict;
import com.borak.hikingapp.server.view.components.validators.impl.ValidatorUserUsernameStrict;

/**
 *
 * @author Despot
 */
public class ValidatorFactory {

//=========Client==============================================================
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

//========Server===============================================================
    private IValidator<String> portValidator;
//=================================================================================
    private static ValidatorFactory instance;

    private ValidatorFactory() {
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

    }

    public static ValidatorFactory getInstance() {
        if (instance == null) {
            instance = new ValidatorFactory();
        }
        return instance;
    }
//======================================================================================

    public IValidator<String> getUserFirstNameValidator() {
        return userFirstNameValidator;
    }

    public IValidator<String> getUserLastNameValidator() {
        return userLastNameValidator;
    }

    public IValidator<String> getUserUsernameValidator() {
        return userUsernameValidator;
    }

    public IValidator<String> getUserPasswordValidator() {
        return userPasswordValidator;
    }

    public IValidator<String> getUserEmailValidator() {
        return userEmailValidator;
    }

    public IValidator<String> getHikerUcinValidator() {
        return hikerUcinValidator;
    }

    public IValidator<String> getHikerFirstNameValidator() {
        return hikerFirstNameValidator;
    }

    public IValidator<String> getHikerLastNameValidator() {
        return hikerLastNameValidator;
    }

    public IValidator<String> getHikerYearsOfExperienceValidator() {
        return hikerYearsOfExperienceValidator;
    }

    public IValidator<String> getHikerFilterValidator() {
        return hikerFilterValidator;
    }

    public IValidator<String> getGroupCrnValidator() {
        return groupCrnValidator;
    }

    public IValidator<String> getGroupNameValidator() {
        return groupNameValidator;
    }

    public IValidator<String> getGroupDescriptionValidator() {
        return groupDescriptionValidator;
    }

    public IValidator<String> getGroupResourcesValidator() {
        return groupResourcesValidator;
    }

    public IValidator<String> getGroupFilterValidator() {
        return groupFilterValidator;
    }

    public IValidator<String> getHikingActivityOrderNumberValidator() {
        return hikingActivityOrderNumberValidator;
    }

    public IValidator<String> getHikingActivityNameValidator() {
        return hikingActivityNameValidator;
    }

    public IValidator<String> getHikingActivityDescriptionValidator() {
        return hikingActivityDescriptionValidator;
    }

    public IValidator<Object> getPlaceValidator() {
        return placeValidator;
    }

    public IValidator<String> getPortValidator() {
        return portValidator;
    }
}
