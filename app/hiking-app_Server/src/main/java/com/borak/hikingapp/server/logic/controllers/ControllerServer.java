/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.logic.controllers;

import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.server.threads.ServerThread;
import java.io.IOException;

/**
 *
 * @author Despot
 */
public class ControllerServer {

    private static ControllerServer instance;

    private ServerThread serverThread;

    private ControllerServer() {
    }

    public static ControllerServer getInstance() {
        if (instance == null) {
            instance = new ControllerServer();
        }
        return instance;
    }

    public boolean isOnline() {
        return (serverThread != null && serverThread.getServerSocket().isBound());
    }

    public boolean isOffline() {
        return (serverThread == null || !serverThread.isAlive());
    }

    public void startServer() throws CustomException {
        serverThread = new ServerThread();
        serverThread.start();
    }

    public void stopServer() throws CustomException {
        try {
            serverThread.getServerSocket().close();
            serverThread = null;
        } catch (IOException | NullPointerException ex) {
            throw new CustomException(ErrorType.SERVER_TERMINATION_ERROR, "Unable to terminate server!", ex);
        }
    }

}
