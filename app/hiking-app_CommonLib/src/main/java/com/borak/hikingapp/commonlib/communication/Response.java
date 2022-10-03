/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.commonlib.communication;

import com.borak.hikingapp.commonlib.domain.enums.ResponseType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;

/**
 *
 * @author Despot
 */
public class Response {
    
    private ResponseType responseType;
    private TransferObject argument;
    private CustomException exception;

    public Response() {
    }

    public Response(ResponseType responseType, TransferObject argument) {
        this.responseType = responseType;
        this.argument = argument;
    }

    
    
    public Response(ResponseType responseType, TransferObject argument, CustomException exception) {
        this.responseType = responseType;
        this.argument = argument;
        this.exception = exception;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public TransferObject getArgument() {
        return argument;
    }

    public void setArgument(TransferObject argument) {
        this.argument = argument;
    }

    public CustomException getException() {
        return exception;
    }

    public void setException(CustomException exception) {
        this.exception = exception;
    }
    
    
    
}
