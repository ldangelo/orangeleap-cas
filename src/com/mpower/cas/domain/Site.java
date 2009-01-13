package com.mpower.cas.domain;

/**
 * A Site represents a logical grouping of Users. The Site defines
 * the <code>domain</code> half of the user's login.
 * @version 1.0
 */
public class Site {

    // -1 means the Site does not have an ID, meaning it is not in the DB
    private int id = -1;
    private String companyName;
    private String loginDomain;
    private String primaryContact;
    private String contactPhone;
    private String contactEmail;
    private boolean active = true;
    private boolean locked = false;

    // Various Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLoginDomain() {
        return loginDomain;
    }

    public void setLoginDomain(String loginDomain) {
        this.loginDomain = loginDomain;
    }

    public String getPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(String primaryContact) {
        this.primaryContact = primaryContact;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
