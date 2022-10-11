/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.client.logic.controllers;

import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import com.borak.hikingapp.client.domain.constants.PropertyConstants;

/**
 *
 * @author Despot
 */
public final class Util {

    private static Util instance;

    private Properties imagesLocations;    
    private File imagesLocationsFile;
    

    private Util() throws CustomException {
        imagesLocations = new Properties();
       
        try {
            imagesLocationsFile = new File(PropertyConstants.IMAGES_LOCATIONS_FILE_PATH);
            FileInputStream in = new FileInputStream(imagesLocationsFile);
            imagesLocations.load(in);
            in.close();
        } catch (FileNotFoundException ex) {
            throw new CustomException(ErrorType.FILE_NOT_FOUND_ERROR, "Unable to find " + PropertyConstants.IMAGES_LOCATIONS_FILE_NAME + " file at: " + PropertyConstants.IMAGES_LOCATIONS_FILE_PATH, ex);
        } catch (IOException ex) {
            throw new CustomException(ErrorType.IO_ERROR, "Unable to open " + PropertyConstants.IMAGES_LOCATIONS_FILE_NAME + " file at: " + PropertyConstants.IMAGES_LOCATIONS_FILE_PATH, ex);
        }
       
    }

    public static Util getInstance() throws CustomException {
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }
   
    //================FORMS FAVICONS================================================  
    public String getFrmLoginFavicon() {
        return imagesLocations.getProperty(PropertyConstants.IMAGES_LOCATIONS_FILE_FRMLOGIN_FAVICON);
    }

    public String getFrmRegisterFavicon() {
        return imagesLocations.getProperty(PropertyConstants.IMAGES_LOCATIONS_FILE_FRMREGISTER_FAVICON);
    }

    public String getFrmMainFavicon() {
        return imagesLocations.getProperty(PropertyConstants.IMAGES_LOCATIONS_FILE_FRMMAIN_FAVICON);
    }

    public String getFrmHikerCreateFavicon() {
        return imagesLocations.getProperty(PropertyConstants.IMAGES_LOCATIONS_FILE_FRMHIKERCREATE_FAVICON);
    }

    public String getFrmHikerDeleteFavicon() {
        return imagesLocations.getProperty(PropertyConstants.IMAGES_LOCATIONS_FILE_FRMHIKERDELETE_FAVICON);
    }

    public String getFrmHikerChangeInfoFavicon() {
        return imagesLocations.getProperty(PropertyConstants.IMAGES_LOCATIONS_FILE_FRMHIKERCHANGEINFO_FAVICON);
    }

    public String getFrmHikerChangeInfo_DialogFavicon() {
        return imagesLocations.getProperty(PropertyConstants.IMAGES_LOCATIONS_FILE_FRMHIKERCHANGEINFODIALOG_FAVICON);
    }

    public String getFrmGroupCreateFavicon() {
        return imagesLocations.getProperty(PropertyConstants.IMAGES_LOCATIONS_FILE_FRMGROUPCREATE_FAVICON);
    }

    public String getFrmGroupDeleteFavicon() {
        return imagesLocations.getProperty(PropertyConstants.IMAGES_LOCATIONS_FILE_FRMGROUPDELETE_FAVICON);
    }

    public String getFrmGroupChangeInfoFavicon() {
        return imagesLocations.getProperty(PropertyConstants.IMAGES_LOCATIONS_FILE_FRMGROUPCHANGEINFO_FAVICON);
    }

    public String getFrmGroupChangeInfo_DialogFavicon() {
        return imagesLocations.getProperty(PropertyConstants.IMAGES_LOCATIONS_FILE_FRMGROUPCHANGEINFODIALOG_FAVICON);
    }

    public String getFrmConfigurationPortFavicon() {
        return imagesLocations.getProperty(PropertyConstants.IMAGES_LOCATIONS_FILE_FRMCONFIGURATIONPORTFAVICON);
    }

    public String getFrmConfigurationDatabaseFavicon() {
        return imagesLocations.getProperty(PropertyConstants.IMAGES_LOCATIONS_FILE_FRMCONFIGURATIONDATABASEFAVICON);
    }

//=====================JOPTIONS PANES FAVICONS=====================================  
    public String getSuccessFavicon() {
        return imagesLocations.getProperty(PropertyConstants.IMAGES_LOCATIONS_FILE_SUCCESS_FAVICON);
    }

    public String getQuestionFavicon() {
        return imagesLocations.getProperty(PropertyConstants.IMAGES_LOCATIONS_FILE_QUESTION_FAVICON);
    }

    public String getErrorFavicon() {
        return imagesLocations.getProperty(PropertyConstants.IMAGES_LOCATIONS_FILE_ERROR_FAVICON);
    }

}
