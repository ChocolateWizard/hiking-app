/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.client.start;

import com.borak.hikingapp.client.logic.controllers.ControllerForms;
import com.borak.hikingapp.client.logic.controllers.ControllerSO;
import com.borak.hikingapp.client.logic.controllers.ControllerClient;
import com.borak.hikingapp.client.view.forms.FrmPlanShow_Dialog;
import com.borak.hikingapp.client.view.helpers.Window;
import com.borak.hikingapp.commonlib.domain.classes.HikingGroup;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Despot
 */
public class Start {

    public static void main(String[] args) {
        try {
            ControllerClient.getInstance().connect();
            ControllerForms.getInstance().openFrmLogin();
        } catch (CustomException ex) {
            Window.unSuccessfulOperation(null, "Connection error", ex.getMessage());
        }

    }
}
