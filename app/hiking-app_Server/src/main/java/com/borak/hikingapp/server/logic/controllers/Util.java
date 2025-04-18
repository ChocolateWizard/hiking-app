/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.logic.controllers;

import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.server.domain.constants.PropertyConstants;
import com.borak.hikingapp.server.domain.enums.DatabaseType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author Despot
 */
public final class Util {

    private static Util instance;

    private Properties imagesLocations;
    private Properties databaseParams;
    private Properties serverParams;

    private File imagesLocationsFile;
    private File databaseParamsFile;
    private File serverParamsFile;

    private Util() throws CustomException {
        imagesLocations = new Properties();
        databaseParams = new Properties();
        serverParams = new Properties();

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
        try {
            databaseParamsFile = new File(PropertyConstants.DATABASE_PARAM_FILE_PATH);
            FileInputStream in = new FileInputStream(databaseParamsFile);
            databaseParams.load(in);
            in.close();
        } catch (FileNotFoundException ex) {
            throw new CustomException(ErrorType.FILE_NOT_FOUND_ERROR, "Unable to find " + PropertyConstants.DATABASE_PARAM_FILE_NAME + " file at: " + PropertyConstants.DATABASE_PARAM_FILE_PATH, ex);
        } catch (IOException ex) {
            throw new CustomException(ErrorType.IO_ERROR, "Unable to open " + PropertyConstants.DATABASE_PARAM_FILE_NAME + " file at: " + PropertyConstants.DATABASE_PARAM_FILE_PATH, ex);
        }
        try {
            serverParamsFile = new File(PropertyConstants.SERVER_PARAM_FILE_PATH);
            FileInputStream in = new FileInputStream(serverParamsFile);
            serverParams.load(in);
            in.close();
        } catch (FileNotFoundException ex) {
            throw new CustomException(ErrorType.FILE_NOT_FOUND_ERROR, "Unable to find " + PropertyConstants.SERVER_PARAM_FILE_NAME + " file at: " + PropertyConstants.SERVER_PARAM_FILE_PATH, ex);
        } catch (IOException ex) {
            throw new CustomException(ErrorType.IO_ERROR, "Unable to open " + PropertyConstants.SERVER_PARAM_FILE_NAME + " file at: " + PropertyConstants.SERVER_PARAM_FILE_PATH, ex);
        }
    }

    public static Util getInstance() throws CustomException {
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }

//=================DATABASE PARAMS==================================================
    public DatabaseType getCurrentDatabase() {
        String type = databaseParams.getProperty(PropertyConstants.DATABASE_PARAM_FILE_CURRENT_DB);
        return DatabaseType.parseDatabaseType(type);
    }

    public void setCurrentDatabase(DatabaseType databaseType) throws CustomException {
        try ( OutputStream out = new FileOutputStream(databaseParamsFile)) {
            databaseParams.setProperty(PropertyConstants.DATABASE_PARAM_FILE_CURRENT_DB, DatabaseType.toPropertyName(databaseType));
            databaseParams.store(out, null);
        } catch (IOException e) {
            throw new CustomException(ErrorType.IO_ERROR, "Unable to set current database!", e);
        }
    }

    public String getCurrentDatabaseUrl() {
        String currentDB = databaseParams.getProperty(PropertyConstants.DATABASE_PARAM_FILE_CURRENT_DB);
        return databaseParams.getProperty(currentDB + "_" + PropertyConstants.DATABASE_PARAM_FILE_URL);
    }

    public String getCurrentDatabaseUsername() {
        String currentDB = databaseParams.getProperty(PropertyConstants.DATABASE_PARAM_FILE_CURRENT_DB);
        return databaseParams.getProperty(currentDB + "_" + PropertyConstants.DATABASE_PARAM_FILE_USERNAME);
    }

    public String getCurrentDatabasePassword() {
        String currentDB = databaseParams.getProperty(PropertyConstants.DATABASE_PARAM_FILE_CURRENT_DB);
        return databaseParams.getProperty(currentDB + "_" + PropertyConstants.DATABASE_PARAM_FILE_PASSWORD);
    }

    public String getDatabaseUrl(DatabaseType databaseType) {
        return databaseParams.getProperty(DatabaseType.toPropertyName(databaseType) + "_" + PropertyConstants.DATABASE_PARAM_FILE_URL);
    }

    public String getDatabaseUsername(DatabaseType databaseType) {
        return databaseParams.getProperty(DatabaseType.toPropertyName(databaseType) + "_" + PropertyConstants.DATABASE_PARAM_FILE_USERNAME);
    }

    public String getDatabasePassword(DatabaseType databaseType) {
        return databaseParams.getProperty(DatabaseType.toPropertyName(databaseType) + "_" + PropertyConstants.DATABASE_PARAM_FILE_PASSWORD);
    }

//=======================SERVER PARAMS===============================================
    public String getServerUrl() {
        return serverParams.getProperty(PropertyConstants.SERVER_PARAM_FILE_URL);
    }

    public int getServerPort() {
        return Integer.parseInt(serverParams.getProperty(PropertyConstants.SERVER_PARAM_FILE_PORT));
    }

    public void setServerPort(Integer value) throws CustomException {
        try ( OutputStream out = new FileOutputStream(serverParamsFile)) {
            serverParams.setProperty(PropertyConstants.SERVER_PARAM_FILE_PORT, String.valueOf(value));
            serverParams.store(out, null);
        } catch (IOException e) {
            throw new CustomException(ErrorType.IO_ERROR, "Unable to save port changes!", e);
        }
    }

    //================FORMS FAVICONS================================================    
    public String getFrmMainFavicon() {
        return imagesLocations.getProperty(PropertyConstants.IMAGES_LOCATIONS_FILE_FRMMAIN_FAVICON);
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
