package com.mpower.cas.service;

import com.mpower.cas.domain.Site;

import java.util.List;

/**
 * Service used to find a Site. This service is designed for the search
 * box on the right of the menu bar on the screens. Site search should only
 * be peformed by someone who is an MPower admin, as it looks across all the sites.
 * Normal admins will only ever be searching within their own site and won't
 * have need of cross-site searches.
 * @version 1.0
 */
public interface SiteSearchService {

    /**
     * Given the criteria, find a list of Sites that match
     * @param criteria the text criteria
     * @return the list of Sites as List<Site>
     */
    public List<Site> findSites(String criteria);

}
