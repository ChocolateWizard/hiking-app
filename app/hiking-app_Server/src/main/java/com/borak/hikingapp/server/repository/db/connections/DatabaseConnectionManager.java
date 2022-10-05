/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.repository.db.connections;


import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.server.repository.IRepository;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Despot
 */
public abstract class DatabaseConnectionManager<T> implements IRepository<T> {

    protected Connection connection;

    @Override
    public void rollback() throws CustomException {
        try {
            connection.rollback();
        } catch (NullPointerException ex) {
            throw new CustomException(ErrorType.CONNECTION_ROLLBACK_ERROR, "Connection must be established first, in order to rollback transaction!", ex);
        } catch (SQLException ex) {
            throw new CustomException(ErrorType.CONNECTION_ROLLBACK_ERROR, "Unable to rollback transaction!", ex);
        }
    }

    @Override
    public void commit() throws CustomException {
        try {
            connection.commit();
        } catch (NullPointerException ex) {
            throw new CustomException(ErrorType.CONNECTION_COMMIT_ERROR, "Connection must be established first, in order to commit transaction!", ex);
        } catch (SQLException ex) {
            throw new CustomException(ErrorType.CONNECTION_COMMIT_ERROR, "Unable to commit transaction!", ex);
        }
    }

    @Override
    public void disconnect() throws CustomException {
        try {
            connection.close();
        } catch (NullPointerException ex) {
            throw new CustomException(ErrorType.CONNECTION_TERMINATION_ERROR, "Connection must be established first, in order to be closed!", ex);
        } catch (SQLException ex) {
            throw new CustomException(ErrorType.CONNECTION_TERMINATION_ERROR, "Unable to close connection with database!", ex);
        }
    }

    @Override
    public void connect() throws CustomException {
        connection = DatabaseConnectionFactory.getInstance().getConnection();
    }

}
