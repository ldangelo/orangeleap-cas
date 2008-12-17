package com.mpower.cas.service;

import com.mpower.cas.domain.User;
import com.mpower.cas.repository.UserDao;

import java.util.List;

/**
 * @version 1.0
 */
public class UserServiceImpl implements UserService{

    private AuditService auditService;
    private PasswordSecurityPolicy passwordPolicy;
    private UserDao userDao;
    private UserSearchService searchService;


    public void setAuditService(AuditService audit) {
        this.auditService = audit;
    }

    public void setPasswordPolicy(PasswordSecurityPolicy passwordPolicy) {
        this.passwordPolicy = passwordPolicy;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setSearchService(UserSearchService searchService) {
        this.searchService = searchService;
    }

    @Override
    public void changePassword(User user, String oldPassword, String newPassword) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void createUser(User user) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateUser(User user) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<User> findUsers(String criteria) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void generateResetCode(User user) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User getUser(int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User getUser(String loginName) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void resetPassword(User user, String resetCode, String newPassword) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setActive(User user) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setAdmin(User user) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setLockedOut(User user) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setLoginAttempts(User user, int attempts) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
