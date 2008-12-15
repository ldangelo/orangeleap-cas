package com.mpower.cas.domain;

import java.util.Date;

/**
 * The user represents two concepts. The first is someone who will
 * login to the system for single sign-on purposes to authenticate to another
 * system. The second are users who will login to the Authentication Service
 * in order to manage user and site accounts. Only users who are admins
 * for a site can login to modify information. This is indicated with a simple
 * boolean flag on the User for now.
 * @version 1.0
 */
public class User {

    private int id;
    // the short name used to login to the system
    private String loginName;
    private String fullName;
    private String emailAddress;
    private Date lastLogin;
    private int loginAttempts;
    private Date passwordChangeDate;
    private String resetCode;
    private Site site;

    // various flags used to indicate the status of the User
    private boolean siteAdmin;
    private boolean locked;
    private boolean active;

    /**
     * No-arg constructor should be used when creating a new User, i.e.
     * when the user has not been assigned an ID.
     */
    public User() {

    }

    /**
     * This constructor should be used when loading an existing user from the
     * database. ID is an immutable attribute of the User.
     * @param id the unique ID for the user
     */
    public User(int id) {
        this.id  = id;        
    }

    // Bunch of getters/setters. For now, ID is the only immutable field, but
    // will change some of the flags to be immutable too

    public int getId() {
        return id;
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

    public String getResetCode() {
        return resetCode;
    }

    public void setResetCode(String resetCode) {
        this.resetCode = resetCode;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
