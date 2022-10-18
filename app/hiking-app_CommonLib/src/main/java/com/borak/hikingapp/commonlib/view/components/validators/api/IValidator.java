/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.borak.hikingapp.commonlib.view.components.validators.api;

import com.borak.hikingapp.commonlib.exceptions.CustomException;

/**
 *
 * @author Despot
 */
public interface IValidator<E> {

    public void validate(E entity) throws CustomException;
}
