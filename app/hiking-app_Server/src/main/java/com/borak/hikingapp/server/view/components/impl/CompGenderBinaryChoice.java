/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.view.components.impl;

import com.borak.hikingapp.commonlib.domain.enums.Gender;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.commonlib.view.components.design.CompTwoRadioButton;

/**
 *
 * @author Despot
 */
public class CompGenderBinaryChoice extends CompTwoRadioButton<Gender, Object> {

    public CompGenderBinaryChoice() {
        super("male", "female", null);
    }

    public CompGenderBinaryChoice(String maleOption, String femaleOption) {
        super(maleOption, femaleOption, null);
    }

    @Override
    public Gender getValue() throws CustomException {
        if (rb1.isSelected()) {
            return Gender.MALE;
        }
        return Gender.FEMALE;
    }

    @Override
    public void setValue(Gender value) throws CustomException {
        if (value == Gender.MALE) {
            rb1.setSelected(true);
        } else {
            rb2.setSelected(true);
        }
    }

}
