package com.mpower.cas.repository;

import org.junit.Test;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.mpower.cas.domain.User;
import java.util.List;
import static org.junit.Assert.*;

/**
 * @version 1.0
 */
public class JdbcUserSearchDaoTest {

    private UserSearchDao dao = null;


    @Before
    public void setupTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("springConfig.xml");
        dao = (UserSearchDao) ctx.getBean("userSearch");

    }


    @Test
    public void simpleFind() {

        List<User> users = dao.findUsers("smi", 1);
        assertEquals(3, users.size());
        
    }

    @Test
    public void emptyFind() {

        List<User> users = dao.findUsers("zzz", 1);
        assertNotNull(users);
        assertEquals(0, users.size());
    }

    @Test
    public void allUsers() {
        
        List<User> users = dao.findUsers(null,1);
        assertEquals(4, users.size());        
    }


}
