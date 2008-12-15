package com.mpower.cas.domain;

import java.util.Date;

/**
 * Activity represents some action performed related to the authentication
 * service. It is used by the AuditService to track who does what, and when.
 * It contains the essential details of an auditable event.
 * @version 1.0
 */
public class Activity {

    private ActivityCode code;
    private String clientIpAddress;
    private int id;
    private User affecterUser;
    private User performingUser;
    private Site affectedSite;
    private Date timestamp = new Date();
    private String message;

    /**
     * No-arg constructor is used when creating a new activity to
     * pass to the AuditService for tracking. ID is not know.
     */
    public Activity() {

    }

    /**
     * This constructor is used when retrieving an activity
     * from the AuditService and the ID is known.
     * @param id
     */
    public Activity(int id) {
        this.id = id;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public ActivityCode getCode() {
        return code;
    }

    public void setCode(ActivityCode code) {
        this.code = code;
    }

    public String getClientIpAddress() {
        return clientIpAddress;
    }

    public void setClientIpAddress(String clientIpAddress) {
        this.clientIpAddress = clientIpAddress;
    }

    public User getAffecterUser() {
        return affecterUser;
    }

    public void setAffecterUser(User affecterUser) {
        this.affecterUser = affecterUser;
    }

    public User getPerformedBy() {
        return performingUser;
    }

    public void setPerformedBy(User performingUser) {
        this.performingUser = performingUser;
    }

    public Site getAffectedSite() {
        return affectedSite;
    }

    public void setAffectedSite(Site affectedSite) {
        this.affectedSite = affectedSite;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
