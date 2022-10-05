/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.repository.db.connections;

import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.server.logic.controllers.Util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Despot
 */
public class DatabaseConnectionFactory {

    private Connection connection;
    private static DatabaseConnectionFactory instance;

    private DatabaseConnectionFactory() {
    }

    public static DatabaseConnectionFactory getInstance() {
        if (instance == null) {
            instance = new DatabaseConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() throws CustomException {
        try {
            if (connection == null || connection.isClosed()) {
                String url = Util.getInstance().getCurrentDatabaseUrl();
                String username = Util.getInstance().getCurrentDatabaseUsername();
                String password = Util.getInstance().getCurrentDatabasePassword();
                connection = DriverManager.getConnection(url, username, password);
                connection.setAutoCommit(false);
            }
        } catch (SQLException ex) {
            throw new CustomException(ErrorType.CONNECTION_ESTABLISHING_ERROR, "Unable to establish connection with database!", ex);
        }
        return connection;
    }

}
