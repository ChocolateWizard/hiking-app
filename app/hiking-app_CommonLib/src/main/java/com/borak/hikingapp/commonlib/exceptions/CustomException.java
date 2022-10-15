/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.commonlib.exceptions;

import com.borak.hikingapp.commonlib.domain.enums.ErrorType;



/**
 *
 * @author Despot
 */
public class CustomException extends Exception{
    
    private final ErrorType errorType;
    private final String description;

    public CustomException(ErrorType errorType, String description) {
        super(description);
        this.errorType = errorType;
        this.description = description;
    }

    public CustomException(ErrorType errorType, String description, Throwable cause) {
        super(description, cause);
        this.errorType = errorType;
        this.description = description;
    }

    @Override
    public String toString() {
        return "\n"+errorType+": "+description;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
    
    
    
}
