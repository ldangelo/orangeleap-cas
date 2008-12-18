package com.mpower.cas.service;

import com.mpower.cas.domain.Site;
import com.mpower.cas.repository.SiteSearchDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

/**
 * Implementation of the SiteSearchService. This implementation makes
 * use of the SiteSearchDao to search the backing datastore using the
 * criteria.
 * @version 1.0
 */
public class SiteSearchServiceImpl implements SiteSearchService {

    private SiteSearchDao siteSearchDao;

    @Override
    public List<Site> findSites(String criteria) {
        return siteSearchDao.findSites(criteria);
    }

    /**
     * Injected reference to the SiteSearchDao to use
     * @param siteSearchDao the SiteSearchDao ref
     */
    @Required
    public void setSiteSearchDao(SiteSearchDao siteSearchDao) {
        this.siteSearchDao = siteSearchDao;
    }
}
