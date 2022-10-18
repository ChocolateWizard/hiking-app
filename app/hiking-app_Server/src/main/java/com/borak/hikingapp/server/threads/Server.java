/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.borak.hikingapp.server.threads;

import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.server.threads.HandleClientThread;
import com.borak.hikingapp.server.threads.ServerThread;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Server {

    private ServerThread serverThread;

    public Server() {
        System.out.println("=====================SERVER INITIALIZED=======================");
    }

    public boolean isUp() {
        return (serverThread != null && serverThread.isAlive());
    }

    public boolean isDown() {
        return (serverThread == null || !serverThread.isAlive());
    }

    public void bootUp() throws CustomException {
        if (isUp()) {
            throw new CustomException(ErrorType.SERVER_BOOT_ERROR, "Server is already running!");
        }
        try {
            serverThread = new ServerThread();
            serverThread.start();
        } catch (IllegalThreadStateException e) {
            throw new CustomException(ErrorType.SERVER_BOOT_ERROR, "Unable to boot server!", e);
        }
    }

    public void shutDown() throws CustomException {
        if (isDown()) {
            throw new CustomException(ErrorType.SERVER_SHUTDOWN_ERROR, "Server is already down!");
        }
        try {
            serverThread.terminate();
        } catch (IOException ex) {
            throw new CustomException(ErrorType.SERVER_SHUTDOWN_ERROR, "Unable to shut down server!", ex);
        }
    }

    public void removeClient(HandleClientThread clientThread) {
        if(serverThread!=null){
            serverThread.removeClient(clientThread);
        }
    }

}
