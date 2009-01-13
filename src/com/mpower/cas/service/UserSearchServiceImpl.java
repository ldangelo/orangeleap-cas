package com.mpower.cas.service;

import com.mpower.cas.domain.User;
import com.mpower.cas.repository.UserSearchDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

/**
 * Service Impl used for when a freeform search for a user needs to be performed.
 * This implementation depends on a UserSearchDao to check against the repository
 * @version 1.0
 */
public class UserSearchServiceImpl implements UserSearchService {

    private UserSearchDao userSearchDao;


    /**
     * Return the list of found users. If no users are found, the List will
     * be empty but not null. The will be no duplicates in the List returned.
     * @param criteria the text criteria
     * @param siteId
     * @return the List<User> of matching users
     */
    @Override
    public List<User> findUsers(String criteria, int siteId) {

        return userSearchDao.findUsers(criteria, siteId);

    }

    /**
     *  Injected reference to the UseSearchDao to use
     *  @param userSearchDao the UserSearchDao ref 
     */
    @Required
    public void setUserSearchDao(UserSearchDao userSearchDao) {
        this.userSearchDao = userSearchDao;
    }
}
