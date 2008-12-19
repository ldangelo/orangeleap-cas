package com.mpower.cas.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.mpower.cas.domain.User;
import com.mpower.cas.repository.JdbcUserDao;


/**
 * @version 1.0
 */
public class JdbcUserServiceTest {

// note, we're cheating by specifying the Jdbc implementation
// specifically so we have access to the template for test
    // and clean-up code
    private UserService service = null;
    private ApplicationContext ctx = null;

    @Before
    public void setupTest() {
        ctx = new ClassPathXmlApplicationContext("springConfig.xml");
        service = (UserService) ctx.getBean("userService");
    }

    @Test
    public void testReset() {

        service.resetPassword(1,"tim","9936","mpower");


    }


}
