/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.logic.controllers;

import com.borak.hikingapp.server.view.forms.FrmConfigurationDatabase;
import com.borak.hikingapp.server.view.forms.FrmConfigurationPort;
import com.borak.hikingapp.server.view.forms.FrmMain;
import com.borak.hikingapp.server.view.tables.TableLoggedUsers;

/**
 *
 * @author Despot
 */
public final class ControllerForms {

    private static ControllerForms instance;
    //View forms
    private FrmMain frmMain;
    private FrmConfigurationDatabase frmConfigurationDatabase;
    private FrmConfigurationPort frmConfigurationPort;

    //View tables
    private TableLoggedUsers tblLoggedUsers;

    private ControllerForms() {
    }

    public static ControllerForms getInstance() {
        if (instance == null) {
            instance = new ControllerForms();
        }
        return instance;
    }
//======================FORMS===================================================

    public void openFrmMain() {
        if (frmMain == null) {
            frmMain = new FrmMain();
        }
        frmMain.setVisible(true);
    }

    public void closeFrmMain() {
        if (frmMain != null) {
            frmMain.dispose();
            frmMain = null;
        }
    }

    public FrmMain getFrmMain() {
        return frmMain;
    }

    public void openFrmConfigurationDatabase() {
        frmConfigurationDatabase = new FrmConfigurationDatabase(frmMain, true);
        frmConfigurationDatabase.setVisible(true);
    }

    public void closeFrmConfigurationDatabase() {
        if (frmConfigurationDatabase != null) {
            frmConfigurationDatabase.dispose();
            frmConfigurationDatabase = null;
        }
    }

    public void openFrmConfigurationPort() {
        frmConfigurationPort = new FrmConfigurationPort(frmMain, true);
        frmConfigurationPort.setVisible(true);
    }

    public void closeFrmConfigurationPort() {
        if (frmConfigurationPort != null) {
            frmConfigurationPort.dispose();
            frmConfigurationPort = null;
        }
    }
//========================TABLES================================================================

    public TableLoggedUsers getTableLoggedUsers() {
        if (tblLoggedUsers == null) {
            tblLoggedUsers = new TableLoggedUsers();
        }
        return tblLoggedUsers;
    }

}
