/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.borak.hikingapp.commonlib.communication;

import com.borak.hikingapp.commonlib.domain.enums.RequestType;
import com.borak.hikingapp.commonlib.domain.enums.ResponseType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import java.io.Serializable;

/**
 *
 * @author Despot
 */
public class TransferObject implements Serializable{

    private RequestType requestType;
    private ResponseType responseType;
    private Object argument;
    private CustomException exception;

    public TransferObject(RequestType requestType) {
        this.requestType = requestType;
    }

    public TransferObject(RequestType requestType, Object argument) {
        this.requestType = requestType;
        this.argument = argument;
    }

    public TransferObject(ResponseType responseType, CustomException exception) {
        this.responseType = responseType;
        this.exception = exception;
    }
     
    public TransferObject(ResponseType responseType, Object argument, CustomException exception) {
        this.responseType = responseType;
        this.argument = argument;
        this.exception = exception;
    }
    

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public Object getArgument() {
        return argument;
    }

    public void setArgument(Object argument) {
        this.argument = argument;
    }

    public CustomException getException() {
        return exception;
    }

    public void setException(CustomException exception) {
        this.exception = exception;
    }
    
    
    
    
    
}
