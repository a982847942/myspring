package test_04;

import nuaa.edu.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @Classname test
 * @Description
 * @Date 2022/10/21 13:25
 * @Created by brain
 */
public class test {
    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:test04.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
        System.out.println("ApplicationContextAware："+userService.getApplicationContext());
        System.out.println("BeanFactoryAware："+userService.getBeanFactory());
    }
}
