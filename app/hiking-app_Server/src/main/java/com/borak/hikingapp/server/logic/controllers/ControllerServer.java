/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.logic.controllers;

import com.borak.hikingapp.commonlib.domain.classes.User;
import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.server.threads.Server;
import com.borak.hikingapp.server.threads.HandleClientThread;
import com.borak.hikingapp.server.threads.ServerThread;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Despot
 */
public final class ControllerServer {

    private Server server;
    private static ControllerServer instance;

    private ControllerServer() {
        server = new Server();
    }

    public static ControllerServer getInstance() {
        if (instance == null) {
            instance = new ControllerServer();
        }
        return instance;
    }
//=========================SERVER BOOT==========================================================

    public boolean isOnline() {
        return server.isUp();
    }

    public boolean isOffline() {
        return server.isDown();
    }

    public void startServer() throws CustomException {
        ControllerSO.initialize();
        try {
            server.bootUp();
        } catch (CustomException e) {
            ControllerSO.getInstance().terminate();
            throw e;
        }
    }

    public void stopServer() throws CustomException {
        server.shutDown();
        ControllerSO.getInstance().terminate();
    }
//===============================================================================================================

    public void removeClient(HandleClientThread clientThread) {
            server.removeClient(clientThread);
    }
//
//    public ServerThread getServerThread() {
//        return server;
//    }

}
