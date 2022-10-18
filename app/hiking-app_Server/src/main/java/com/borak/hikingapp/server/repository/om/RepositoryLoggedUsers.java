/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.repository.om;

import com.borak.hikingapp.commonlib.domain.classes.User;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import java.util.LinkedList;
import java.util.List;
import com.borak.hikingapp.server.view.tables.UserViewObserver;
import com.borak.hikingapp.server.logic.controllers.ControllerForms;

/**
 *
 * @author User
 */
public class RepositoryLoggedUsers {

    private List<User> loggedUsers;
    private List<UserViewObserver> userTables;

    public RepositoryLoggedUsers() {
        loggedUsers = new LinkedList<>();
        userTables = new LinkedList<>();
        userTables.add(ControllerForms.getInstance().getTableLoggedUsers());
    }

    public List<User> getAll() throws CustomException {
        return loggedUsers;
    }

    public void add(User user) throws CustomException {
        loggedUsers.add(user);
        for (UserViewObserver userTable : userTables) {
            userTable.addUser(user);
        }
    }

    public boolean isLoggedIn(User user) {
        for (User loggedUser : loggedUsers) {
            if (loggedUser.getUsername().equals(user.getUsername())) {
                return true;
            }
        }
        return false;
    }

    public void remove(User object) throws CustomException {
        if (loggedUsers.remove(object)) {
            for (UserViewObserver userTable : userTables) {
                userTable.removeUser(object);
            }
        }
    }

    public void removeAll() {
        loggedUsers = new LinkedList<>();
        for (UserViewObserver userTable : userTables) {
            userTable.removeAllUsers();
        }
    }

}
