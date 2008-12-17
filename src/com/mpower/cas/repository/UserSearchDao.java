package com.mpower.cas.repository;

import com.mpower.cas.domain.User;

import java.util.List;

/**
 * DAO interface to search the datastore for the Users that match
 * the criteria.
 * @version 1.0
 */
public interface UserSearchDao {

    /**
     * Find the Users who are subordinate to the specified site and
     * match the specified criteria.
     * @param criteria the text criteria
     * @param siteId the site ID parent
     * @return
     */
    public List<User> findUsers(String criteria, int siteId);

}
