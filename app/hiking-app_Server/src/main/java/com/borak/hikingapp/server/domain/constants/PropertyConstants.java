/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.borak.hikingapp.server.domain.constants;

/**
 *
 * @author Despot
 */
public final class PropertyConstants {

    private PropertyConstants() {
    }

    public static final String IMAGES_LOCATIONS_FILE_NAME = "images.locations.properties";
    public static final String IMAGES_LOCATIONS_FILE_PATH = "src/main/resources/config/" + IMAGES_LOCATIONS_FILE_NAME;

    public static final String IMAGES_LOCATIONS_FILE_FRMMAIN_FAVICON = "FrmMain_favicon";
    public static final String IMAGES_LOCATIONS_FILE_FRMCONFIGURATIONPORTFAVICON = "FrmConfigurationPort_favicon";
    public static final String IMAGES_LOCATIONS_FILE_FRMCONFIGURATIONDATABASEFAVICON = "FrmConfigurationDatabase_favicon";
    public static final String IMAGES_LOCATIONS_FILE_SUCCESS_FAVICON = "Success_favicon";
    public static final String IMAGES_LOCATIONS_FILE_QUESTION_FAVICON = "Question_favicon";
    public static final String IMAGES_LOCATIONS_FILE_ERROR_FAVICON = "Error_favicon";

    public static final String DATABASE_PARAM_FILE_NAME = "db.param.properties";
    public static final String DATABASE_PARAM_FILE_PATH = "src/main/resources/config/" + DATABASE_PARAM_FILE_NAME;

    public static final String DATABASE_PARAM_FILE_CURRENT_DB = "current_db";
    public static final String DATABASE_PARAM_FILE_URL = "url";
    public static final String DATABASE_PARAM_FILE_USERNAME = "username";
    public static final String DATABASE_PARAM_FILE_PASSWORD = "password";

    public static final String SERVER_PARAM_FILE_NAME = "server.param.properties";
    public static final String SERVER_PARAM_FILE_PATH = "src/main/resources/config/" + SERVER_PARAM_FILE_NAME;

    public static final String SERVER_PARAM_FILE_URL = "url";
    public static final String SERVER_PARAM_FILE_PORT = "port";

}
