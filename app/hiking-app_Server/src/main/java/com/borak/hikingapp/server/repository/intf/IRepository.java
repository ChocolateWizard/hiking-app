/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.borak.hikingapp.server.repository.intf;


import com.borak.hikingapp.commonlib.exceptions.CustomException;
import java.util.List;

/**
 *
 * @author Despot
 */
public interface IRepository<T> {
    
    public void connect()throws CustomException;
    public void disconnect()throws CustomException;
    public void commit()throws CustomException;
    public void rollback()throws CustomException;
    
    public List<T> getAll()throws CustomException;
    public void insert(T object)throws CustomException;
    public T find(T object)throws CustomException;
    public void delete(T object)throws CustomException;
    public void update(T object)throws CustomException;
}
