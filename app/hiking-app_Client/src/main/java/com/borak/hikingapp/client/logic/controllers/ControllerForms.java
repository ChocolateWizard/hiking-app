/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.client.logic.controllers;

import com.borak.hikingapp.client.view.forms.FrmGroupChangeInfo;
import com.borak.hikingapp.client.view.forms.FrmGroupChangeInfo_Dialog;
import com.borak.hikingapp.client.view.forms.FrmGroupCreate;
import com.borak.hikingapp.client.view.forms.FrmGroupDelete;
import com.borak.hikingapp.client.view.forms.FrmHikerChangeInfo;
import com.borak.hikingapp.client.view.forms.FrmHikerChangeInfo_Dialog;
import com.borak.hikingapp.client.view.forms.FrmHikerCreate;
import com.borak.hikingapp.client.view.forms.FrmHikerDelete;
import com.borak.hikingapp.client.view.forms.FrmLogin;
import com.borak.hikingapp.client.view.forms.FrmMain;
import com.borak.hikingapp.client.view.forms.FrmProfileConfiguration;
import com.borak.hikingapp.client.view.forms.FrmRegister;
import com.borak.hikingapp.commonlib.domain.classes.Hiker;
import com.borak.hikingapp.commonlib.domain.classes.HikingGroup;

/**
 *
 * @author Despot
 */
public class ControllerForms {

    private FrmLogin frmLogin;
    private FrmRegister frmRegister;
    private FrmMain frmMain;
    private FrmGroupCreate frmGroupCreate;
    private FrmGroupChangeInfo frmGroupChangeInfo;
    private FrmGroupDelete frmGroupDelete;
    private FrmHikerCreate frmHikerCreate;
    private FrmHikerDelete frmHikerDelete;
    private FrmHikerChangeInfo frmHikerChangeInfo;
    private FrmHikerChangeInfo_Dialog frmHikerChangeInfo_Dialog;
    private FrmGroupChangeInfo_Dialog frmGroupChangeInfo_Dialog;
    private FrmProfileConfiguration frmProfileConfiguration;

    private static ControllerForms instance;

    private ControllerForms() {
    }

    public static ControllerForms getInstance() {
        if (instance == null) {
            instance = new ControllerForms();
        }
        return instance;
    }

    public void openFrmLogin() {
        frmLogin = new FrmLogin();
        frmLogin.setVisible(true);
    }

    public void closeFrmLogin() {
        if (frmLogin != null) {
            frmLogin.dispose();
            frmLogin = null;
        }
    }

    public void openFrmRegister() {
        frmRegister = new FrmRegister(frmLogin, true);
        frmRegister.setVisible(true);
    }

    public void closeFrmRegister() {
        if (frmRegister != null) {
            frmRegister.dispose();
            frmRegister = null;
        }
    }

    public void openFrmMain() {
        frmMain = new FrmMain();
        frmMain.setVisible(true);
    }

    public void closeFrmMain() {
        if (frmMain != null) {
            frmMain.dispose();
            frmMain = null;
        }
    }

    public void openFrmHikerCreate() {
        frmHikerCreate = new FrmHikerCreate(frmMain, true);
        frmHikerCreate.setVisible(true);
    }

    public void closeFrmHikerCreate() {
        if (frmHikerCreate != null) {
            frmHikerCreate.dispose();
            frmHikerCreate = null;
        }
    }

    public void openFrmHikerDelete() {
        frmHikerDelete = new FrmHikerDelete(frmMain, true);
        frmHikerDelete.setVisible(true);
    }

    public void closeFrmHikerDelete() {
        if (frmHikerDelete != null) {
            frmHikerDelete.dispose();
            frmHikerDelete = null;
        }
    }

    public void openFrmHikerChangeInfo() {
        frmHikerChangeInfo = new FrmHikerChangeInfo(frmMain, true);
        frmHikerChangeInfo.setVisible(true);
    }

    public void closeFrmHikerChangeInfo() {
        if (frmHikerChangeInfo != null) {
            frmHikerChangeInfo.dispose();
            frmHikerChangeInfo = null;
        }
    }

    public void openFrmHikerChangeInfo_Dialog(Hiker h) {
        frmHikerChangeInfo_Dialog = new FrmHikerChangeInfo_Dialog(frmHikerChangeInfo, true, h);
        frmHikerChangeInfo_Dialog.setVisible(true);
    }

    public void closeFrmHikerChangeInfo_Dialog() {
        if (frmHikerChangeInfo_Dialog != null) {
            frmHikerChangeInfo_Dialog.dispose();
            frmHikerChangeInfo_Dialog = null;
        }
    }

    public void openFrmGroupCreate() {
        frmGroupCreate = new FrmGroupCreate(frmMain, true);
        frmGroupCreate.setVisible(true);
    }

    public void closeFrmGroupCreate() {
        if (frmGroupCreate != null) {
            frmGroupCreate.dispose();
            frmGroupCreate = null;
        }
    }

    public void openFrmGroupChangeInfo() {
        frmGroupChangeInfo = new FrmGroupChangeInfo(frmMain, true);
        frmGroupChangeInfo.setVisible(true);
    }

    public void closeFrmGroupChangeInfo() {
        if (frmGroupChangeInfo != null) {
            frmGroupChangeInfo.dispose();
            frmGroupChangeInfo = null;
        }
    }

    public void openFrmGroupDelete() {
        frmGroupDelete = new FrmGroupDelete(frmMain, true);
        frmGroupDelete.setVisible(true);
    }

    public void closeFrmGroupDelete() {
        if (frmGroupDelete != null) {
            frmGroupDelete.dispose();
            frmGroupDelete = null;
        }
    }

    public void openFrmHikingGroupChangeInfo_Dialog(HikingGroup g) {
        frmGroupChangeInfo_Dialog = new FrmGroupChangeInfo_Dialog(frmGroupChangeInfo, true, g);
        frmGroupChangeInfo_Dialog.setVisible(true);
    }

    public void closeFrmHikingGroupChangeInfo_Dialog() {
        if (frmGroupChangeInfo_Dialog != null) {
            frmGroupChangeInfo_Dialog.dispose();
            frmGroupChangeInfo_Dialog = null;
        }
    }

    public void openFrmProfileConfiguration() {
        frmProfileConfiguration = new FrmProfileConfiguration(frmMain, true);
        frmProfileConfiguration.setVisible(true);
    }

    public void closeFrmProfileConfiguration() {
        if (frmProfileConfiguration != null) {
            frmProfileConfiguration.dispose();
            frmProfileConfiguration = null;
        }
    }

}
