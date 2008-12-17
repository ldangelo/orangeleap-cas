package com.mpower.cas.repository;

import com.mpower.cas.domain.Site;

import java.util.List;

/**
 * DAO to check against the backing datastore and attempt to find
 * a list of Sites that match the criteria.
 * @version 1.0
 */
public interface SiteSearchDao {

    /**
     * Given the criteria, find the Sites that match
     * @param criteria the text criteria
     * @return the list of Sites as List<Site>
     */
    public List<Site> findSites(String criteria);

}
