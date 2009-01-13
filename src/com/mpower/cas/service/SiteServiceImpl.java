package com.mpower.cas.service;

import com.mpower.cas.domain.Site;
import com.mpower.cas.domain.User;
import com.mpower.cas.repository.SiteDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

/**
 * Basic implementation of the SiteService that depends on the
 * SiteDao for its operations. Current implementation is just a
 * pretty straight forward pass-through of the method calls down
 * to the DAO, as there is not a lot of business logic involved
 * at this point.
 * @version 1.0
 */
public class SiteServiceImpl implements SiteService {

    private SiteDao siteDao;
    private SiteSearchService siteSearchService;

    /**
     * Injected resources. Sets the SiteDao to be used
     * @param siteDao
     */
    @Required
    public void setSiteDao(SiteDao siteDao) {
        this.siteDao = siteDao;
    }

    @Required
    public void setSiteSearchService(SiteSearchService siteSearchService) {
        this.siteSearchService = siteSearchService;
    }

    /**
     * Retrieves a Site based on the login domain
     *
     * @param loginDomain the login domain of the site
     * @return the corresponding Site object
     */
    @Override
    public Site getSite(String loginDomain) {
        // shortcut for null/empty strings
        if(loginDomain == null || loginDomain.length() == 0) {
            return null;
        }

        return siteDao.getSite(loginDomain);
    }

    /**
     * Retrieves a Site by its unique numeric ID
     *
     * @param siteId the numeric ID of the site
     * @return the corresponding Site object
     */
    @Override
    public Site getSite(int siteId) {
        // shortcut for negatives
        if (siteId < 0) {
            return null;
        }

        return siteDao.getSite(siteId);
    }

    /**
     * Returns a List of Users associated with a specific Site ID. The
     * list will be empty if there are no users associated with the site (not null).
     *
     * @param siteId the numeric ID of the Site
     * @return a List<User> of the Users
     */
    @Override
    public List<User> getUsers(int siteId) {

        return siteDao.getUsers(siteId);
    }

    /**
     * Returns the count of the number of users for a Site
     *
     * @param siteId the numeric Site ID
     * @return an int user count, always >= 0
     */
    @Override
    public int getUserCount(int siteId) {

        return siteDao.getUserCount(siteId);
    }

    /**
     * Returns a List of Users for the specific Site who are currently locked out.
     *
     * @param siteId the numeric ID of the Site
     * @return a List<User> of the locked-out Users
     */
    @Override
    public List<User> getLockedOutUsers(int siteId) {

        return siteDao.getLockedOutUsers(siteId);
    }

    /**
     * Returns a count of the number of users for a Site that
     * are currently locked out
     *
     * @param siteId the numeric Site ID
     * @return an int count of locked out users, always >= 0
     */
    @Override
    public int getLockedOutUserCount(int siteId) {

        return siteDao.getLockedOutUserCount(siteId);
    }

     /**
     * Update the data for the specified Site. Only certain fields are allowed
     * to be updated. Changes to read-only fields are ignored.
     * Note that the ID property of the Site must be present. If ID is not set,
     * this method will assume you are trying to create instead of update, and will
     * throw an IllegalArgumentException
     * @param site the Site to update
     */
    @Override
    public void updateSite(Site site) {

         if(site == null || site.getId() < 0) {
             throw new IllegalArgumentException("Cannot update a site which does not have an ID");
         }

         siteDao.updateSite(site);
    }

    /**
     * Creates a new Site in the system and sets the site ID on the provided
     * Site object
     * @param site a Site object containing the initial data for record
     */
    @Override
    public void createSite(Site site) {

        if(site == null || site.getId() >= 0) {
            throw new IllegalArgumentException("Invalid Site to create (null or exists)");
        }

        siteDao.createSite(site);

    }

    /**
     * Given a criteria, find the list of Sites that match
     * @param criteria the text criteria
     * @return a List of matching Site objects
     */
    public List<Site> findSites(String criteria) {

        return siteSearchService.findSites(criteria);
    }
}
