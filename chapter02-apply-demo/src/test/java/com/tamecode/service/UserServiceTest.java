package com.tamecode.service;

import com.tamecode.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author Liqc
 * @date 2021/7/20 20:01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/spring-context.xml") // 指定配置文件，启动 Spring 容器
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
        UserService bean = applicationContext.getBean(UserService.class);
        boolean admin = bean.hasMatchUser("admin", "123456");
        System.out.println(admin);
    }

    @Test
    public void hasMatchUser() {
        boolean b1 = userService.hasMatchUser("admin", "123456");
        boolean b2 = userService.hasMatchUser("admin", "100001");
        assertTrue(b1);
        assertTrue(!b2);
    }

    @Test
    public void findUserByUsername() {
        assertEquals(userService.findUserByUsername("admin").getUsername(), "admin");
    }

    @Test
    public void loginSuccess() {
        User user = userService.findUserByUsername("admin");
        user.setUserId(1);
        user.setUsername("admin");
        user.setLastIp("192.168.12.7");
        user.setLastVisit(new Date());
        userService.loginSuccess(user);
    }
}
