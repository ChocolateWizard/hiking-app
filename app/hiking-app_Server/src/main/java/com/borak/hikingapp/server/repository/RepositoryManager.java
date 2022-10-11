/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borak.hikingapp.server.repository;

import com.borak.hikingapp.commonlib.domain.classes.Hiker;
import com.borak.hikingapp.commonlib.domain.classes.HikingGroup;
import com.borak.hikingapp.commonlib.domain.classes.Place;
import com.borak.hikingapp.commonlib.domain.classes.User;
import com.borak.hikingapp.commonlib.domain.enums.ErrorType;
import com.borak.hikingapp.commonlib.exceptions.CustomException;
import com.borak.hikingapp.server.domain.enums.DatabaseType;
import com.borak.hikingapp.server.logic.controllers.Util;
import com.borak.hikingapp.server.repository.IRepository;
import com.borak.hikingapp.server.repository.om.RepositoryLoggedUsers;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author User
 */
public final class RepositoryManager {

    private DatabaseType currentDatabase;

    private IRepository<Place> repositoryPlace;
    private IRepository<User> repositoryUser;
    private IRepository<Hiker> repositoryHiker;
    private IRepository<HikingGroup> repositoryHikingGroup;
    private RepositoryLoggedUsers repositoryLoggedUsers;

    public RepositoryManager() throws CustomException {
        setRepositories();
    }

    public void setRepositories() throws CustomException {
        try {
            currentDatabase = Util.getInstance().getCurrentDatabase();
            switch (currentDatabase) {
                case OM:
                    repositoryPlace = new com.borak.hikingapp.server.repository.om.RepositoryPlace();
                    repositoryUser = new com.borak.hikingapp.server.repository.om.RepositoryUser();
                    repositoryHiker = new com.borak.hikingapp.server.repository.om.RepositoryHiker();
                    repositoryHikingGroup = new com.borak.hikingapp.server.repository.om.RepositoryHikingGroup();
                    break;
                case MYSQL:
                    repositoryPlace = new com.borak.hikingapp.server.repository.db.mysql.RepositoryPlace();
                    repositoryUser = new com.borak.hikingapp.server.repository.db.mysql.RepositoryUser();
                    repositoryHiker = new com.borak.hikingapp.server.repository.db.mysql.RepositoryHiker();
                    repositoryHikingGroup = new com.borak.hikingapp.server.repository.db.mysql.RepositoryHikingGroup();
                    break;
                default:
            }
            repositoryLoggedUsers = new com.borak.hikingapp.server.repository.om.RepositoryLoggedUsers();
        } catch (CustomException e) {
            throw new CustomException(ErrorType.CRITICAL_ERROR, "Unable to initialize repositories!", e);
        }
    }
//==========================================================================================================

    public IRepository<Place> getRepositoryPlace() {
        return repositoryPlace;
    }

    public IRepository<Hiker> getRepositoryHiker() {
        return repositoryHiker;
    }

    public IRepository<HikingGroup> getRepositoryHikingGroup() {
        return repositoryHikingGroup;
    }

    public IRepository<User> getRepositoryUser() {
        return repositoryUser;
    }

    public RepositoryLoggedUsers getRepositoryLoggedUsers() {
        return repositoryLoggedUsers;
    }
    
    
}
