package com.mpower.cas.repository;


import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.mpower.cas.domain.User;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @version 1.0
 */
public class JdbcUserDaoTest {

    // note, we're cheating by specifying the Jdbc implementation
    // specifically so we have access to the template for test
    // and clean-up code
    private JdbcUserDao dao = null;
    private ApplicationContext ctx = null;

    @Before
    public void setupTest() {
        ctx = new ClassPathXmlApplicationContext("springConfig.xml");
        dao = (JdbcUserDao) ctx.getBean("userDao");
    }


    public void createUser() {

        User user = new User();
        user.setLoginName("tim");
        user.setFullName("Tim Sporcic");
        user.setSiteId(1);
        user.setActive(true);
        user.setSiteAdmin(true);
        user.setEmailAddress("tim@mpoweropen.com");
        
        dao.saveUser(user);

        assertTrue(user.getId() > -1);
        assertTrue(user.isLocked());
        assertNotNull(user.getLockedDate());
        assertNotNull(user.getResetCode());

        System.out.println("Reset Code: " + user.getResetCode());

        //dao.getSimpleJdbcTemplate().update("delete from users where id = ?", user.getId());


    }

    @Test
    public void resetUser() {
        User user = dao.getUser(5);
        user.setLocked(true);
        dao.setLockedOut(user);


    }





    @Test
    public void getUserTest() {

        User user = dao.getUser(1);
        assertEquals(1, user.getId());
        assertEquals("bsmith", user.getLoginName());
        assertEquals("Barney Smith", user.getFullName());
        assertFalse(user.isSiteAdmin());
        assertTrue(user.isActive());
        assertFalse(user.isLocked());

        user = dao.getUser("bsmith",1);
        assertEquals(1, user.getId());
        assertEquals("bsmith", user.getLoginName());
        assertEquals("Barney Smith", user.getFullName());
        assertFalse(user.isSiteAdmin());
        assertTrue(user.isActive());
        assertFalse(user.isLocked());
    }

    @Test
    public void updateUser() {
        User user = dao.getUser(1);
        user.setFullName("Larry Hines");
        user.setEmailAddress("larry@hines.com");
        user.setSiteAdmin(true);
        user.setActive(false);
        user.setLocked(true);
        dao.updateUser(user);

        user = dao.getUser(1);
        assertEquals("Larry Hines", user.getFullName());
        assertEquals("larry@hines.com", user.getEmailAddress());
        assertTrue(user.isSiteAdmin());
        assertFalse(user.isActive());
        assertTrue(user.isLocked());
        assertNotNull(user.getLockedDate());

         dao.getSimpleJdbcTemplate().update("update users set name='Barney Smith', emailaddress=null, " +
                 "adminuser=false, active=true, locked=false, lockeddttm=null where id = 1");
    }


}
