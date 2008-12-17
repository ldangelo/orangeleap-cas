package com.mpower.cas.service;

import com.mpower.cas.domain.User;

import java.util.List;

/**
 * @version 1.0
 */
public interface UserService {

    public void changePassword(User user, String oldPassword, String newPassword);

    public void createUser(User user);

    public void updateUser(User user);

    public List<User> findUsers(String criteria);

    public void generateResetCode(User user);

    public User getUser(int id);

    public User getUser(String loginName);

    public void resetPassword(User user, String resetCode, String newPassword);

    public void setActive(User user);

    public void setAdmin(User user);

    public void setLockedOut(User user);

    public void setLoginAttempts(User user, int attempts);

}
