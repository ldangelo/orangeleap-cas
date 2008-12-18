package com.mpower.cas.domain;

import java.util.Date;
import java.io.Serializable;

/**
 * The user represents two concepts. The first is someone who will
 * login to the system for single sign-on purposes to authenticate to another
 * system. The second are users who will login to the Authentication Service
 * in order to manage user and site accounts. Only users who are admins
 * for a site can login to modify information. This is indicated with a simple
 * boolean flag on the User for now.
 * @version 1.0
 */
public class User implements Serializable {

    // we'll use a default value of -1 to indicate a new user that is not
    // in the database
    private int id = -1;
    private String loginName;  // the short name used to login to the system
    private String fullName;
    private String emailAddress;
    private Date lastLogin;
    private int loginAttempts = 0;
    private Date passwordChangeDate;
    private String password; // Base64 HASH value - not clear text
    private String priorPassword;
    private String resetCode;
    private int siteId;
    private Date lockedDate;

    // various flags used to indicate the status of the User
    private boolean siteAdmin;
    private boolean locked;
    private boolean active;

    // Bunch of getters/setters.
    public int getId() {
        return id;
    }

    /**
     * This setter should never be called by code outside the UserService.
     * You cannot update the ID of a User manually.
     */
    public void setId(int id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    public Date getPasswordChangeDate() {
        return passwordChangeDate;
    }

    public void setPasswordChangeDate(Date passwordChangeDate) {
        this.passwordChangeDate = passwordChangeDate;
    }

    /**
     * HASH value of the password, not the clear text
     * @return
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPriorPassword() {
        return priorPassword;
    }

    public void setPriorPassword(String priorPassword) {
        this.priorPassword = priorPassword;
    }

    public String getResetCode() {
        return resetCode;
    }

    public void setResetCode(String resetCode) {
        this.resetCode = resetCode;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public boolean isSiteAdmin() {
        return siteAdmin;
    }

    public void setSiteAdmin(boolean siteAdmin) {
        this.siteAdmin = siteAdmin;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * The Date the account was locked. Null if the account is not locked
     * @return
     */
    public Date getLockedDate() {
        return lockedDate;
    }

    public void setLockedDate(Date lockedDate) {
        this.lockedDate = lockedDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
