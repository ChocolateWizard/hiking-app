/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.borak.hikingapp.server.view.tables;

import com.borak.hikingapp.commonlib.domain.classes.User;

/**
 *
 * @author User
 */
public interface UserViewObserver {
    void addUser(User user);
    void removeUser(User user);
    void removeAllUsers();
}
