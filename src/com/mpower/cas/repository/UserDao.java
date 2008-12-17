package com.mpower.cas.repository;

import com.mpower.cas.domain.User;

/**
 * The DAO used by the UserService class
 * @version 1.0
 */
public interface UserDao {

    public User getUser(int id);

    public User getUser(String loginName);

    public void saveUser(User user);

    public void updateUser(User user);

    public void setActive(User user);

    public void setAdmin(User user);

    public void setLockedOut(User user);

    public void setLoginAttempts(User user, int attempts);

}
