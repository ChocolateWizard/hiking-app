/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.commonlib.communication;

import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author Despot
 */
public class Receiver {

    private Socket socket;

    public Receiver(Socket socket) {
        this.socket = socket;
    }

    public TransferObject receive() throws CustomException {
        try {
            InputStream inSocket = socket.getInputStream();
            ObjectInputStream in = new ObjectInputStream(inSocket);
            return (TransferObject) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            throw new CustomException(ErrorType.RECEIVING_OBJECT_ERROR, "Error in receiving object!", ex);
        }

    }
}
