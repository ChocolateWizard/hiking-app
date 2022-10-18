/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.threads;

import com.borak.hikingapp.commonlib.domain.classes.User;
import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.server.logic.controllers.Util;
import com.borak.hikingapp.server.view.tables.TableLoggedUsers;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Despot
 */
public class ServerThread extends Thread {

    private ServerSocket serverSocket;
    private List<HandleClientThread> lisOfClients;

    public ServerThread() throws CustomException {
        try {
            int port = Util.getInstance().getServerPort();
            serverSocket = new ServerSocket(port);
            lisOfClients = new LinkedList<>();
        } catch (CustomException | IOException ex) {
            throw new CustomException(ErrorType.SERVER_SOCKET_ERROR, "Unable to initialize server socket!", ex);
        }
    }

    @Override
    public void run() {
        int i = 0;
        while (!serverSocket.isClosed()) {
            try {
                System.out.println("Waiting for a client...");
                Socket socket = serverSocket.accept();
                System.out.println("Client No. " + (++i) + " connected!");
                HandleClientThread client = new HandleClientThread(socket, i);
                lisOfClients.add(client);
                client.start();
            } catch (IOException ex) {
                if(!serverSocket.isClosed()){
                    System.out.println("Error in accepting client: " + ex.getMessage());
                    ex.printStackTrace();
                }else{
                    System.out.println("Server shut down");
                }             
            }
        }
        stopAllClientThreads();
    }

    private void stopAllClientThreads() {
        for (HandleClientThread client : lisOfClients) {
            try {
                client.getSocket().close();
            } catch (IOException ex) {
                System.out.println("Error in deleting client thread number " + client.getClientNum() + ": " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
//==================================================================================================================

//    public ServerSocket getServerSocket() {
//        return serverSocket;
//    }
    public void terminate() throws IOException{
        serverSocket.close();
    }

    public void removeClient(HandleClientThread clientThread) {
        lisOfClients.remove(clientThread);
    }
//==================================================================================================================
    

}
