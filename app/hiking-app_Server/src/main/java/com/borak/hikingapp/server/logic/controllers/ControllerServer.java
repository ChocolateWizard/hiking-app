/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.logic.controllers;

import com.borak.hikingapp.commonlib.domain.classes.User;
import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.server.threads.HandleClientThread;
import com.borak.hikingapp.server.threads.ServerThread;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Despot
 */
public final class ControllerServer {

    private ServerThread serverThread;
    private static ControllerServer instance;

    private ControllerServer() {
    }

    public static ControllerServer getInstance() {
        if (instance == null) {
            instance = new ControllerServer();
        }
        return instance;
    }
//=========================SERVER BOOT==========================================================

    public boolean isOnline() {
        return (serverThread != null && serverThread.getServerSocket().isBound());
    }

    public boolean isOffline() {
        return (serverThread == null || !serverThread.isAlive());
    }

    public void startServer() throws CustomException {
        ControllerSO.initialize();
        try {
            serverThread = new ServerThread();
            serverThread.start();
        } catch (CustomException e) {
            ControllerSO.getInstance().terminate();
            throw e;
        }
    }

    public void stopServer() throws CustomException {
        try {
            serverThread.getServerSocket().close();
            serverThread = null;           
            ControllerSO.getInstance().terminate();
        } catch (IOException | NullPointerException ex) {
            throw new CustomException(ErrorType.SERVER_TERMINATION_ERROR, "Unable to terminate server!", ex);
        }
    }
//===============================================================================================================

    public void removeClient(HandleClientThread clientThread) {
        if (serverThread != null) {
            serverThread.removeClient(clientThread);
        }
    }

    public ServerThread getServerThread() {
        return serverThread;
    }

}
