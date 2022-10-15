/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.threads;

import com.borak.hikingapp.commonlib.communication.Receiver;
import com.borak.hikingapp.commonlib.communication.Sender;
import com.borak.hikingapp.commonlib.communication.TransferObject;
import com.borak.hikingapp.commonlib.domain.classes.Hiker;
import com.borak.hikingapp.commonlib.domain.classes.HikingGroup;
import com.borak.hikingapp.commonlib.domain.classes.Place;
import com.borak.hikingapp.commonlib.domain.classes.User;
import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.domain.enums.ResponseType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.server.logic.controllers.ControllerSO;
import com.borak.hikingapp.server.logic.controllers.ControllerServer;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Despot
 */
public class HandleClientThread extends Thread {

    private Socket socket;
    private int clientNum;
    private User loggedUser;

    HandleClientThread(Socket socket, int clientNum) {
        this.socket = socket;
        this.clientNum = clientNum;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                handleClient();
            }
        } catch (CustomException e) {
            //Client thread reached its end either by network malfuctioning, or client program terminating!
            try {
                if (ControllerServer.getInstance().isOnline()) {
                    ControllerServer.getInstance().removeClient(this);
                    ControllerSO.getInstance().logout(loggedUser);
                    System.out.println("Client No. " + clientNum + " disconnected!");
                }
            } catch (CustomException ex) {
                ex.printStackTrace();
            }

        }
    }

    private void handleClient() throws CustomException {
        try {
            TransferObject request = (new Receiver(socket)).receive();
            switch (request.getRequestType()) {
                case GET_ALL_PLACES:
                    getAllPlaces();
                    break;
                case REGISTER:
                    register(request);
                    break;
                case LOGIN:
                    login(request);
                    break;
                case CREATE_HIKER:
                    createHiker(request);
                    break;
                case FIND_HIKERS:
                    findHikers(request);
                    break;
                case UPDATE_HIKER:
                    updateHiker(request);
                    break;
                case DELETE_HIKER:
                    deleteHiker(request);
                    break;
                case CREATE_HIKING_GROUP:
                    createHikingGroup(request);
                    break;
                case FIND_HIKING_GROUPS:
                    findHikingGroups(request);
                    break;
                case DELETE_HIKING_GROUP:
                    deleteHikingGroup(request);
                    break;
                case UPDATE_HIKING_GROUP:
                    updateHikingGroup(request);
                    break;
                default:
            }
        } catch (CustomException ex) {
            TransferObject response = new TransferObject(ResponseType.ERROR, null, ex);
            (new Sender(socket)).send(response);
        }
    }

    private void getAllPlaces() throws CustomException {
        List<Place> places = ControllerSO.getInstance().getAllPlaces();
        TransferObject response = new TransferObject(ResponseType.SUCCESS, places, null);
        (new Sender(socket)).send(response);
    }

    private void register(TransferObject request) throws CustomException {
        try {
            User user = (User) request.getArgument();
            ControllerSO.getInstance().register(user);
            TransferObject response = new TransferObject(ResponseType.SUCCESS, null, null);
            (new Sender(socket)).send(response);
        } catch (CustomException | ClassCastException e) {
            throw new CustomException(ErrorType.REGISTRATION_ERROR, e.getMessage());
        }
    }

    private void login(TransferObject request) throws CustomException {
        try {
            User clientUser = (User) request.getArgument();
            User databaseUser = ControllerSO.getInstance().login(clientUser);
            loggedUser = databaseUser;
            TransferObject response = new TransferObject(ResponseType.SUCCESS, databaseUser, null);
            (new Sender(socket)).send(response);
        } catch (CustomException | ClassCastException e) {
            throw new CustomException(ErrorType.LOGIN_ERROR, e.getMessage());
        }
    }

    private void createHiker(TransferObject request) throws CustomException {
        try {
            Hiker hiker = (Hiker) request.getArgument();
            ControllerSO.getInstance().createHiker(hiker);
            TransferObject response = new TransferObject(ResponseType.SUCCESS, null, null);
            (new Sender(socket)).send(response);
        } catch (CustomException | ClassCastException e) {
            throw new CustomException(ErrorType.HIKER_CREATION_ERROR, e.getMessage());
        }
    }

    private void findHikers(TransferObject request) throws CustomException {
        try {
            String name = (String) request.getArgument();
            List<Hiker> hikers = ControllerSO.getInstance().findHikers(name);
            TransferObject response = new TransferObject(ResponseType.SUCCESS, hikers, null);
            (new Sender(socket)).send(response);
        } catch (CustomException | ClassCastException e) {
            throw new CustomException(ErrorType.SEARCH_ERROR, e.getMessage());
        }
    }

    private void updateHiker(TransferObject request) throws CustomException {
        try {
            Hiker hiker = (Hiker) request.getArgument();
            ControllerSO.getInstance().updateHiker(hiker);
            TransferObject response = new TransferObject(ResponseType.SUCCESS, null, null);
            (new Sender(socket)).send(response);
        } catch (CustomException | ClassCastException e) {
            throw new CustomException(ErrorType.HIKER_UPDATE_ERROR, e.getMessage());
        }
    }

    private void deleteHiker(TransferObject request) throws CustomException {
        try {
            Hiker hiker = (Hiker) request.getArgument();
            ControllerSO.getInstance().deleteHiker(hiker);
            TransferObject response = new TransferObject(ResponseType.SUCCESS, null, null);
            (new Sender(socket)).send(response);
        } catch (CustomException | ClassCastException e) {
            throw new CustomException(ErrorType.HIKER_DELETE_ERROR, e.getMessage());
        }
    }

    private void createHikingGroup(TransferObject request) throws CustomException {
        try {
            HikingGroup group = (HikingGroup) request.getArgument();
            ControllerSO.getInstance().createHikingGroup(group);
            TransferObject response = new TransferObject(ResponseType.SUCCESS, null, null);
            (new Sender(socket)).send(response);
        } catch (CustomException | ClassCastException e) {
            throw new CustomException(ErrorType.HIKING_GROUP_CREATION_ERROR, e.getMessage());
        }
    }

    private void findHikingGroups(TransferObject request) throws CustomException {
        try {
            String name = (String) request.getArgument();
            List<HikingGroup> groups = ControllerSO.getInstance().findHikingGroups(name);
            TransferObject response = new TransferObject(ResponseType.SUCCESS, groups, null);
            (new Sender(socket)).send(response);
        } catch (CustomException | ClassCastException e) {
            throw new CustomException(ErrorType.SEARCH_ERROR, e.getMessage());
        }
    }

    private void deleteHikingGroup(TransferObject request) throws CustomException {
        try {
            HikingGroup group = (HikingGroup) request.getArgument();
            ControllerSO.getInstance().deleteHikingGroup(group);
            TransferObject response = new TransferObject(ResponseType.SUCCESS, null, null);
            (new Sender(socket)).send(response);
        } catch (CustomException | ClassCastException e) {
            throw new CustomException(ErrorType.HIKING_GROUP_DELETE_ERROR, e.getMessage());
        }
    }

    private void updateHikingGroup(TransferObject request) throws CustomException {
        try {
            HikingGroup group = (HikingGroup) request.getArgument();
            ControllerSO.getInstance().updateHikingGroup(group);
            TransferObject response = new TransferObject(ResponseType.SUCCESS, null, null);
            (new Sender(socket)).send(response);
        } catch (CustomException | ClassCastException e) {
            throw new CustomException(ErrorType.HIKING_GROUP_UPDATE_ERROR, e.getMessage());
        }
    }

//==========================================================================================================
    public Socket getSocket() {
        return socket;
    }

    public int getClientNum() {
        return clientNum;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.clientNum;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HandleClientThread other = (HandleClientThread) obj;
        return this.clientNum == other.clientNum;
    }

}
