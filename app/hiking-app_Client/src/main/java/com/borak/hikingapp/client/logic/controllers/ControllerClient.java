/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.client.logic.controllers;

import com.borak.hikingapp.client.domain.constants.ServerConstants;
import com.borak.hikingapp.client.logic.controllers.ControllerSO;
import com.borak.hikingapp.client.logic.controllers.Util;
import com.borak.hikingapp.client.view.helpers.Window;
import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public final class ControllerClient extends Thread {

    private Socket socket;
    private boolean disconnected;
    private static ControllerClient instance;

    private ControllerClient() {
        disconnected = false;
    }

    public static ControllerClient getInstance() {
        if (instance == null) {
            instance = new ControllerClient();
        }
        return instance;
    }
//=======================================================================================================
    public void connect() throws CustomException {
        try {
            socket = new Socket(ServerConstants.SERVER_ADDRESS, ServerConstants.SERVER_PORT);
            ControllerSO.getInstance().setSocket(socket);
//            start();
        } catch (IOException e) {
            throw new CustomException(ErrorType.CONNECTION_ESTABLISHING_ERROR, "Unable to establish connection with server!", e);
        }
    }

    public void disconnect() throws CustomException {
        if (socket != null) {
            try {
                disconnected = true;
                socket.close();
            } catch (IOException ex) {
                disconnected = false;
                throw new CustomException(ErrorType.CONNECTION_TERMINATION_ERROR, "Unable to disconnect from server!", ex);
            }
        }
    }

    @Override
    public void run() {
        while (socket != null && !socket.isClosed()) {

        }
        System.out.println("AAAA");
        if (disconnected == false) {
            Window.unSuccessfulOperation(null, "Disconnected from server", "Connection error");
            System.exit(0);
        }
    }

}
