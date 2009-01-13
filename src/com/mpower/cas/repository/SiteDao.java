package com.mpower.cas.repository;

import com.mpower.cas.domain.Site;
import com.mpower.cas.domain.User;

import java.util.List;

/**
 * DAO used for getting Site information from a backing
 * data store
 * @version 1.0
 */
public interface SiteDao {

    /**
     * Retrieves a Site based on the login domain
     * @param loginDomain the login domain of the site
     * @return the corresponding Site object
     */
    public Site getSite(String loginDomain);

    /**
     * Retrieves a Site by its unique numeric ID
     * @param siteId the numeric ID of the site
     * @return the corresponding Site object
     */
    public Site getSite(int siteId);

    /**
     * Returns a List of Users associated with a specific Site ID. The
     * list will be empty if there are no users associated with the site (not null).
     * @param siteId the numeric ID of the Site
     * @return a List<User> of the Users
     */
    public List<User> getUsers(int siteId);

    /**
     * Returns the count of the number of users for a Site
     * @param siteId the numeric Site ID
     * @return an int user count, always >= 0
     */
    public int getUserCount(int siteId);

    /**
     * Returns a List of Users for the specific Site who are currently locked out.
     * @param siteId the numeric ID of the Site
     * @return a List<User> of the locked-out Users
     */
    public List<User> getLockedOutUsers(int siteId);


    /**
     * Returns a count of the number of users for a Site that
     * are currently locked out
     * @param siteId the numeric Site ID
     * @return an int count of locked out users, always >= 0
     */
    public int getLockedOutUserCount(int siteId);


    /**
     * Update the data for the specified Site. Only certain fields are allowed
     * to be updated. Changes to read-only fields are ignored.
     * Note that the ID property of the Site must be present. If ID is not set,
     * this method will assume you are trying to create instead of update, and will
     * throw an IllegalArgumentException
     * @param site the Site to update
     */
    public void updateSite(Site site);

    /**
     * Creates a new Site in the system and sets the generated ID on the
     * Site object
     * @param site a Site object containing the initial data for record
     */
    public void createSite(Site site);
    
}
