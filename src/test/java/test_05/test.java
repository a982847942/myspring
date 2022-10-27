package test_05;

import nuaa.edu.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * @Classname test
 * @Description
 * @Date 2022/10/21 15:20
 * @Created by brain
 */
public class test {
    @Test
    public void test_prototype() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:test05.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService01 = applicationContext.getBean("userService", UserService.class);
        UserService userService02 = applicationContext.getBean("userService", UserService.class);

        // 3. 配置 scope="prototype/singleton"
        System.out.println(userService01);
        System.out.println(userService02);

        // 4. 打印十六进制哈希
        System.out.println(userService01 + " 十六进制哈希：" + Integer.toHexString(userService01.hashCode()));
        System.out.println(ClassLayout.parseInstance(userService01).toPrintable());

    }

    @Test
    public void test_factory_bean() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:test05.xml");
        applicationContext.registerShutdownHook();

        // 2. 调用代理方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
        System.out.println(ClassLayout.parseInstance(userService).toPrintable());
        System.out.println(userService + "十六进制哈希:" + Integer.toHexString(userService.hashCode()));
        UserService userService1 = applicationContext.getBean("userService", UserService.class);
        System.out.println(userService1 + "十六进制哈希：" + Integer.toHexString(userService1.hashCode()));
        System.out.println(ClassLayout.parseInstance(userService).toPrintable());

    }
    @Test
    public void test(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:test05.xml");
        IUserDao userDao = applicationContext.getBean("proxyUserDao", IUserDao.class);
//        System.out.println(userDao + "十六进制哈希：" + Integer.toHexString(userDao.hashCode()));
        IUserDao userDao1 = applicationContext.getBean("proxyUserDao", IUserDao.class);
//        System.out.println(userDao1 + "十六进制哈希：" + Integer.toHexString(userDao1.hashCode()));
    }
}
