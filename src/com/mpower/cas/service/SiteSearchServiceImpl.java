package com.mpower.cas.service;

import com.mpower.cas.domain.Site;
import com.mpower.cas.repository.SiteSearchDao;

import java.util.List;

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

    /* Injected property */
    public void setSiteSearchDao(SiteSearchDao siteSearchDao) {
        this.siteSearchDao = siteSearchDao;
    }
}
