package test_06;

import nuaa.edu.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;
import test_05.UserService;

import java.lang.reflect.Proxy;

/**
 * @Classname test
 * @Description
 * @Date 2022/10/22 10:26
 * @Created by brain
 */
public class test {
    @Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:test06.xml");
        applicationContext.publishEvent(new CustomEvent(new User(20, "赵总"), 1019129009086763L, "成功了！"));

        applicationContext.registerShutdownHook();
    }

}

