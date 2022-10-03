/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.commonlib.communication;

import com.borak.hikingapp.commonlib.domain.enums.RequestType;

/**
 *
 * @author Despot
 */
public class Request {

    private RequestType requestType;
    private TransferObject argument;

    public Request() {
    }

    public Request(RequestType requestType, TransferObject argument) {
        this.requestType = requestType;
        this.argument = argument;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public TransferObject getArgument() {
        return argument;
    }

    public void setArgument(TransferObject argument) {
        this.argument = argument;
    }
    
}
