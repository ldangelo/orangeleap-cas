package com.mpower.cas.domain;

/**
 * A Site represents a logical grouping of Users. The Site defines
 * the <code>domain</code> half of the user's login. Once set, the domain
 * is read-only, but the other fields can be changed.
 *
 * @version 1.0
 */
public class Site {

    private int id;
    private String companyName;
    private String loginDomain;
    private String primaryContact;
    private String contactPhone;
    private String contactEmail;
    private boolean active;
    private boolean locked;


    /**
     * Constructor to be used when creating a new site, i.e. it has not
     * yet been assigned a unique ID. Login Domain is used in the constructor
     * as it is an immutable field (along with ID)
     *
     * @param loginDomain the Login Domain for the Site
     */
    public Site(String loginDomain) {

    }

    /**
     * Constructor to use when loading an existing Site from the backing store.
     * It takes the two immutable fields as parameters.
     *
     * @param id          the Unique ID for the Site
     * @param loginDomain the Login Domain for the Site
     */
    public Site(int id, String loginDomain) {
        this.id = id;
        this.loginDomain = loginDomain;
    }


    // Various Getters and Setters

    public int getId() {
        return id;
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
