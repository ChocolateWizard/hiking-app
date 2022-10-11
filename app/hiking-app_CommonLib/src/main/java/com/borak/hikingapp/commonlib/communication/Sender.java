/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.commonlib.communication;

import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author Despot
 */
public class Sender {

    private Socket socket;

    public Sender(Socket socket) {
        this.socket = socket;
    }

    public void send(TransferObject object) throws CustomException {
        try {
            OutputStream outSocket = socket.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(outSocket);
            out.writeObject(object);
            out.flush();
        } catch (IOException ex) {
            throw new CustomException(ErrorType.SENDING_OBJECT_ERROR, "Error while sending object!", ex);
        }
    }

}
