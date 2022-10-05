/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.threads;

import java.net.Socket;

/**
 *
 * @author Despot
 */
public class HandleClientThread extends Thread {

    private Socket socket;
    
    HandleClientThread(Socket socket) {
        this.socket=socket;
    }

    public Socket getSocket() {
        return socket;
    }
    
    @Override
    public void run() {
        while (!socket.isClosed()) {
            handleClient();
        }
    }

    
    
    private void handleClient() {
        //TODO

    }
    
}
