package com.mpower.cas.service;

import com.mpower.cas.domain.User;

import java.util.List;

/**
 * Service to find a list of Users based on a criteria String. The intent for
 * this service is to be the logic for the search box at the right side of the
 * menu bar on the screen.
 * @version 1.0
 */
public interface UserSearchService {

    /**
     * Return the list of found users. If no users are found, the List will
     * be empty but not null. The will be no duplicates in the List returned.
     * @param criteria the text criteria
     * @param siteId
     * @return the List<User> of matching users
     */
    public List<User> findUsers(String criteria, int siteId);

}
