package com.mpower.cas.domain;

import java.util.Date;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

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

    private final static DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

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

    public String getLastLogin() {
        return (lastLogin == null) ? "" : FORMAT.format(lastLogin);
    }

    /**
     * Setter using a Date instead of a String. This setter
     * is usually used when loading a user from the data store
     * @param lastLogin
     */
    public void setLastLoginDate(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     * Sets the date of the last login using a String representation
     * of the date in yyyy-MM-dd format. Date strings that are not
     * in the correct format are ignored.
     * @param date the date String
     */
    public void setLastLogin(String date) {
        if(date != null) {
            try {
                lastLogin = FORMAT.parse(date);
            } catch(ParseException ex) {
                // ignore for now
            }
        }
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    /**
     * Returns a String representation of the date of the
     * last password change in yyyy-MM-dd format
     * @return
     */
    public String getPasswordChange() {
        return (passwordChangeDate == null) ? "" : FORMAT.format(passwordChangeDate);
    }

    public Date getPasswordChangeDate() {
        return passwordChangeDate;
    }

    /**
     * Sets the password change date as a Date. This setter
     * is the one usually used when loading a user from the database
     * @param passwordChangeDate
     */
    public void setPasswordChangeDate(Date passwordChangeDate) {
        this.passwordChangeDate = passwordChangeDate;
    }

    /**
     * Sets the password change date using a String representation
     * of the date in the yyyy-MM-dd format. Dates in the incorrect
     * format are ignored.
     * @param date
     */
    public void setPasswordChange(String date) {
        if(date != null) {
            try {
                passwordChangeDate = FORMAT.parse(date);
            } catch(ParseException ex) {
                // ignore for now
            }
        }
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
    public String getLockedDate() {
        return (lockedDate == null) ? "":FORMAT.format(lockedDate);
    }

    public void setDateLockedDate(Date lockedDate) {
        this.lockedDate = lockedDate;
    }

    public void setLockedDate(String dateString) {
        if(dateString != null) {
            try {
                this.lockedDate = FORMAT.parse(dateString);
            } catch(ParseException ex) {
                // ignore for now
            }
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
