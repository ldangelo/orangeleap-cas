package com.mpower.cas.repository;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.mpower.cas.domain.Site;
import java.util.List;
import static org.junit.Assert.*;
/**
 * @version 1.0
 */
public class JdbcSiteSearchDaoTest {

private SiteSearchDao dao = null;


    @Before
    public void setupTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("springConfig.xml");
        dao = (SiteSearchDao) ctx.getBean("siteSearch");
    }

    @Test
    public void simpleFind() {
        List<Site> sites = dao.findSites("open");
        assertEquals(2,sites.size());
    }

    @Test
    public void emptyCriteria() {
        List<Site> sites = dao.findSites(null);
        assertEquals(3,sites.size());
    }



}
