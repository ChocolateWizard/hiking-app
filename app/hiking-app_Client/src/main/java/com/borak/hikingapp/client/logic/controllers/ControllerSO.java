/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.client.logic.controllers;

import com.borak.hikingapp.commonlib.communication.Receiver;
import com.borak.hikingapp.commonlib.communication.Sender;
import com.borak.hikingapp.commonlib.communication.TransferObject;
import com.borak.hikingapp.commonlib.domain.classes.Hiker;
import com.borak.hikingapp.commonlib.domain.classes.HikingGroup;
import com.borak.hikingapp.commonlib.domain.classes.Profile;
import com.borak.hikingapp.commonlib.domain.classes.User;
import com.borak.hikingapp.commonlib.domain.enums.RequestType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author User
 */
public final class ControllerSO {

    private Socket socket;
    private static ControllerSO instance;

    private ControllerSO() {

    }

    public static ControllerSO getInstance() {
        if (instance == null) {
            instance = new ControllerSO();
        }
        return instance;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public TransferObject login(User user) throws CustomException {
        TransferObject request = new TransferObject(RequestType.LOGIN, user);
        (new Sender(socket)).send(request);
        return (new Receiver(socket)).receive();
    }

    public TransferObject getAllPlaces() throws CustomException {
        TransferObject request = new TransferObject(RequestType.GET_ALL_PLACES);
        (new Sender(socket)).send(request);
        return (new Receiver(socket)).receive();
    }

    public TransferObject register(User user) throws CustomException {
        TransferObject request = new TransferObject(RequestType.REGISTER, user);
        (new Sender(socket)).send(request);
        return (new Receiver(socket)).receive();
    }

    public TransferObject findHikingGroups(String name) throws CustomException {
        TransferObject request = new TransferObject(RequestType.FIND_HIKING_GROUPS, name);
        (new Sender(socket)).send(request);
        return (new Receiver(socket)).receive();
    }

    public TransferObject createHikingGroup(HikingGroup mainGroup) throws CustomException {
        TransferObject request = new TransferObject(RequestType.CREATE_HIKING_GROUP, mainGroup);
        (new Sender(socket)).send(request);
        return (new Receiver(socket)).receive();
    }

    public TransferObject updateHikingGroup(HikingGroup mainGroup) throws CustomException {
        TransferObject request = new TransferObject(RequestType.UPDATE_HIKING_GROUP, mainGroup);
        (new Sender(socket)).send(request);
        return (new Receiver(socket)).receive();
    }

    public TransferObject deleteHikingGroup(HikingGroup g) throws CustomException {
        TransferObject request = new TransferObject(RequestType.DELETE_HIKING_GROUP, g);
        (new Sender(socket)).send(request);
        return (new Receiver(socket)).receive();
    }

    public TransferObject findHikers(String name) throws CustomException {
        TransferObject request = new TransferObject(RequestType.FIND_HIKERS, name);
        (new Sender(socket)).send(request);
        return (new Receiver(socket)).receive();
    }

    public TransferObject updateHiker(Hiker h) throws CustomException {
        TransferObject request = new TransferObject(RequestType.UPDATE_HIKER, h);
        (new Sender(socket)).send(request);
        return (new Receiver(socket)).receive();
    }

    public TransferObject createHiker(Hiker h) throws CustomException {
        TransferObject request = new TransferObject(RequestType.CREATE_HIKER, h);
        (new Sender(socket)).send(request);
        return (new Receiver(socket)).receive();
    }

    public TransferObject deleteHiker(Hiker h) throws CustomException {
        TransferObject request = new TransferObject(RequestType.DELETE_HIKER, h);
        (new Sender(socket)).send(request);
        return (new Receiver(socket)).receive();
    }

    public TransferObject getAllHikingGroups() throws CustomException {
        TransferObject request = new TransferObject(RequestType.GET_ALL_HIKING_GROUPS);
        (new Sender(socket)).send(request);
        return (new Receiver(socket)).receive();
    }

    public TransferObject getAllHikers() throws CustomException {
        TransferObject request = new TransferObject(RequestType.GET_ALL_HIKERS);
        (new Sender(socket)).send(request);
        return (new Receiver(socket)).receive();
    }

    public TransferObject getAllProfiles() throws CustomException {
        TransferObject request = new TransferObject(RequestType.GET_ALL_PROFILES);
        (new Sender(socket)).send(request);
        return (new Receiver(socket)).receive();
    }

    public TransferObject saveProfiles(List<Profile> p) throws CustomException {
        TransferObject request = new TransferObject(RequestType.SAVE_PROFILES, p);
        (new Sender(socket)).send(request);
        return (new Receiver(socket)).receive();
    }

    public void showHiker(Hiker h) throws CustomException {
        ControllerForms.getInstance().openFrmHikerChangeInfo_Dialog(h);
    }

    public void showHikingGroup(HikingGroup g) throws CustomException {
        ControllerForms.getInstance().openFrmHikingGroupChangeInfo_Dialog(g);
    }

    public void showGroupPlan(HikingGroup g) throws CustomException {
        ControllerForms.getInstance().openFrmPlanShow_Dialog(g);
    }

    public TransferObject getMembersAndActivities(HikingGroup hikingGroup) throws CustomException {
        TransferObject request = new TransferObject(RequestType.GET_HIKING_GROUP_PLAN, hikingGroup);
        (new Sender(socket)).send(request);
        return (new Receiver(socket)).receive();
    }

}
