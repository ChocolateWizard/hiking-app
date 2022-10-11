/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.client.view.components;

import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.commonlib.view.components.design.CompTwoRadioButton;

/**
 *
 * @author Despot
 */
public class CompYesNoRB extends CompTwoRadioButton<Boolean, Object> {

    public CompYesNoRB() {
        super("Yes", "No", null);
    }

    public CompYesNoRB(String firstOption, String secondOption) {
        super(firstOption, secondOption, null);
    }

    @Override
    public Boolean getValue() throws CustomException {
        return rb1.isSelected();
    }

    @Override
    public void setValue(Boolean value) throws CustomException {
        if (value == true) {
            rb1.setSelected(true);
        } else {
            rb2.setSelected(true);
        }
    }

}
